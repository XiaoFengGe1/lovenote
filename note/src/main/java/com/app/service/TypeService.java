package com.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.TypeHome;
import com.app.model.Type;

@Service
public class TypeService {

	@Resource
	TypeHome typeHome;
	
	public void save(Type type){
		typeHome.save(type);
	}
	
	public void delete(Integer id){
		typeHome.delete(id);
	}
	
	public void update(Type type){
		typeHome.update(type);
	}
	
	public Type findById(Integer id){
		return typeHome.findById(id);
	}
	
	public List<Type> findByName(String name) {
		return typeHome.findByName(name);
	}
	
	public Boolean isHas(String classify,String name){
		return typeHome.isHas(classify, name);
	}
}	