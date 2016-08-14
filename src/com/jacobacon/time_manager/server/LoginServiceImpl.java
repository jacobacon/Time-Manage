package com.jacobacon.time_manager.server;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jacobacon.time_manager.client.service.LoginService;
import com.jacobacon.time_manager.shared.User;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		User user = new User();
		return user;
	}

	@Override
	public String login(String username, String password) {
		System.out.println("Login called");
		
		Factory<SecurityManager> factory = new IniSecurityManagerFactory();
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();

		Session session = currentUser.getSession();

		if (!currentUser.isAuthenticated()) {

			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			// UsernamePasswordToken token = new UsernamePasswordToken(username,
			// password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				System.out.println("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				System.out.println("The account for username " + token.getPrincipal() + " is locked.  "
						+ "Please contact your administrator to unlock it.");
			}
			// ... catch more exceptions here (maybe custom ones specific to
			// your application?
			catch (AuthenticationException ae) {
				// unexpected condition? error?
			}
		}

		return "Your username is: " + username + " and Password is: " + password;

	}

}
