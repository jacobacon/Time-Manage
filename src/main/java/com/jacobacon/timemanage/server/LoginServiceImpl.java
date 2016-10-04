package com.jacobacon.timemanage.server;

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

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 1284756406544822869L;

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	private static final UserDAO userDAO = new UserDAO();

	private Subject currentUser;

	public LoginServiceImpl() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory();
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

	}

	@Override
	public Boolean isLoggedIn() {
		currentUser = SecurityUtils.getSubject();

		if (currentUser.isAuthenticated()) {
			log.info("The user is logged in.");
			return true;
		} else {
			log.info("The user is logged out.");
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
				log.info("User (" + currentUser.getPrincipal().toString() + ") is logged in.");
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
		log.info("User is logged out.");
	}

	@Override
	public void register(String username, String password, Set<String> roles, Set<String> permissions) {
		userDAO.saveUser(new User(username, password, roles, permissions), true);

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

}
