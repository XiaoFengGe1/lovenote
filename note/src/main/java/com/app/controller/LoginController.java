package com.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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

import com.app.model.Type;
import com.app.model.User;
import com.app.service.NoteService;
import com.app.service.RecordService;
import com.app.service.TypeService;
import com.app.service.UserService;
import com.app.utils.Email;
import com.app.utils.MD5Util;
import com.app.utils.VAR;

@Controller
@SessionAttributes({ "username", "id" })
public class LoginController {
	@Resource
	private UserService userService;
	@Resource
	private NoteService noteService;
	@Resource
	private TypeService typeService;
	@Resource
	private RecordService recordService;

	String flag = "false";
	String password = "";
	String email = "";
	String name = "";
	String emailData = "";
	Timer timer = null;

	@RequestMapping(value = "/baidu_verify_RLVPqgPGmI.html", method = RequestMethod.GET)
	public String baidu_verify_RLVPqgPGmI() {
		return "/baidu_verify_RLVPqgPGmI";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/front/login";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request) {
		String s1 = request.getHeader("user-agent");
		if (s1.contains("Android")) {
			s1 = null;
			return "/weixin/search";
		} else if (s1.contains("iPhone")) {
			s1 = null;
			return "/weixin/search";
		} else if (s1.contains("iPad")) {
			s1 = null;
			return "/weixin/search";
		}
		return "/front/search";
	}

	@RequestMapping(value = "/mysearch", method = RequestMethod.GET)
	public String mysearch(HttpServletRequest request) {
		return "/front/mysearch";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index1(HttpServletRequest request) {
		String s1 = request.getHeader("user-agent");
		if (s1.contains("Android")) {
			s1 = null;
			return "/weixin/index";
		} else if (s1.contains("iPhone")) {
			s1 = null;
			return "/weixin/index";
		} else if (s1.contains("iPad")) {
			s1 = null;
			return "/weixin/index";
		}
		return "/front/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		String s1 = request.getHeader("user-agent");
		if (s1.contains("Android")) {
			s1 = null;
			return "/weixin/index";
		} else if (s1.contains("iPhone")) {
			s1 = null;
			return "/weixin/index";
		} else if (s1.contains("iPad")) {
			s1 = null;
			return "/weixin/index";
		}
		return "/front/index";
	}

	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String note(@RequestParam("id") String noteid, HttpSession session,
			HttpServletRequest request) {
		if (noteid.matches(VAR.matchNumber)) {
			session.setAttribute("lovelxfNoteId", noteid);
			String s1 = request.getHeader("user-agent");
			if (s1.contains("Android")) {
				s1 = null;
				return "/weixin/note";
			} else if (s1.contains("iPhone")) {
				s1 = null;
				return "/weixin/note";
			} else if (s1.contains("iPad")) {
				s1 = null;
				return "/weixin/note";
			}
			return "/front/note";
		} else {
			return "/front/index";
		}
	}

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg() {
		return "/front/reg";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpServletRequest request) {
		try {
			String s1 = request.getHeader("user-agent");
			if (s1.contains("Android")) {
				s1 = null;
				return "/weixin/user";
			} else if (s1.contains("iPhone")) {
				s1 = null;
				return "/weixin/user";
			} else if (s1.contains("iPad")) {
				s1 = null;
				return "/weixin/user";
			}
			return "/front/user";
		} catch (Exception e) {
			return "/front/index";
		}
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request) {
		try {
			String s1 = request.getHeader("user-agent");
			if (s1.contains("Android")) {
				s1 = null;
				return "/weixin/write";
			} else if (s1.contains("iPhone")) {
				s1 = null;
				return "/weixin/write";
			} else if (s1.contains("iPad")) {
				s1 = null;
				return "/weixin/write";
			}
			return "/front/write";
		} catch (Exception e) {
			return "/front/index";
		}
	}

	// produces参数解决get中文乱码问题。
	@RequestMapping(value = "/email", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String get() {
		if (timer != null) {
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Email email = new Email();
					Map<String, String> map = recordService.getData();
					emailData = "访问IP数:" + map.get("id") + "全网搜点击数:"
							+ map.get("searchnum") + "首页点击数:"
							+ map.get("indexnum") + "<br>所有用户"
							+ map.get("users");
					email.toAddress("1174254785@qq.com", "爱笔记数据汇报", emailData);
					email = null;
					map = null;
				}
			}, 24 * 60 * 60 * 1000, 24 * 60 * 60 * 1000);
		}
		Map<String, String> map = recordService.getData();
		emailData = "访问IP数:" + map.get("id") + "全网搜点击数:" + map.get("searchnum")
				+ "首页点击数:" + map.get("indexnum") + "所有用户:" + map.get("users");
		return emailData;
	}

	/*
	 * //微信公众号请求地址....因公众号审核问题，暂时弃用。
	 * 
	 * @RequestMapping(value = "/weixinlovenote", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String weixin(HttpServletResponse resp,
	 * HttpServletRequest request) { // 微信加密签名 String signature =
	 * request.getParameter("signature"); // 时间戳 String timestamp =
	 * request.getParameter("timestamp"); // 随机数 String nonce =
	 * request.getParameter("nonce"); // 随机字符串 String echostr =
	 * request.getParameter("echostr");
	 * 
	 * // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 if
	 * (WinxinSignatureTool.checkSignature(signature, timestamp, nonce)) {
	 * return echostr; } return ""; }
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> login(HttpServletResponse resp,
			HttpServletRequest request, HttpSession session) {
		name = request.getParameter("account");
		password = request.getParameter("password");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			String result = userService.login(name);
			if (result.equals("")) {
				hashMap.put("status", "noname");
				return hashMap;
			}
			String[] result2 = result.split("@@@###");
			if (password.equals(result2[0])) {
				session.setAttribute("lovelxfName", result2[1]);
				session.setAttribute("lovelxfId", result2[2]);
				setCookie(request, resp);
				flag = "true";
			} else {
				flag = "false";
			}
		} catch (Exception e) {
			flag = "false";
		}
		hashMap.put("status", flag);
		return hashMap;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> reg(User user) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (!userService.CheckByName(user.getName())) {
			try {
				userService.save(user);
				Type type = new Type();
				type.setClassify("其他");
				type.setName(user.getName());
				type.setIsdelete(0);
				typeService.save(type);
				flag = "true";
			} catch (Exception e) {
				flag = "false";
			}
			hashMap.put("status", flag);
			return hashMap;
		} else {
			hashMap.put("status", "noname");
			return hashMap;
		}
	}

	@RequestMapping(value = "/checkSixNum", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> checkSixNum(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String checkSixNum = req.getParameter("sixNum");
		String checkSixNumSessionl = (String) session.getAttribute("regSixNum");
		if (checkSixNum.equals(checkSixNumSessionl)) {
			flag = "true";
		} else {
			flag = "false";
		}
		hashMap.put("status", flag);
		return hashMap;
	}

	@RequestMapping(value = "/findEmail", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> findEmail(HttpServletRequest req,
			HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String findEmail = req.getParameter("email");
		if (!userService.CheckByEmail(findEmail)) { // 邮箱不存在，就发验证码
			Email email = new Email();
			int regSixNum = VAR.getSixRandom();
			session.setAttribute("regSixNum", "" + regSixNum);
			email.toAddress(findEmail, "验证码", "请在一分钟内输入验证码:" + regSixNum
					+ ",来自www.lovelxf.com。");
			email = null;
			flag = "true";
		} else {
			flag = "false";
		}
		findEmail = null;
		hashMap.put("status", flag);
		return hashMap;
	}

	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> findPassword(HttpServletRequest req) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String findPasswordEmail = req.getParameter("email");
		int regSixNum = VAR.getSixRandom();
		String findpassword = MD5Util.getMD5(regSixNum + "e2ATh95jd");
		if (userService.findPassword(findPasswordEmail, findpassword)) { // 邮箱不存在，就发验证码
			Email email = new Email();
			email.toAddress(findPasswordEmail, "新密码", "请保管好您的新密码:" + regSixNum
					+ ",来自www.lovelxf.com。");
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
	public HashMap<String, Object> quit(HttpServletResponse resp,
			HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				c.setMaxAge(0);
				if (c.getName().equals("username")
						|| c.getName().equals("password")) {
					resp.addCookie(c);
				}
			}
		}
		cookies = null;
		session.invalidate();// 清空session
		hashMap.put("status", "true");
		return hashMap;
	}

	public void setCookie(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		if (request.getParameter("autoLogin") != null
				&& request.getParameter("autoLogin").equals("1")) {

			try {
				username = URLEncoder.encode(username, "UTF-8");// 解决中文乱码问题-----无效！！！
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cookie cookie1 = new Cookie("username", username);
			Cookie cookie2 = new Cookie("password", password);
			cookie1.setMaxAge(14 * 24 * 60 * 60);
			cookie2.setMaxAge(14 * 24 * 60 * 60);
			cookie1.setPath("/");// 这样所有路径都可以访问。
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					c.setMaxAge(0);
					if (c.getName().equals("username")
							|| c.getName().equals("password")) {
						response.addCookie(c);
					}
				}
			}
		}
	}
}
