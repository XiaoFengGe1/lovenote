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
import com.app.utils.SpanderTool;

@Controller
public class SearchController {
	@Resource
	private UserService userService;
	@Resource
	private RecordService recordService;
	
	@RequestMapping(value = "/getSearch", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getSearch(HttpSession session,HttpServletRequest request) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
			try{
				String ip = IpUtils.getIpAddr(request);
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
				if(toAddress.equals("CSDN")){
					list = fromCSDN(keyword,num);
				}
				if(toAddress.equals("脚本之家")){
					list = fromJiaoben(keyword,num);
				}
				if(toAddress.equals("博客园")){
					list = fromBokeyuan(keyword,num);
				}
				if(toAddress.equals("新浪博客")){
					list = fromSina(keyword,num);
				}
				if(toAddress.equals("码农网")){
					list = fromManong(keyword,num);
				}
				if(toAddress.equals("前端开发网")){
					list = fromQianduankaifa(keyword,num);
				}
				if(toAddress.equals("网易博客")){
					list = fromWangyi(keyword,num);
				}
				if(toAddress.equals("前端里")){
					list = fromQiandaunli(keyword,num);
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

	private List<HashMap<String, String>> fromQiandaunli(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromQianduanli(keyword, num);
	}

	private List<HashMap<String, String>> fromWangyi(String keyword, int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromWangyi(keyword, num);
	}

	private  List<HashMap<String, String>> fromQianduankaifa(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromQianduankaifa(keyword, num);
	}

	private  List<HashMap<String, String>> fromManong(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromManongwang(keyword, num);
	}

	private  List<HashMap<String, String>> fromSina(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromSina(keyword, num);
	}

	private  List<HashMap<String, String>> fromBokeyuan(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromBokeyaun(keyword, num);
	}

	private  List<HashMap<String, String>> fromJiaoben(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromJiaoben(keyword, num);
	}

	private List<HashMap<String, String>> fromCSDN(String keyword,int num) {
		SpanderTool SpanderTool = new SpanderTool();
		return SpanderTool.getFromCSDN(keyword, num);
	}
}

class IpUtils {  
    public static String getIpAddr(HttpServletRequest request) {  
           String ip = request.getHeader("X-Forwarded-For");  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("Proxy-Client-IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("WL-Proxy-Client-IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_CLIENT_IP");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
           }  
           if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getRemoteAddr();  
           }  
           return ip;  
       }  
}  