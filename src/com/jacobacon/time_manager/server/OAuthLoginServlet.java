package com.jacobacon.time_manager.server;



import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.Cookies;
import com.jacobacon.time_manager.client.Time_Manager;


public class OAuthLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.getWriter().println("This URL is only used for handling OAuth2 Callbacks");
		
		//resp.getWriter().println("Parameters: " + req.getParameter("test"));
		
		String authCode = req.getParameter("code");
		String state = req.getParameter("state");
		
		Time_Manager.setAuthValue(authCode, state);
		
		Time_Manager.loginStatus = true;
		
		
		resp.sendRedirect("/");
		
	}
		
		
		
		
}

