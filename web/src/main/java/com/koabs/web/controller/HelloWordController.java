package com.koabs.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koabs.common.logging.Log;
import com.koabs.common.logging.LogFactory;
import com.koabs.web.entity.Person;

@Controller
@RequestMapping("mvc")
public class HelloWordController{
	Log log = LogFactory.getInstance();
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@RequestMapping("/helloParam")
	@ResponseBody
	public String helloParam(String name, String age){
		log.debug("name："+name);
		log.debug("age: "+ age);
		return "helloParam";
	}
	
	@RequestMapping("/person")
	@ResponseBody
	public String person(Person person) {
		log.debug("person.name:"+ person.getName());
		log.debug("person.age:"+ person.getAge());
		return "person";
	}
	
	@RequestMapping("/date")
	public String date(Date date) {
		log.debug("Date:" + date);
		return "date";
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	     binder.registerCustomEditor(Date.class, 
	    		 new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
	             true));
	}
	
	
	
	
	
	

}
