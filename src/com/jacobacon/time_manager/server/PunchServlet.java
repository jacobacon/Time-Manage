package com.jacobacon.time_manager.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class PunchServlet extends HttpServlet {
	private static final transient Logger log = LoggerFactory.getLogger(PunchServlet.class);

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

	/*
	 * public void doGet(HttpServletRequest req, HttpServletResponse resp)
	 * throws IOException {
	 * 
	 * 
	 * //resp.getWriter().println("<html>This is a Webpage</html>");
	 * 
	 * 
	 * Factory<SecurityManager> factory = new IniSecurityManagerFactory();
	 * SecurityManager securityManager = factory.getInstance();
	 * 
	 * SecurityUtils.setSecurityManager(securityManager); Subject currentUser =
	 * SecurityUtils.getSubject();
	 * 
	 * Session session = currentUser.getSession();
	 * session.setAttribute("someKey", "aValue");
	 * 
	 * String value = (String) session.getAttribute("someKey"); if
	 * (value.equals("aValue")) { //log.info("Retrieved the correct value");
	 * System.out.println("Moo"); }
	 * 
	 * if (!currentUser.isAuthenticated()) {
	 * 
	 * 
	 * UsernamePasswordToken token = new UsernamePasswordToken("lonestarr",
	 * "vespa"); //UsernamePasswordToken token = new
	 * UsernamePasswordToken(username, password); token.setRememberMe(true); try
	 * { currentUser.login(token); } catch (UnknownAccountException uae) {
	 * System.out.println("There is no user with username of " +
	 * token.getPrincipal()); } catch (IncorrectCredentialsException ice) {
	 * System.out.println("Password for account " + token.getPrincipal() +
	 * " was incorrect!"); } catch (LockedAccountException lae) {
	 * System.out.println("The account for username " + token.getPrincipal() +
	 * " is locked.  " + "Please contact your administrator to unlock it."); }
	 * // ... catch more exceptions here (maybe custom ones specific to // your
	 * application? catch (AuthenticationException ae) { // unexpected
	 * condition? error? } }
	 * 
	 * // say who they are: // print their identifying principal (in this case,
	 * a username): System.out.println("User [" + currentUser.getPrincipal() +
	 * "] logged in successfully.");
	 * 
	 * // test a role: if (currentUser.hasRole("schwartz")) {
	 * System.out.println("May the Schwartz be with you!");
	 * resp.getWriter().println("<html>Welcome to the webpage!</html>");
	 * 
	 * } else { System.out.println("Hello, mere mortal."); }
	 * 
	 * // test a typed permission (not instance-level) if
	 * (currentUser.isPermitted("lightsaber:weild")) { System.out.println(
	 * "You may use a lightsaber ring.  Use it wisely."); } else {
	 * System.out.println(
	 * "Sorry, lightsaber rings are for schwartz masters only."); }
	 * 
	 * // a (very powerful) Instance Level permission: if
	 * (currentUser.isPermitted("winnebago:drive:eagle5")) { System.out.println(
	 * "You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  "
	 * + "Here are the keys - have fun!"); } else { System.out.println(
	 * "Sorry, you aren't allowed to drive the 'eagle5' winnebago!"); }
	 * 
	 * // all done - log out! //currentUser.logout();
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		out.println("Hello World");

		ShiroWebModule.guiceFilterModule();

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
		// token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (AuthenticationException ae) {
			out.println(ae);
		}

		if (subject.isAuthenticated())
			log.info("You are logged in!");

		subject.logout();
		
		if(subject.hasRole("schwartz"))
			log.info("You have the schwartz");

		if (!subject.isAuthenticated())
			log.info("You are logged out.");

	}
}
