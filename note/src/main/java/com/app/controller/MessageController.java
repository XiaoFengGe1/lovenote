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

import com.app.service.RecordService;
import com.app.service.UserService;

@Controller
public class MessageController {
	@Resource
	private UserService userService;
	@Resource
	private RecordService recordService;

	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getSearch(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		hashMap.put("total", 0);
		hashMap.put("data", list);
		hashMap.put("status", "false");
		return hashMap;
	}

}