package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dao.DislikeUrlHome;
import com.app.dao.LikeUrlHome;
import com.app.model.DislikeUrl;
import com.app.model.LikeUrl;
import com.app.model.Note;
import com.app.service.NoteService;
import com.app.service.RecordService;
import com.app.utils.IpUtils;
import com.app.utils.SpanderTool;
import com.app.utils.StringUtil;

@Controller
public class SearchController {
	@Resource
	private NoteService noteService;
	@Resource
	private DislikeUrlHome disLikeUrlHome;
	@Resource
	private LikeUrlHome likeUrlHome;
	@Resource
	private RecordService recordService;
	String uname = "";
	int uid = 10000;

	@RequestMapping(value = "/addBlack", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addBlack(HttpSession session,
			HttpServletRequest request) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		uname = (String) session.getAttribute("lovelxfName");
		String titleName = request.getParameter("titleName").toString();
		String url = request.getParameter("url").toString();
		if (uname == null) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		uid = Integer.parseInt((String) session.getAttribute("lovelxfId"));
		DislikeUrl DislikeUrl = new DislikeUrl();
		DislikeUrl.setDate(StringUtil.getTimeStr());
		DislikeUrl.setUrl(url);
		DislikeUrl.setTitle(titleName);
		DislikeUrl.setUid(uid);
		disLikeUrlHome.save(DislikeUrl);
		hashMap.put("status", "true");
		return hashMap;

	}

	@RequestMapping(value = "/addLove", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addLove(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		uname = (String) session.getAttribute("lovelxfName");
		String titleName = request.getParameter("titleName").toString();
		String url = request.getParameter("url").toString();
		String content = request.getParameter("content").toString();
		String part = request.getParameter("part").toString();
		String urlTitle = request.getParameter("urlTitle").toString();
		String title = titleName;
		if (uname == null) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		uid = Integer.parseInt((String) session.getAttribute("lovelxfId"));
		LikeUrl likeUrl = new LikeUrl();
		likeUrl.setDate(StringUtil.getTimeStr());
		likeUrl.setUrl(url);
		likeUrl.setTitle(titleName);
		likeUrl.setUid(uid);
		likeUrlHome.save(likeUrl);
		for (int i = 0; i < 100; i++) {
			if (noteService.checkTitle(uname, title)) {
				if (i < 50) {
					title = titleName + StringUtil.getRandom();
				} else {
					title = titleName + StringUtil.getRandom()
							+ StringUtil.getRandom();
				}
			} else {
				break;
			}
			if (i == 99) {
				hashMap.put("status", "toomany");
				return hashMap;
			}
		}
		Note note = new Note();
		note.setUname(uname);
		note.setTitle(title);
		note.setContent(urlTitle + "<p></p>" + content);
		note.setType("其他");
		note.setPart(part);
		note.setExtendone(urlTitle);
		noteService.save(note);
		hashMap.put("status", "true");
		return hashMap;
	}

	@RequestMapping(value = "/getSearch", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getSearch(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			String ip = IpUtils.getIpAddr(request);
			String name = (String) session.getAttribute("lovelxfName");

			String device = request.getHeader("user-agent");
			if (device != null) {
				if (device.contains("Android")) {
					device = "Android";
				} else if (device.contains("iPhone")) {
					device = "iPhone";
				} else if (device.contains("iPad")) {
					device = "iPad";
				} else {
					device = "pc";
				}
			}
			recordService.addRecord(ip, name, device, 1);

			String keyword = request.getParameter("keyword").toString();
			String toAddress = request.getParameter("to").toString();
			int num = Integer.parseInt(request.getParameter("num"));
			if (toAddress.equals("百度")) {
				list = fromBaidu(keyword, num);
			}
			if (toAddress.equals("本站")) {
				list = fromMynet(keyword, num);
			}
			if (toAddress.equals("CSDN")) {
				list = fromCSDN(keyword, num);
			}
			if (toAddress.equals("脚本之家")) {
				list = fromJiaoben(keyword, num);
			}
			if (toAddress.equals("博客园")) {
				list = fromBokeyuan(keyword, num);
			}
			if (toAddress.equals("新浪博客")) {
				list = fromSina(keyword, num);
			}
			if (toAddress.equals("码农网")) {
				list = fromManong(keyword, num);
			}
			if (toAddress.equals("前端开发网")) {
				list = fromQianduankaifa(keyword, num);
			}
			if (toAddress.equals("网易博客")) {
				list = fromWangyi(keyword, num);
			}
			if (toAddress.equals("前端里")) {
				list = fromQiandaunli(keyword, num);
			}
			if (!StringUtil.isBlank(name)) {
				uid = Integer.parseInt((String) session
						.getAttribute("lovelxfId"));
				list = filter(list, uid);
			}
			hashMap.put("status", "true");
			if (list == null) {
				hashMap.put("total", "0");
			} else {
				hashMap.put("total", "" + list.size());
			}
			hashMap.put("data", list);
			ip = null;
			name = null;
			device = null;
			keyword = null;
			toAddress = null;
			list = null;
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
			hashMap.put("status", "false");
			return hashMap;
		}
	}

	private List<HashMap<String, String>> filter(
			List<HashMap<String, String>> list, int uid) {
		if (list == null || list.size() == 0) {
			return null;
		}
		List<DislikeUrl> disLikeList = disLikeUrlHome.findByUid(uid);
		List<LikeUrl> likeList = likeUrlHome.findByUid(uid);
		if ((disLikeList == null || disLikeList.size() == 0)
				&& (likeList == null || likeList.size() == 0)) {
			return list;
		}
		List<HashMap<String, String>> listNew = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> map : list) {
			String titel = map.get("title");
			if (!isHasInDisLike(titel, disLikeList)) {
				if (isHasInLike(titel, likeList)) {
					map.put("isLike", "true");
				} else {
					map.put("isLike", "false");
				}
				listNew.add(map);
			}
		}
		return listNew;
	}

	private boolean isHasInDisLike(String titel, List<DislikeUrl> disLikeList) {
		if (disLikeList != null && disLikeList.size() > 0) {
			for (DislikeUrl dislikeUrl : disLikeList) {
				String url = dislikeUrl.getUrl();
				if (titel.contains(url)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isHasInLike(String titel, List<LikeUrl> likeList) {
		if (likeList != null && likeList.size() > 0) {
			for (LikeUrl likeUrl : likeList) {
				String url = likeUrl.getUrl();
				if (titel.contains(url)) {
					return true;
				}
			}
		}
		return false;
	}

	private List<HashMap<String, String>> fromQiandaunli(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromQianduanli(keyword, num);
	}

	private List<HashMap<String, String>> fromWangyi(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromWangyi(keyword, num);
	}

	private List<HashMap<String, String>> fromQianduankaifa(String keyword,
			int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromQianduankaifa(keyword, num);
	}

	private List<HashMap<String, String>> fromManong(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromManongwang(keyword, num);
	}

	private List<HashMap<String, String>> fromSina(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromSina(keyword, num);
	}

	private List<HashMap<String, String>> fromBokeyuan(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromBokeyaun(keyword, num);
	}

	private List<HashMap<String, String>> fromJiaoben(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromJiaoben(keyword, num);
	}

	private List<HashMap<String, String>> fromCSDN(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromCSDN(keyword, num);
	}

	private List<HashMap<String, String>> fromBaidu(String keyword, int page) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getBaidu(keyword, page);
	}

	private List<HashMap<String, String>> fromMynet(String keyword, int page) {
		List<HashMap<String, String>> listData = new ArrayList<HashMap<String, String>>();
		String[] keyWords = keyword.toString().split("\\s+");
		HashMap<String, Object> data = noteService.findMain(keyWords, page);
		if (data != null && data.size() > 0) {
			int size = (Integer) data.get("total");
			if (size > 0) {
				List<Note> list = (List<Note>) data.get("rows");
				Iterator<Note> it = list.iterator();
				while (it.hasNext()) {
					Note note = it.next();
					if (note != null) {
						HashMap<String, String> map = new HashMap<String, String>();
						String url = "http://note.lovelxf.com/note?id="
								+ note.getId();
						String title = "<a href=\"" + url
								+ "\" target=\"_blank\"><em>" + note.getTitle()
								+ "</em></a>";
						map.put("title", title);
						map.put("author", note.getUname());
						map.put("content", "原文:" + note.getExtendone() + ".摘要:"
								+ note.getPart());
						listData.add(map);
					}
				}
			}

		}
		return listData;
	}
}
