package com.app.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Message;
import com.app.service.MessageService;
import com.app.utils.IpUtils;
import com.app.utils.VAR;

@Controller
public class MessageController {
	@Resource
	private MessageService messageService;

	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addMessage(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String uname = (String) session.getAttribute("lovelxfName");
		if (uname == null) {
			uname = "";
		}
		String content = request.getParameter("content");
		if (content.length() > 1000) {
			content = content.substring(0, 1000);
		}
		Message message = new Message();
		String ip = IpUtils.getIpAddr(request);
		message.setName(uname);
		message.setIp(ip);
		message.setContent(content);
		messageService.save(message);
		hashMap.put("status", "true");
		return hashMap;
	}

	@RequestMapping(value = "/getMessageInit", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getMessageInit(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		List<Message> list = messageService.findLastData(VAR.messageInitNum);
		if (list == null || list.size() == 0) {
			hashMap.put("total", 0);
			hashMap.put("data", "");
			hashMap.put("status", "false");
		} else {
			hashMap.put("total", list.size());
			hashMap.put("data", list);
			hashMap.put("status", "true");
		}
		return hashMap;
	}

	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getMessage(HttpSession session,
			HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String endStr = request.getParameter("end");
		if (endStr.matches(VAR.matchNumber)) {
			int end = Integer.parseInt(endStr);
			if (end > 1) {
				int start = end - VAR.messageStep;
				if (start < 1) {
					start = 1;
				}
				List<Message> list = messageService.findByFromTo(start, end);
				if (list != null && list.size() > 0) {
					hashMap.put("total", list.size());
					hashMap.put("data", list);
					hashMap.put("status", "true");
					return hashMap;
				}
			}
			hashMap.put("total", 0);
			hashMap.put("data", "");
			hashMap.put("status", "false");
		} else {
			hashMap.put("status", "format");
		}
		return hashMap;
	}
}