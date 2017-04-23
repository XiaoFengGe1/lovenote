package com.app.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.NoteHome;
import com.app.model.Note;

@Service
public class NoteService {

	@Resource
	NoteHome noteHome;
	
	public void save(Note note){
		noteHome.save(note);
	}
	
	public void delete(Integer id){
		noteHome.delete(id);
	}
	
	public void update(Note note){
		noteHome.update(note);
	}
	
	public Note findById(Integer id){
		return noteHome.findById(id);
	}
	
	public Boolean fixType(String typeName,String uname){
		try {
			noteHome.fixType(typeName, uname);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean checkTitle(String name,String title){
			if( noteHome.checkTitle(name, title) == 0){
				return false;
			}else{
				return true;
			}
	}
	
	public  HashMap<String, Object> findMain(String[] keys,int pageNum){
		return noteHome.findMain(keys, pageNum);
	}
	
	public  HashMap<String, Object> findMainBlock(int pageNum){
		return noteHome.findMainBlock(pageNum);
	}
	
	public  HashMap<String, Object> findMainTool(String[] keys,int pageNum,HashMap hashmap){ 
		return noteHome.findMainTool(keys, pageNum, hashmap);
		
	}
	
	public  HashMap<String, Object> findMainToolBlock(int pageNum,HashMap hashmap){
		return noteHome.findMainToolBlock(pageNum, hashmap);
	}
}	