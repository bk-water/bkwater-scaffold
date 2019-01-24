package com.koabs.web.service;

import com.koabs.common.logging.Log;
import com.koabs.common.logging.LogFactory;
import com.koabs.web.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	Log log = LogFactory.getInstance();
	
	public UserService() {
		log.debug("Init UserService.");
	}
	
	public void save(User user) {
		log.debug("Save UserService:" + user.toString());
	}
}
