package com.jacobacon.time_manager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {

	/**
	 * This is the entry point method.
	 */

	final MainView mainView = new MainView();
	final ManualPunchView manualPunchView = new ManualPunchView();
	final ReportView reportView = new ReportView();

	// Setup Login
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please Sign in to your Google Account to Access this Application");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	final String url = "punch";

	public void onModuleLoad() {

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					showApp();
				} else {
					loadLogin();
				}

			}

		});

	}

	public void setContent(int viewNumber) {
		switch (viewNumber) {
		case 0:
			System.out.println("Switching to the main view!");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(mainView.getMainPanel());
			Window.setTitle("Home");
			break;

		case 1:
			System.out.println("Switching to the 2nd view!");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(manualPunchView.getMainPanel());
			Window.setTitle("Edit Time");
			break;

		case 2:
			System.out.println("Switching to the 3rd view");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(reportView.getMainPanel());
			Window.setTitle("Reports");
			break;

		}

	}

	public void postData(String url, RequestBuilder builder, String data) {
		try {
			@SuppressWarnings("unused")
			Request request = builder.sendRequest(data, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					Window.alert(response.getText());

				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub

				}

			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadLogin() {
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);
	}

	public void showApp() {

		// Create RequestBuilder for POST

		final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "punch");
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		// String for the RequestBuilder that holds the request data.
		@SuppressWarnings("unused")
		final String data = "Default";

		final HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setWidth("50%");
		headerPanel.setSpacing(5);
		final PushButton mainViewButton = new PushButton("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(0);

			}

		});
		final PushButton manualPunchView = new PushButton("Edit Time", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(1);

			}
		});
		final PushButton reportsViewButton = new PushButton("Reporting", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(2);

			}
		});
		mainViewButton.setSize("150px", "50px");
		manualPunchView.setSize("150px", "50px");
		reportsViewButton.setSize("150px", "50px");

		Image clockImage = new Image("images/clock-small.png");
		clockImage.setPixelSize(74, 96);
		headerPanel.add(clockImage);
		headerPanel.add(mainViewButton);
		headerPanel.add(manualPunchView);
		headerPanel.add(reportsViewButton);
		signOutLink.setHref(loginInfo.getLogoutUrl());
		headerPanel.add(signOutLink);
		headerPanel.add(new Label(loginInfo.getNickname() + loginInfo.getUserId()));
		headerPanel.setBorderWidth(5);

		Button b = new Button("Click Me!", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				StringBuilder sb = new StringBuilder();
				sb.append("key1=JACOB");
				sb.append("&key2=YAY");
				sb.append("&key3=10:30");
				postData(url, builder, sb.toString());

			}
		});

		mainView.button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Test");
				postData(url, builder, mainView.notes.getValue());

			}

		});

		RootPanel.get("header").add(headerPanel);

		RootPanel.get("content").add(b);

		RootPanel.get("content").add(mainView.getMainPanel());

	}

}
