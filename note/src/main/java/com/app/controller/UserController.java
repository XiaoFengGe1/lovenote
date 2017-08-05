package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.app.model.Type;
import com.app.model.User;
import com.app.service.NoteService;
import com.app.service.TypeService;
import com.app.service.UserService;
import com.app.utils.MD5Util;
import com.app.utils.StringUtil;
import com.app.utils.VAR;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private NoteService noteService;
	@Resource
	private TypeService typeService;
	@Resource
	private DislikeUrlHome disLikeUrlHome;
	@Resource
	private LikeUrlHome likeUrlHome;
	String flag = "false";

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getUser(HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		User user = userService.findByName(uname);
		hashMap.put("status", "true");
		hashMap.put("data", user);
		return hashMap;
	}

	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> setUser(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String name = req.getParameter("name");
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		name = StringUtil.getLegalStr(name);
		if (!(name.equals(uname)) && userService.CheckByName(name)) {
			hashMap.put("status", "hasname");
			return hashMap;
		} else {
			User user;
			user = userService.findByName(uname);
			String flag = "true";
			String sex = req.getParameter("sex");
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			if (tel.equals("0")) {

			} else {
				user.setTel(Long.parseLong(tel));
			}
			if (!password.equals(VAR.passwordCheck)) {
				flag = "password";
				if (StringUtil.isNotBlank(user.getExtendone())) {
					password = MD5Util.getMD5(password + user.getExtendone());
				} else {
					String randomStr = VAR.getRandomString(20);
					user.setExtendone(randomStr);
					password = MD5Util.getMD5(password + randomStr);
				}
				user.setPassword(password);
			}
			user.setEmail(email);
			user.setName(name);
			user.setSex(sex);
			user.setId(Integer.parseInt(session.getAttribute("lovelxfId")
					.toString()));
			userService.update(user);
			hashMap.put("status", flag);
			return hashMap;
		}
	}

	@RequestMapping(value = "/getClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getClass(HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		List<Type> data = typeService.findByName(uname);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, Object> detail = new HashMap<String, Object>();
			detail.put("class", data.get(i).getClassify());
			detail.put("id", data.get(i).getId());
			list.add(detail);
		}
		hashMap.put("status", "true");
		hashMap.put("data", list);
		return hashMap;
	}

	@RequestMapping(value = "/addClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addClass(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		String addClass = req.getParameter("add");
		addClass = StringUtil.getLegalStr(addClass);
		Type type = new Type();
		type.setClassify(addClass);
		type.setName(uname);
		typeService.save(type);
		hashMap.put("status", "true");
		return hashMap;
	}

	@RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> deleteClass(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		String id = req.getParameter("id");
		String className = req.getParameter("name");
		typeService.delete(Integer.parseInt(id));
		noteService.fixType(className, uname);
		hashMap.put("status", "true");
		return hashMap;
	}

	@RequestMapping(value = "/getUrls", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getUrls(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		int uid = Integer.parseInt((String) session.getAttribute("lovelxfId"));
		String type = req.getParameter("type");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String searchStr = req.getParameter("searchStr");
		startTime = startTime.replace("-", "");
		endTime = endTime.replace("-", "");
		if (startTime.length() != 8 || endTime.length() != 8) {
			hashMap.put("status", "timeError");
			return hashMap;
		}
		if (Integer.parseInt(endTime) < Integer.parseInt(startTime)) {
			hashMap.put("status", "timeError");
			return hashMap;
		}
		startTime = startTime + "000000";
		endTime = endTime + "595959";
		List list = null;
		if ("1".equals(type)) {
			list = likeUrlHome.search(uid, searchStr, startTime, endTime);
		} else if ("2".equals(type)) {
			list = disLikeUrlHome.search(uid, searchStr, startTime, endTime);
		}
		if (list == null) {
			hashMap.put("total", "0");
		} else {
			hashMap.put("total", "" + list.size());
		}
		hashMap.put("data", list);
		hashMap.put("status", "true");
		return hashMap;
	}

	@RequestMapping(value = "/deleteUrl", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> deleteUrl(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (StringUtil.isBlank(uname)) {
			hashMap.put("status", "timeout");
			return hashMap;
		}
		String type = req.getParameter("type");
		String id = req.getParameter("id");
		if ("1".equals(type)) {
			likeUrlHome.delete(Integer.parseInt(id));
		} else if ("2".equals(type)) {
			disLikeUrlHome.delete(Integer.parseInt(id));
		}
		hashMap.put("status", "true");
		return hashMap;
	}
}
