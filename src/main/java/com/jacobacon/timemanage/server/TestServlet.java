package com.jacobacon.timemanage.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServlet extends HttpServlet {

	
	private static final long serialVersionUID = -644981243152129035L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		out.println("Hello World!");

		String output = req.getContextPath();

		Logger log = LoggerFactory.getLogger(TestServlet.class);

		log.info("Test");
		log.info(output);

	}

}
