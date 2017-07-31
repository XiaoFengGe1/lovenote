package com.app.controller.android;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.model.Note;
import com.app.model.Type;
import com.app.model.User;
import com.app.service.NoteService;
import com.app.service.RecordService;
import com.app.service.TypeService;
import com.app.service.UserService;
import com.app.utils.Email;
import com.app.utils.EncodingTools;
import com.app.utils.MD5Util;
import com.app.utils.VAR;

@Controller
@RequestMapping("/android")
public class LoginControllerA {
	@Resource
	private UserService userService;
	@Resource
	private NoteService noteService;
	@Resource
	private TypeService typeService;
	@Resource
	private RecordService recordService;
	
	String flag="false";
	String password="";
	String email="";
	String name="";
	String emailData="";
	Timer timer = null;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/android/login";
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {
		return "/android/search";
	}
	@RequestMapping(value = "/money", method = RequestMethod.GET)
	public String money() {
		return "/android/money";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index1() {
		return "/android/index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session) {
		try {
			String pass = request.getParameter("pass");
			if(pass !=null)
			session.setAttribute("lovelxfPass",pass);
			User user = userService.findByPass(pass);
			session.setAttribute("lovelxfName", user.getName());
			
		} catch (Exception e) {
			return "/android/index";
		}
		return "/android/index";
	}
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String note(@RequestParam("id") String noteid, HttpSession session) {
		if(noteid.matches(VAR.matchNumber)){
			session.setAttribute("lovelxfNoteId",noteid );
			return "/android/note";
		}else{
			return "/android/index";
		}
	}
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg() {
		return "/android/reg";
	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user() {
		return "/android/user";
	}
	@RequestMapping(value = "/user2", method = RequestMethod.GET)
	public String user2(@RequestParam("pass") String pass, HttpSession session) {
		try {
			session.setAttribute("lovelxfPass",pass);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/android/user";
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
			return "/android/write";
	}
	
	@RequestMapping(value = "/write2", method = RequestMethod.POST)
	@ResponseBody
	public String write2(@RequestParam("id") String id,@RequestParam("type") String type, @RequestParam("title") String title,@RequestParam("content") String content) {
		User user = null;
		user = userService.findByPass(id);
		if(user == null){
			return "false";
		}
		Boolean isExit= noteService.checkTitle(user.getName(), title);
		if(isExit==true){
			return "title";
		}
		Note note = new Note();
		note.setUname(user.getName());
		note.setTitle(title.trim());
		note.setContent(content.trim());
		note.setType(type);
		if(content.trim().length()>110){
			note.setPart(content.trim().substring(0, 100));
		}else{
			note.setPart(content.trim());
		}
		note.setType("其他");
		noteService.save(note);
		return "true";
	}
	
	@RequestMapping(value = "/gettype2", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String gettype2(@RequestParam("id") String id) {
		User user = null;
		user = userService.findByPass(id);
		if(user == null){
			return "";
		}
		List<Type> data = typeService.findByName(user.getName());
		String str = "";
		 for(int i=0;i<data.size();i++){
			 if(i == data.size()-1){
				 str = str + data.get(i).getClassify();
			 }else{
				 str = str + data.get(i).getClassify()+"@@@";
			 }
		 }
		 
		return str;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> login(HttpServletResponse resp, HttpServletRequest request,HttpSession session) {
		name = request.getParameter("account");
		password = request.getParameter("password");
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
			try{
				String result = userService.login(name);
				if(result.equals("")){
					hashMap.put("status","noname");
					return hashMap;
				}
				String[] result2 = result.split("@@@###");
				if(password.equals(result2[0])){
					session.setAttribute("lovelxfName", result2[1]);
					session.setAttribute("lovelxfId", result2[2]);
					setCookie(request, resp);
					flag = "true";
					hashMap.put("uid",result2[2]+"@@@@@@"+result2[0]);	
				}else{
					flag = "false";
				}
			}catch (Exception e) {
				flag = "false";
			}
		hashMap.put("status",flag);
		return hashMap;
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> reg(User user) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		if(!userService.CheckByName(user.getName())){
			try{
				userService.save(user);
				Type type = new Type();
				type.setClassify("其他");
				type.setName(user.getName());
				type.setIsdelete(0);
				typeService.save(type);
				flag = "true";
			}catch (Exception e){
				flag = "false";
			}
			hashMap.put("status",flag);
			return hashMap;
		}else{
			hashMap.put("status","noname");
			return hashMap;
		}
	}
	
	@RequestMapping(value = "/checkSixNum", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> checkSixNum(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String checkSixNum = req.getParameter("sixNum");
		String checkSixNumSessionl = (String) session.getAttribute("regSixNum");
		if(checkSixNum.equals(checkSixNumSessionl)){
			flag = "true";
		}else{
			flag = "false";
		}
		hashMap.put("status",flag);
		return hashMap;
	}
	
	@RequestMapping(value = "/findEmail", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> findEmail(HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String findEmail = req.getParameter("email");
		if(!userService.CheckByEmail(findEmail)){ //邮箱不存在，就发验证码
			Email email = new Email();
			int regSixNum = VAR.getSixRandom();
			session.setAttribute("regSixNum", ""+regSixNum);
			email.toAddress(findEmail, "验证码", "请在一分钟内输入验证码:"+regSixNum+",来自www.lovelxf.com。");
			email=null;
			flag = "true";
		}else{
			flag = "false";
		}
		findEmail=null;
		hashMap.put("status",flag);
		return hashMap;
	}
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> findPassword(HttpServletRequest req) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String findPasswordEmail = req.getParameter("email");
		int regSixNum = VAR.getSixRandom();
		String findpassword = MD5Util.getMD5(regSixNum + "e2ATh95jd");
		User user = userService.findByEmail(findPasswordEmail);

		if (user != null) {
			Email.toAddress(findPasswordEmail, "新密码", "您的新密码是:" + regSixNum
					+ "，请及时登录并修改密码。来自www.lovelxf.com。");
			user.setPassword(findpassword);
			userService.update(user);
			email = null;
			flag = "true";
		} else {
			flag = "false";
		}
		findPasswordEmail = null;
		findpassword = null;
		hashMap.put("status", flag);
		return hashMap;
	}
	
	@RequestMapping(value = "/quit", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> quit(HttpServletResponse resp,HttpServletRequest req,HttpSession session) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		Cookie[] cookies = req.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie c:cookies){
				c.setMaxAge(0);
				if(c.getName().equals("username")||c.getName().equals("password")){
					resp.addCookie(c);
				}
			}
		}
		cookies=null;
		session.invalidate();//清空session
		hashMap.put("status","true");
		return hashMap;
	}

	public void setCookie(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		if(request.getParameter("autoLogin")!=null&&request.getParameter("autoLogin").equals("1")){

			try {
				username=URLEncoder.encode(username,"UTF-8");//解决中文乱码问题-----无效！！！
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cookie cookie1 = new Cookie("username",username);
			Cookie cookie2 = new Cookie("password",password);
			cookie1.setMaxAge(14*24*60*60);
			cookie2.setMaxAge(14*24*60*60);
			cookie1.setPath("/");//这样所有路径都可以访问。
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		}else{
			Cookie[] cookies = request.getCookies();
			if(cookies!=null&&cookies.length>0){
				for(Cookie c:cookies){
					c.setMaxAge(0);
					if(c.getName().equals("username")||c.getName().equals("password")){
						response.addCookie(c);
					}
				}
			}
		}
	}
}
