package com.app.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.RelationHome;
import com.app.model.Relation;

@Service
public class RelationService {

	@Resource
	RelationHome relationHome;
	
	public void save(Relation relation){
		relationHome.save(relation);
	}
	
	public void delete(Integer id){
		relationHome.delete(id);
	}
	
	public void update(Relation relation){
		relationHome.update(relation);
	}
	
	public Relation findById(Integer id){
		return relationHome.findById(id);
	}
}	