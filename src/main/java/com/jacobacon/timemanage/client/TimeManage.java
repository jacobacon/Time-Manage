package com.jacobacon.timemanage.client;

import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

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
	private final static LoginServiceAsync loginService = GWT.create(LoginService.class);

	private static final Logger log = LoggerFactory.getLogger(TimeManage.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		
		 History.addValueChangeHandler(new ValueChangeHandler<String>() {
		      public void onValueChange(ValueChangeEvent<String> event) {
		        String historyToken = event.getValue();

		        // Parse the history token
		        try {
		          if (historyToken.substring(0, 5).equals("login")) {
		            RootPanel.get().add(new Label("Login Page"));
		          } else {
		            RootPanel.get("webApp").add(new Label("You are at the home page."));
		          }

		        } catch (IndexOutOfBoundsException e) {
		          RootPanel.get().add(new Label("Home Page"));
		        }
		      }
		    });

		Window.setTitle("Loading App...");

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
					History.newItem("home");
					RootPanel.get("webApp").add(new Label("Welcome to the App"));
					RootPanel.get("webApp").add(new Button("Click Me", new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent arg0) {
							loginService.logout(new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable arg0) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(Void arg0) {
									Window.Location.reload();
									Notify.notify("Logged out successfully");
									
								}
							});
							
						}
					}));
				} else {
					History.newItem("login");
					RootPanel.get("webApp").add(new Login());
					Window.setTitle("Login Page");
				}
			}

		});

	}

	// Used to pass user login status to the Client GUI
	private Boolean loginStatus = false;

	public Boolean userLoginCheck() {
		loginService.isLoggedIn(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				loginStatus = result;
			}

			@Override
			public void onFailure(Throwable arg0) {
				loginStatus = false;
				log.error(arg0.getLocalizedMessage());
			}

		});

		return loginStatus;
	}
}
