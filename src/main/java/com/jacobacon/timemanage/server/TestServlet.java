package com.jacobacon.timemanage.server;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.client.Window;

public class TestServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		out.println("Hello World!");
		
		String output = req.getContextPath();
		
		
		Logger log = LoggerFactory.getLogger(TestServlet.class);
		
		log.info("Test");
		log.info(output);
		
	}
	

}
