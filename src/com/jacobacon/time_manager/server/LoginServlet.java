package com.jacobacon.time_manager.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		UserService userService = UserServiceFactory.getUserService();

		String thisURL = req.getRequestURI();

		resp.setContentType("text/html");
		if (req.getUserPrincipal() != null) {
			resp.getWriter().println("<p>Hello, " + req.getUserPrincipal().getName() + "! You can <a href=\""
					+ userService.createLogoutURL(thisURL) + "\">sign out</a>.</p>");

		}

		else {
			resp.getWriter()
					.println("<p>Please <a href=\"" + userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");

		}
		
		
		ObjectifyService.register(Car.class);

		
		Car porsche = new Car("2FAST", "Red");
		com.googlecode.objectify.ObjectifyService.ofy().save().entity(porsche).now();
		
		Car fetched = ofy().load().type(Car.class).id(porsche.id).now();
		
		resp.getWriter().println("<p> The color of the Car is: " + fetched.color + "</p>");
		
		
		

	}
	@Entity
	public class Car
	{
		@Id Long id;
		@Index String license;
		String color;
		Car(String license, String color){
			this.license = license;
			this.color = color;
		}
	}
	public static String getName() {
		return "Test";
	}
}
