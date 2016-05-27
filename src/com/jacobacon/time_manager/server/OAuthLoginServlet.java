package com.jacobacon.time_manager.server;



import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class OAuthLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.getWriter().println("This URL is only used for handling OAuth2 Callbacks");
		
		//resp.getWriter().println("Parameters: " + req.getParameter("test"));
		
		resp.sendRedirect("/");
		
	}
		
		
		
		
}

