package com.jacobacon.timemanage.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("cEvPlfr1TqVL_D5QYNVRgepSNBlb7URZp4PqS5oM0zo.jPVLF3hkPkrS7ioB4GQqQyNOXoxopB11oSkpMHckIp0");

	}

}
