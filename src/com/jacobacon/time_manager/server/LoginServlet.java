package com.jacobacon.time_manager.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static UserService userService = UserServiceFactory.getUserService();
	static User user = userService.getCurrentUser();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		

		String thisUrl = req.getRequestURI();

		resp.setContentType("text/html");
		if (req.getUserPrincipal() != null) {
			resp.getWriter().println("<p>Hello, " + req.getUserPrincipal().getName() + "!  You can <a href=\""
					+ userService.createLogoutURL(thisUrl) + "\">sign out</a>.</p>");
		} else {
			resp.getWriter()
					.println("<p>Please <a href=\"" + userService.createLoginURL("/") + "\">sign in</a>.</p>");
		}
	}
}
