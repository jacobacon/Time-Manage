package com.jacobacon.time_manager.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.oauth.OAuthService;
import com.google.appengine.api.oauth.OAuthServiceFactory;
import com.google.appengine.api.oauth.OAuthServiceFailureException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


import com.google.gwt.dev.util.collect.HashSet;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(LoginServlet.class.getCanonicalName());

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// UserService userService = UserServiceFactory.getUserService();

		// String thisURL = req.getRequestURI();

		/*
		 * resp.setContentType("text/html"); if (req.getUserPrincipal() != null)
		 * { resp.getWriter().println("<p>Hello, " +
		 * req.getUserPrincipal().getName() + "! You can <a href=\"" +
		 * userService.createLogoutURL(thisURL) + "\">sign out</a>.</p>");
		 * 
		 * }
		 * 
		 * else { resp.getWriter() .println("<p>Please <a href=\"" +
		 * userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
		 * 
		 * }
		 */

		// System.out.println(req.getParameter("nameListElement"));
		// System.out.println(req.getParameter("hiddenPunchIn"));
		// System.out.println(req.getParameter("hiddenPunchOut"));
		// System.out.println(req.getParameter("test"));

		// System.out.println(req.getReader().readLine());

		// Enumeration<String> names = req.getParameterNames();

		// while (names.hasMoreElements()) {
		// String paramName = names.nextElement();
		// System.out.println(paramName);

		// }

		// ObjectifyService.register(Car.class);

		// Car porsche = new Car("2FAST", "Red");
		// com.googlecode.objectify.ObjectifyService.ofy().save().entity(porsche).now();

		// Car fetched = ofy().load().type(Car.class).id(porsche.id).now();

		// resp.getWriter().println("<p> The color of the Car is: " +
		// fetched.color + "</p>");

		// resp.getWriter().println();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().write("<html>The Page You Requested is Not Valid</html>");
		
	};
	

}
