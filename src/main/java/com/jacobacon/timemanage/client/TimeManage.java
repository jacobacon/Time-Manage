package com.jacobacon.timemanage.client;

import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.ui.Home;
import com.jacobacon.timemanage.client.ui.Login;

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
	 * Create a loginService object to communicate with the LoginService Server
	 * Service.
	 */
	private final static LoginServiceAsync loginService = GWT.create(LoginService.class);

	private static final Logger log = LoggerFactory.getLogger(TimeManage.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

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
					showApp();
				} else {
					History.newItem("login");
					showLogin();
				}
			}

		});

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				if (userLoginCheck()) {
					switch (historyToken) {
					case "login":
						Window.alert("You are already logged in.");
						showApp();
						History.newItem("home");
						break;
					case "home":
						Window.alert("You are logged in, going home.");
						showApp();
						break;
					case "timelog":
						Window.alert("Going to the timelog page.");
						showApp();
						break;
					case "reports":
						Window.alert("Showing Report View");
						showApp();
						break;
					case "admin":
						Window.alert("Showing Admin Page");
						showApp();
						break;
					case "logout":
						loginService.logout(new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub
								Window.alert("You Couldn't Log Out. Try Again.");
							}

							@Override
							public void onSuccess(Void result) {
								showLogin();
								Notify.notify("Logged Out Successfully");

							}
						});

					}
				}

			}
		});

	}

	// Shows the app after user logs in.
	public static void showApp() {
		RootPanel.get("webApp").clear();

		Window.setTitle("Home");
		RootPanel.get("webApp").add(new Home());
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
	}

	// Shows login page.
	public static void showLogin() {
		RootPanel.get("webApp").clear();

		Window.setTitle("Login Page");
		RootPanel.get("webApp").add(new Login());

	}

	// Used to pass user login status to the Client GUI
	private Boolean loginStatusCheck = false;

	public Boolean userLoginCheck() {
		loginService.isLoggedIn(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				loginStatusCheck = result;
			}

			@Override
			public void onFailure(Throwable arg0) {
				loginStatusCheck = false;
				log.error(arg0.getLocalizedMessage());
			}

		});

		return loginStatusCheck;
	}
}
