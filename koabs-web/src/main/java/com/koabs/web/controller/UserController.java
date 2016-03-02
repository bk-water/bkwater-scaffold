package com.koabs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koabs.web.entity.User;
import com.koabs.web.service.UserService;

@RestController
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
