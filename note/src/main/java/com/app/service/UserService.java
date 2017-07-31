package com.app.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.UserHome;
import com.app.model.User;

@Service
public class UserService {

	@Resource
	UserHome userHome;

	public void save(User user) {
		userHome.save(user);
	}

	public void delete(Integer id) {
		userHome.delete(id);
	}

	public void update(User user) {
		userHome.update(user);
	}

	public User findById(Integer id) {
		return userHome.findById(id);
	}

	public User findByName(String name) {
		return userHome.findByName(name);
	}

	public User findByPass(String pass) {
		return userHome.findByPass(pass);
	}

	public User findByEmail(String email) {
		return userHome.CheckByEmail(email);
	}

	public Boolean CheckByEmail(String email) {
		if (userHome.CheckByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean CheckByName(String name) {
		if (userHome.CheckByName(name) == null) {
			return false;
		} else {
			return true;
		}
	}

	public String login(String account) {
		return userHome.login(account);
	}

}