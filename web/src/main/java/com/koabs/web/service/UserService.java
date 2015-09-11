package com.koabs.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.koabs.common.logging.Log;
import com.koabs.common.logging.LogFactory;
import com.koabs.service.BaseService;
import com.koabs.web.dao.IUserDao;
import com.koabs.web.entity.User;

@Component
public class UserService extends BaseService<User, Integer>{
	
	@Autowired
	IUserDao userDao;
	
	Log log = LogFactory.getInstance();
	
	public UserService() {
		log.debug("Init UserService.");
	}
	
	public void save(User user) {
		log.debug("Save UserService:" + user.toString());
	}

	@Override
	protected void setBaseDao() {
		super.baseDao = this.userDao;
	}
}
