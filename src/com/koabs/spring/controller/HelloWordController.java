package com.koabs.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koabs.spring.pojo.Person;
import com.koabs.spring.util.Log;

@Controller
@RequestMapping("mvc")
public class HelloWordController{
	@RequestMapping("/hello.do")
	public String hello(){
		return "hello";
	}
	
	@RequestMapping("/helloParam.do")
	@ResponseBody
	public String helloParam(String name, String age){
		Log.debug("name", name);
		Log.debug("age", age);
		return "helloParam";
	}
	
	@RequestMapping("/person.do")
	@ResponseBody
	public String person(Person person) {
		Log.debug("person.name", person.getName());
		Log.debug("person.age", person.getAge());
		return "person";
	}
	
	@RequestMapping("/date.do")
	public String date(Date date) {
		Log.debug("Date", date);
		return "date";
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	     binder.registerCustomEditor(Date.class, 
	    		 new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
	             true));
	}
	
	
	
	
	
	

}
