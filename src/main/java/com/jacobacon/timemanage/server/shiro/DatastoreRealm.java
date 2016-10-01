package com.jacobacon.timemanage.server.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatastoreRealm extends AuthorizingRealm {

	private static Logger log = LoggerFactory.getLogger(DatastoreRealm.class);

	public DatastoreRealm() {
		super(new MemcacheManager(), theCredentials());
		log.trace("Created instance of objectify realm for shiro.");

	}
	
	private UserDAO dao(){
		return new UserDAO();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = ((UsernamePasswordToken) token).getUsername();
		return doGetAuthenticationInfo(username);
	}

	private AuthenticationInfo doGetAuthenticationInfo(String userName) throws AuthenticationException {
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	
	private static CredentialsMatcher theCredentials(){
		HashedCredentialsMatcher credentials = new HashedCredentialsMatcher(User.HASH_ALGORITHM);
		credentials.setHashIterations(User.HASH_ITERATIONS);
		credentials.setStoredCredentialsHexEncoded(true);
		return credentials;
	}
	
	

}
