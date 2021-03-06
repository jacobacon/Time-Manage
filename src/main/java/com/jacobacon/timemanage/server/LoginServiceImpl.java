package com.jacobacon.timemanage.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.server.shiro.User;
import com.jacobacon.timemanage.server.shiro.UserDAO;
import com.jacobacon.timemanage.shared.UserData;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 1284756406544822869L;

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	private static final UserDAO userDAO = new UserDAO();

	private Subject currentUser;

	// private String ipAddress = getThreadLocalRequest().getRemoteAddr();
	String ipAddress = "000.000.000.123";

	public LoginServiceImpl() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory();
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

	}

	@Override
	public Boolean isLoggedIn() {
		currentUser = SecurityUtils.getSubject();

		if (currentUser.isAuthenticated()) {
			log.debug("The user is logged in.");
			return true;
		} else {
			log.debug("The user is logged out.");
			return false;
		}
	}

	@Override
	public Boolean tryLogIn(String username, String password, Boolean rememberMe) {

		currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(rememberMe);

			try {
				currentUser.login(token);
				log.debug("User (" + currentUser.getPrincipal().toString() + ") is logged in.");
				return true;
			} catch (UnknownAccountException uae) {
				log.error(uae.getLocalizedMessage());
			} catch (IncorrectCredentialsException ice) {
				log.error(ice.getLocalizedMessage());
			} catch (LockedAccountException lae) {
				log.error(lae.getLocalizedMessage());

			} catch (AuthenticationException ae) {
				log.error(ae.getLocalizedMessage());
			}

		}
		log.error("User Couldn't Log In.");
		// For some reason you couldn't log in.
		return false;
	}

	@Override
	public void logout() {
		currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		log.debug("User is logged out.");
	}

	@Override
	public String register(String username, String password, String name, Set<String> roles, Set<String> permissions) {
		User tempUser = userDAO.findUser(username);

		if (tempUser != null) {
			log.info("User is already found.");
			return "User is already found";
		} else {
			userDAO.saveUser(new User(username, password, name, roles, permissions), true);
			return "created";
		}

	}

	@Override
	public Long test() {
		return userDAO.getCount();

	}

	@Override
	public Boolean checkRole(String role) {
		currentUser = SecurityUtils.getSubject();
		if (currentUser.hasRole(role)) {
			return true;
		} else
			return false;

	}

	@Override
	public Boolean checkPermission(String permission) {
		currentUser = SecurityUtils.getSubject();
		if (currentUser.isPermitted(permission)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> getUserList() {
		ArrayList<String> users = new ArrayList<String>(userDAO.getUserList());

		return users;
	}

	@Override
	public UserData getUserData() {
		currentUser = SecurityUtils.getSubject();
		UserData userData;
		User user = userDAO.findUser(currentUser.getPrincipal().toString());
		if ((user != null) && (!currentUser.equals("jacob"))) {
			userData = new UserData(user.getName(), user.getUserName(), ipAddress);
			log.debug("Created userdata with info: username: " + user.getUserName() + "Name: " + user.getName());
			return userData;
		} else {
			log.debug("Couldn't Find a valid user... making default UserData");
			userData = new UserData("Testy McTestFace", "Username", ipAddress);
			return userData;
		}
	}
}
