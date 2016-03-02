package com.koabs.web.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.koabs.entity.BaseEntity;

public class User extends BaseEntity{

	private Integer id;

	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}

}
