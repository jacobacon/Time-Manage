package com.jacobacon.time_manager.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Random;
import java.util.Scanner;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String NETWORK_NAME = "G+";
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
	//static UserService userService = UserServiceFactory.getUserService();
	//static User user = userService.getCurrentUser();
	
	
	final String clientId = "864208257744-kqvqlki3bp32ocsr494uard5gp23hnhl.apps.googleusercontent.com";
	final String clientSecret = "rykXuQJ5JurthOmnYtYNB_8X";
	final String secretState = "secret" + new Random().nextInt(999_999);
	final OAuth20Service service = new ServiceBuilder().apiKey(clientId).apiSecret(clientSecret).scope("profile").state(secretState).callback("http://example.com/callback").build(GoogleApi20.instance());
	final Scanner in = new Scanner(System.in, "UTF-8");

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		/*String thisUrl = req.getRequestURI();

		resp.setContentType("text/html");
		if (req.getUserPrincipal() != null) {
			resp.getWriter().println("<p>Hello, " + req.getUserPrincipal().getName() + "!  You can <a href=\""
					+ userService.createLogoutURL(thisUrl) + "\">sign out</a>.</p>");
		} else {
			resp.getWriter().println("<p>Please <a href=\"" + userService.createLoginURL("/") + "\">sign in</a>.</p>");
		}*/
		
		
		
		resp.getWriter().println("Hello");
		
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

		System.out.println("And paste the state from server here. We have set 'secretState'='" + secretState + "'.");
		System.out.print(">>");
		final String value = in.nextLine();
		if (secretState.equals(value)) {
			System.out.println("State value does match!");
		} else {
			System.out.println("Ooops, state value does not match!");
			System.out.println("Expected = " + secretState);
			System.out.println("Got      = " + value);
			System.out.println();
		}

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		OAuth2AccessToken accessToken = service.getAccessToken(code);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + ", 'rawResponse'='"
				+ accessToken.getRawResponse() + "')");

		System.out.println("Refreshing the Access Token...");
		accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
		System.out.println("Refreshed the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + ", 'rawResponse'='"
				+ accessToken.getRawResponse() + "')");
		System.out.println();
		
		

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		while (true) {
			System.out.println("Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
			System.out.print(">>");
			final String query = in.nextLine();
			System.out.println();

			final String requestUrl;
			if ("exit".equals(query)) {
				break;
			} else if (query == null || query.isEmpty()) {
				requestUrl = PROTECTED_RESOURCE_URL;
			} else {
				requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
			}

			final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			final Response response = request.send();
			System.out.println();
			System.out.println(response.getCode());
			System.out.println(response.getBody());
			
			

			System.out.println();
			
			
		}
		
	}
		
		
		
		
}

