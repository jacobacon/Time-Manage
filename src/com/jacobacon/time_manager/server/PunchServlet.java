package com.jacobacon.time_manager.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@SuppressWarnings("serial")
public class PunchServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");

		Date date = new Date();

		Car porsche = new Car("ZOOM", "Yellow", date);

		savePunch(porsche);
		resp.getWriter().println("The Color of the car is yo: " + getPunch(porsche));
		resp.getWriter().println("The date the car was made was: " + dateFormat.format(getPunchDate(porsche)));
		resp.getWriter().println(req.getParameter("key1"));
		resp.getWriter().println(req.getParameter("key2"));
		resp.getWriter().println(req.getParameter("key3"));

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.getWriter().println("<html>This is a Webpage</html>");

		// doPost(req, resp);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd h:mm:ss a");

		Date date = new Date();
		resp.getWriter().println(dateFormat.format(date));

	}

	@Entity
	public class Car {
		@Id
		Long id;
		@Index
		String license;
		String color;
		Date date;

		Car(String name, String color, Date date) {
			this.license = name;
			this.color = color;
			this.date = date;
		}
	}

	public void savePunch(Car car) {
		ObjectifyService.register(Car.class);

		com.googlecode.objectify.ObjectifyService.ofy().save().entity(car).now();

	}

	public String getPunch(Car car) {
		Car fetched = ofy().load().type(Car.class).id(car.id).now();

		String color = fetched.color;

		return color;
	}

	public Date getPunchDate(Car car) {
		Car fetched = ofy().load().type(Car.class).id(car.id).now();
		return fetched.date;
	}

}
