package com.app.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Review;
import com.app.service.ReviewService;
import com.app.utils.StringUtil;

@Controller
public class ReviewController {
	@Resource
	private ReviewService reviewService;

	@RequestMapping(value = "/getreview", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getUser(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String idStr = (String) session.getAttribute("lovelxfNoteId");
			String page = req.getParameter("page");
			map = reviewService.findReview(Integer.parseInt(idStr),
					Integer.parseInt(page));
			hashMap.put("status", "true");
			hashMap.put("total", map.get("total"));
			hashMap.put("data", map.get("rows"));
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("status", "false");
			return hashMap;
		}
	}

	@RequestMapping(value = "/addreview", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> setUser(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String name = (String) session.getAttribute("lovelxfName");
		if (name == null) {
			hashMap.put("status", "false");
			return hashMap;
		} else {
			String content = req.getParameter("content");
			if (content.length() > 1000) {
				content = content.substring(0, 1000);
			}
			content = StringUtil.getLegalStr(content);
			String noteid = (String) session.getAttribute("lovelxfNoteId");
			Review review = new Review();
			review.setUname(name);
			review.setNoteid(Integer.parseInt(noteid));
			review.setContent(content);
			reviewService.save(review);
			hashMap.put("status", "true");
			hashMap.put("noteid", noteid);
			return hashMap;
		}
	}

}
