package com.koabs.mybatis.util;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;


import org.apache.ibatis.builder.BuilderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlParser {
	private XPath xpath;   
	private Document document;
	
	public Document getDocument() {
		return document;
	}
	
	public String getSimpleNameSpace() {
		//get root element 
        Element rootElement = document.getDocumentElement(); 
		String[] uris =  rootElement.getAttribute("namespace").split("\\.");
		return uris[uris.length-1];
	}
	
	public XmlParser(InputStream inputSource) {
		  XPathFactory factory = XPathFactory.newInstance();
		  this.xpath = factory.newXPath();
		  this.document = createDocument(inputSource);
	}
	
	public XmlParser(Document doc) {
		 XPathFactory factory = XPathFactory.newInstance();
		  this.xpath = factory.newXPath();
		  this.document = doc;
	}
	private Document createDocument(InputStream inputSource) {
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			builder.setErrorHandler(new ErrorHandler() {
				@Override
				public void error(SAXParseException exception) throws SAXException {
					throw exception;
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					throw exception;
				}

				@Override
				public void warning(SAXParseException exception) throws SAXException {
				}
			});
			return builder.parse(inputSource);
		} catch (Exception e) {
			throw new BuilderException("Error creating document instance.  Cause: " + e, e);
		}
	}

	/**
	 * 将Document 对象转换成String 对象输出.
	 * @return
	 * @throws TransformerException 
	 */
	public String doc2String() throws TransformerException{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(this.document);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();
		return xmlString;
	}
	
	public Document MixStringAndDoc(String xml, Document doc){
		return null;
	}
}
