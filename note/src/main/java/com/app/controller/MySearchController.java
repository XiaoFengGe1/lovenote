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
import com.app.utils.IpUtils;
import com.app.utils.SpanderTool;
@Controller
public class MySearchController {
	@Resource
	private UserService userService;
	@Resource
	private RecordService recordService;
	
	@RequestMapping(value = "/getMySearch", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getSearch(HttpSession session,HttpServletRequest request) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
			try{
				String ip =  IpUtils.getIpAddr(request);
				String name = (String) session.getAttribute("lovelxfName");
				String device = request.getHeader("user-agent");
				if(device!=null){
					if(device.contains("Android")) {
						device = "Android";
					} else if(device.contains("iPhone")) {
						device = "iPhone";
					}  else if(device.contains("iPad")) {
						device = "iPad";
					} else{
						device = "pc";
					}
				}
				recordService.addRecord(ip,name,device,1);
				String keyword = request.getParameter("keyword").toString();
				String toAddress = request.getParameter("to").toString();
				int num = Integer.parseInt(request.getParameter("num"));
				if(toAddress.equals("baidu")){
					list = fromBaidu(keyword,num);
				}
				hashMap.put("status","true");
				if(list==null){
					hashMap.put("total",0);
				}else{
					hashMap.put("total",list.size());
				}
				hashMap.put("data", list);
				ip = null;
				name = null;
				device = null;
				keyword = null;
				toAddress = null;
				list = null;
				return hashMap;
			}catch (Exception e) {
				e.printStackTrace();
				list = null;
				hashMap.put("status","false");
				return hashMap;
			}
	}

	private List<HashMap<String, String>> fromBaidu(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getBaidu(keyword, num);
	}
}