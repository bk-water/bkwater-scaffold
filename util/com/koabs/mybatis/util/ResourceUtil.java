package com.koabs.mybatis.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.TransformerException;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.io.Resources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.support.xml.XmlResultProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ResourceUtil {
	private Map<String, Document> interfaceMap;
	private Resource[] mapperLocations;
	private String basePackge;
	private String dao;
	private String sqlProviderStr;
	
	private Map<String, Object> resourceToDocument(Resource[] mapperLocations) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Resource res: mapperLocations) {
			try {
				
				XmlParser parser = new XmlParser(res.getInputStream());
				map.put(parser.getSimpleNameSpace(), parser.getDocument());
				
			} catch (IOException e) {
				throw new BuilderException("map 获取InputStream 时出错.", e);
			}
		}
		return map;
	}

	public static Resource[] mapToResources(Map<String, Document> map) {
		Resource[] res = {};
		
		return res;
	}
	
	public Map<String, Document> getInterfaceMapper() {
		Map<String, Object> classMap = ClassPathScan.getClassMap(basePackge, dao);
		Map<String, Object> xmlMap = resourceToDocument(mapperLocations);
		classMap.putAll(xmlMap);
		
		XmlBuild xmlBuild = XmlBuild.getInstance(sqlProviderStr, dao);
		for(Map.Entry<String, Object>entry: classMap.entrySet()){
			Object obj = entry.getValue();
			String key = entry.getKey();
			if(obj instanceof Document){
				// 添加 通用Sql Node
				obj = xmlBuild.wrapperDocument((Document) obj);
				classMap.put(key, obj);
			}
			
			if (obj instanceof Class<?>) {
				// 构造通用Mapper
				obj = xmlBuild.wrapperDocument((Class<?>) obj);
				classMap.put(key, obj);
			}
			entry.getKey();
		}
		
		
		return this.interfaceMap;
	}
	
	public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {
		// classpath*:com/koabs/mybatis/xml/*.xml
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("file:/Users/kevin1/Documents/Java_Workspace/KoabsScaffold/target/KoabsScaffold-0.0.1-SNAPSHOT/WEB-INF/classes/com/koabs/mybatis/xml/*.xml");
		XmlParser parser = new XmlParser(res[0].getInputStream());
		
		System.out.println(parser.getSimpleNameSpace());
		// System.out.println(parser.doc2String());
		String XMLString = "<College><Dept>CSE</Dept></College>";
   
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db =dbf.newDocumentBuilder();
        
        Document document = db.parse( new InputSource(   
                new StringReader(XMLString))); 
       
        Element root2=document.getDocumentElement();
        parser.getDocument().getDocumentElement().appendChild( parser.getDocument().importNode(root2, true));
		
        System.out.println(parser.doc2String());
	}
}
