package com.app.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.MoneyHome;
import com.app.model.Money;

@Service
public class MoneyService {

	@Resource
	MoneyHome moneyHome;
	
	public void save(Money money){
		moneyHome.save(money);
	}
	
	public void delete(Integer id){
		moneyHome.delete(id);
	}
	
	public void update(Money money){
		moneyHome.update(money);
	}
	
	public Money findById(Integer id){
		return moneyHome.findById(id);
	}
	public  HashMap<String, Object> findMoney(String uname,int pageNum){
		return moneyHome.findMoney(uname,pageNum);
	}
}	