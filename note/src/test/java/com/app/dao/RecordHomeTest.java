package com.app.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RecordHomeTest extends TestCase {
	ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring-mvc-servlet.xml");
	public void testGetData() {
		//fail("Not yet implemented");
		//System.out.println(context.getBean(RecordHome.class).getData());
	}

}
