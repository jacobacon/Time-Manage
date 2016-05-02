package com.jacobacon.time_manager.server;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

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
