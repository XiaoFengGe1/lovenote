package com.app.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NoteHomeTest extends TestCase {
	ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring-mvc-servlet.xml");
	//NoteHome noteHome = new NoteHome();
	public void testFindMain() {
		//Assert.assertEquals("no", context.getBean(NoteHome.class).findMain(new String[]{"java","ecl"},10));
	}

}
