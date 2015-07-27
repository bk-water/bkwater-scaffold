package com.koabs.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koabs.spring.pojo.User;
import com.koabs.spring.service.UserService;

@Controller("user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String save(@RequestBody@ModelAttribute User user){
		userService.save(user);
		return "hello";
	}
}
