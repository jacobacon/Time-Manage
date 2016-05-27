package com.jacobacon.time_manager.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jacobacon.time_manager.client.LoginServiceOAuth;

public class LoginServiceOAuthImpl extends RemoteServiceServlet implements LoginServiceOAuth {

	private static final String NETWORK_NAME = "G+";
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
	// static UserService userService = UserServiceFactory.getUserService();
	// static User user = userService.getCurrentUser();

	final String clientId = "864208257744-o40h5k5b0d275c7llpgs3sq9vh913vb7.apps.googleusercontent.com";
	final String clientSecret = "7aISZWYkWhXiI_xfTOLuqGzc";
	final String secretState = "secret" + new Random().nextInt(999_999);
	final String callbackUrl = "http://localhost:8888/oauthcallback";
	final OAuth20Service service = new ServiceBuilder().apiKey(clientId).apiSecret(clientSecret).scope("profile")
			.state(secretState).callback(callbackUrl).build(GoogleApi20.instance());

	final Map<String, String> additionalParams = new HashMap<>();

	@Override
	public void login() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public String getAuthUrl() {
		// pass access_type=offline to get refresh token
		// https://developers.google.com/identity/protocols/OAuth2WebServer#preparing-to-start-the-oauth-20-flow
		additionalParams.put("access_type", "offline");
		// force to reget refresh token (if usera are asked not the first time)
		additionalParams.put("prompt", "consent");

		String authorizationUrl = service.getAuthorizationUrl(additionalParams);

		return authorizationUrl;

	}
	
	
}
