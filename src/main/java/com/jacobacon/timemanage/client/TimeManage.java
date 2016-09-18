package com.jacobacon.timemanage.client;

import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.shared.FieldVerifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TimeManage implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a loginService object to communicate with the LoginService Server
	 * Service.
	 */
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	private static final Logger log = LoggerFactory.getLogger(TimeManage.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get("webApp").add(new Label("Hello World"));

		loginService.isLoggedIn(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable result) {
				log.error("Login Service Error: ", "Failed to check if user was logged in.");
				log.error(result.getLocalizedMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				log.info("The user is logged in: " + result.toString());
				if (result == true) {
					RootPanel.get("webApp").add(new Label("Welcome to the App"));
				} else {
					RootPanel.get("webApp").add(new Label("You Are not Logged In Yet!"));
				}
			}

		});

	}

}
