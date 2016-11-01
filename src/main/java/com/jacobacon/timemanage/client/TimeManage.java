package com.jacobacon.timemanage.client;

import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.ui.AppView;
import com.jacobacon.timemanage.client.ui.LoginView;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
						AppView.setActiveView(0);
						History.newItem("home");
						break;
					case "home":
						AppView.setActiveView(0);
						break;
					case "timelog":
						AppView.setActiveView(1);
						break;
					case "reports":
						AppView.setActiveView(2);
						break;
					case "settings":
						AppView.setActiveView(3);
						break;
					case "admin":
						AppView.setActiveView(4);
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
		RootPanel.get("webApp").add(new AppView());
		AppView.setActiveView(0);
	}

	// Shows login page.
	public static void showLogin() {
		RootPanel.get("webApp").clear();

		Window.setTitle("Login Page");
		RootPanel.get("webApp").add(new LoginView());

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
