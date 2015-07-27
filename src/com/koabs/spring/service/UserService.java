package com.koabs.spring.service;

import org.springframework.stereotype.Component;

import com.koabs.spring.pojo.User;
import com.koabs.spring.util.Log;

@Component
public class UserService {
	public UserService() {
		Log.debug("UserServer", "Init UserService.");
	}
	
	public void save(User user) {
		Log.debug("UserService", "Save UserService:" + user.toString());
	}
}
