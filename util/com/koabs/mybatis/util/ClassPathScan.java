package com.koabs.mybatis.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class ClassPathScan {
	/**
	 * 获取所有继承了 dao 接口的类.
	 * @param basePackge
	 * @param dao
	 * @return
	 */
	public static Map<String, Object>  getClassMap(String basePackge, String dao){
		Reflections reflections = new Reflections(basePackge);
		Map<String, Object> map = new HashMap<>();
		 Set<?> allClasses = null;
		 
		 try {
			 Class<?> daoInterface  = Class.forName(dao);			
			 allClasses = reflections.getSubTypesOf(daoInterface);
			 System.out.println(allClasses.size());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Iterator<?> iterator = allClasses.iterator();

		while(iterator.hasNext()) {
			Class<?> cls = (Class<?>) iterator.next();
			map.put(cls.getSimpleName(), cls);
		}
		
		return map;
	}
	
	public static void main(String[] args) throws IOException {
		getClassMap("com.koabs", "com.koabs.mybatis.provider.ISqlProvider");
	}
}
