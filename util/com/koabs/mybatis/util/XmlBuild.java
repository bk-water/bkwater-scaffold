package com.koabs.mybatis.util;

import org.w3c.dom.Document;


public class XmlBuild {
	private String baseDao;
	private String sqlProviderStr;
	private static XmlBuild xmlBuild;

	private XmlBuild() {
		super();
	}

	public static XmlBuild getInstance(String sqlProviderStr, String dao) {
		
		if (xmlBuild == null) {
			xmlBuild = new XmlBuild();
			xmlBuild.sqlProviderStr = sqlProviderStr;
			xmlBuild.baseDao = dao;
		}
		return xmlBuild;
	}

	public Document wrapperDocument(Document doc) {
		// 给 doc 插入生成的通用mapper 操作节点.
		XmlParser parse = new XmlParser(doc);
		// 获取DAO 类里面的泛型
		// 根据泛型类型生成Sql语句
		return null;
	}

	public Document wrapperDocument(Class<?> cls) {
		// Interface 没有对应的xml时 生成一个新的mapper.xml 文件给Mybatis 去解析.
		// 生成的xml 中会包含根据 Class 生成的通用Mapper 操作语句.
		
		return null;
	}

}
