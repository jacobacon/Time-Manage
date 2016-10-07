package com.jacobacon.timemanage.server.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;



public class DatastoreRealm extends AuthorizingRealm {

	private static Logger log = LoggerFactory.getLogger(DatastoreRealm.class);

	public DatastoreRealm() {
		super(new MemcacheManager(), theCredentials());
		log.trace("Created instance of objectify realm for shiro.");

	}

	private UserDAO dao() {
		return new UserDAO();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = ((UsernamePasswordToken) token).getUsername();
		return doGetAuthenticationInfo(username);
	}

	private AuthenticationInfo doGetAuthenticationInfo(String username) throws AuthenticationException {

		Preconditions.checkNotNull(username, "User name can't be null");

		log.info("Finding auth info for " + username + "in the Datastore");

		User user = dao().findUser(username);

		if (user == null) {
			log.info("User: " + user.getUserName() + " is rejected");
			return null;
		}

		log.info("Found " + user.getUserName() + "in the Datastore");

		SimpleAccount account = new SimpleAccount(user.getUserName(), user.getPasswordHash(),
				new SimpleByteSource(user.getSalt()), getName());

		account.setRoles(user.getRoles());

		account.setStringPermissions(user.getPermissions());

		return account;

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Preconditions.checkNotNull(principals, "You can't have a null collection of principals");
		String userName = (String) getAvailablePrincipal(principals);
		if (userName == null) {
			throw new NullPointerException("Can't find a principal in the collection");
		}
		log.info("Finding authorization info for " + userName + " in DB");
		User user = dao().findUser(userName);
		if (user == null) {
			return null;
		}
		log.info("Found " + userName + " in DB");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(user.getRoles());
		info.setStringPermissions(user.getPermissions());
		return info;
	}

	private static CredentialsMatcher theCredentials() {
		HashedCredentialsMatcher credentials = new HashedCredentialsMatcher(User.HASH_ALGORITHM);
		credentials.setHashIterations(User.HASH_ITERATIONS);
		credentials.setStoredCredentialsHexEncoded(true);
		return credentials;
	}

}
