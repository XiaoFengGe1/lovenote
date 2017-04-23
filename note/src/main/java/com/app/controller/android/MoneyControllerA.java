package com.app.controller.android;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Money;
import com.app.model.Review;
import com.app.model.User;
import com.app.service.MoneyService;
import com.app.service.ReviewService;
import com.app.service.UserService;

@Controller
@RequestMapping("/android")
public class MoneyControllerA {
	@Resource
	private MoneyService moneyService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/savemoney", method = RequestMethod.POST)
	@ResponseBody
	public String savemoney(@RequestParam("id") String id, @RequestParam("type") String type,@RequestParam("fenlei") String fenlei,@RequestParam("jine") String jine,@RequestParam("beizhu") String beizhu) {
		User user = null;
		user = userService.findByPass(id);
		if(user == null){
			return "false";
		}
		Money money = new Money();
		if(type.equals("收入")){
			money.setInout2(1);
		}else{
			money.setInout2(0);
		}
		money.setInstruction(beizhu);
		money.setType(fenlei);
		money.setUname(user.getName());
		money.setMoney(Float.parseFloat(jine));
		moneyService.save(money);
		return "true";
	}
	

	@RequestMapping(value = "/getmoney", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getmoney( HttpServletRequest request, HttpSession session) {
		String pass =(String) session.getAttribute("lovelxfPass");
		User user = userService.findByPass(pass);
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String id = request.getParameter("id");
		hashMap = moneyService.findMoney(user.getName(), Integer.parseInt(id));
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("data", hashMap);
		return data;
	}
	@RequestMapping(value = "/getmoney2", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getmoney2( HttpServletRequest request, HttpSession session) {
		
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		String id = request.getParameter("id");
		hashMap = moneyService.findMoney("小峰哥", Integer.parseInt(id));
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("data", hashMap);
		return data;
	}
	
}
