package com.app.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ReviewHome;
import com.app.model.Review;

@Service
public class ReviewService {

	@Resource
	ReviewHome reviewHome;
	
	public void save(Review review){
		reviewHome.save(review);
	}
	
	public void delete(Integer id){
		reviewHome.delete(id);
	}
	
	public void update(Review review){
		reviewHome.update(review);
	}
	
	public Review findById(Integer id){
		return reviewHome.findById(id);
	}
	
	public  HashMap<String, Object> findReview(int noteid,int pageNum){
		return reviewHome.findReview(noteid, pageNum);
	}
}	