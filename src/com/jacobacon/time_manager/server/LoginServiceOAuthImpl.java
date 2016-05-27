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

public class LoginServiceOAuthImpl extends RemoteServiceServlet implements LoginServiceOAuth{
	
	
	private static final String NETWORK_NAME = "G+";
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
	//static UserService userService = UserServiceFactory.getUserService();
	//static User user = userService.getCurrentUser();
	
	
	final String clientId = "864208257744-kqvqlki3bp32ocsr494uard5gp23hnhl.apps.googleusercontent.com";
	final String clientSecret = "rykXuQJ5JurthOmnYtYNB_8X";
	final String secretState = "secret" + new Random().nextInt(999_999);
	final OAuth20Service service = new ServiceBuilder().apiKey(clientId).apiSecret(clientSecret).scope("profile").state(secretState).callback("http://example.com/callback").build(GoogleApi20.instance());
	final Scanner in = new Scanner(System.in, "UTF-8");
	

	@Override
	public void login() {
		// TODO Auto-generated method stub
		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();

		// Obtain the Authorization URL
		System.out.println("Fetching the Authorization URL...");
		// pass access_type=offline to get refresh token
		// https://developers.google.com/identity/protocols/OAuth2WebServer#preparing-to-start-the-oauth-20-flow
		final Map<String, String> additionalParams = new HashMap<>();
		additionalParams.put("access_type", "offline");
		// force to reget refresh token (if usera are asked not the first time)
		additionalParams.put("prompt", "consent");
		final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize ScribeJava here:");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print(">>");
		final String code = in.nextLine();
		System.out.println();

		
	}

}
