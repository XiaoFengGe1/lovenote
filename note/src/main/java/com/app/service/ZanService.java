package com.app.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ZanHome;
import com.app.model.Zan;

@Service
public class ZanService {

	@Resource
	ZanHome zanHome;
	
	public void save(Zan zan){
		zanHome.save(zan);
	}
	
	public void delete(Integer id){
		zanHome.delete(id);
	}
	
	public void update(Zan zan){
		zanHome.update(zan);
	}
	
	public Zan findById(Integer id){
		return zanHome.findById(id);
	}
	
	public Boolean isExist (String name,int noteid){
		return zanHome.isExist(name, noteid);
	}
}	