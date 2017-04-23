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

import com.app.model.Type;
import com.app.model.User;
import com.app.service.NoteService;
import com.app.service.TypeService;
import com.app.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private NoteService noteService;
	@Resource
	private TypeService typeService;
	
	String flag="false";
	
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getUser(HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String uname =(String) session.getAttribute("lovelxfName");
		User user = userService.findByName(uname);
		hashMap.put("status","true");
		hashMap.put("data", user);
		uname = null;
		user = null;
		return hashMap;
	}
	
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> setUser(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String name = req.getParameter("name");
		String uname =(String) session.getAttribute("lovelxfName");
		if(!(name.equals(uname))&&userService.CheckByName(name)){
			hashMap.put("status","hasname");
			return hashMap;
		}else{
			User user;
			user = userService.findByName(uname);
			String flag = "true";
			String sex = req.getParameter("sex");
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String password = req.getParameter("password");
			String passwordCheck = "562150580a896eca18906e51dda79acc";
			if(tel.equals("0")){
				
			}else{
				user.setTel(Long.parseLong(tel));
			}
			if(password.equals(passwordCheck)){
				
			}else{
				flag = "password";
				user.setPassword(password);
			}
			user.setEmail(email);
			user.setName(name);
			user.setSex(sex);
			user.setId(Integer.parseInt(session.getAttribute("lovelxfId").toString()));
			userService.update(user);
			hashMap.put("status",flag);
			uname = null;
			name = null;
			user = null;
			flag = null;
			sex = null;
			email = null;
			tel = null;
			password = null;
			passwordCheck = null;
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/getClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getClass(HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String uname =(String) session.getAttribute("lovelxfName");
		 List<Type> data = typeService.findByName(uname);
		 List<HashMap<String,Object>> list=new ArrayList< HashMap<String,Object>>();
		 for(int i=0;i<data.size();i++){
			 HashMap<String,Object> detail = new HashMap<String,Object>();
			 detail.put("class", data.get(i).getClassify());
			 detail.put("id", data.get(i).getId());
			 list.add(detail);
			 detail = null;
		 }
		 hashMap.put("status","true");
		 hashMap.put("data", list);
		 uname =  null;
		 data = null;
		 list = null;
		 return hashMap;
	}
	
	@RequestMapping(value = "/addClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> addClass(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String uname =(String) session.getAttribute("lovelxfName");
		String addClass =  req.getParameter("add");
		Type type = new Type();
		type.setClassify(addClass);
		type.setName(uname);
		typeService.save(type);
		hashMap.put("status","true");
		uname = null;
		addClass = null;
		type = null;
		return hashMap;
	}
	
	@RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> deleteClass(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String uname =(String) session.getAttribute("lovelxfName");
		String id =  req.getParameter("id");
		String className = req.getParameter("name");
		typeService.delete(Integer.parseInt(id));
		noteService.fixType(className,uname);
		hashMap.put("status","true");
		uname = null;
		id = null;
		className = null;
		return hashMap;
	}

}
