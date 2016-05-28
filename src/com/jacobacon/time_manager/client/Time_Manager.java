package com.jacobacon.time_manager.client;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jacobacon.time_manager.shared.WorkDay;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {
	static String authCode = "";
	static String state = "";

	final MainView mainView = new MainView();
	final ManualPunchView manualPunchView = new ManualPunchView();
	final ReportView reportView = new ReportView();

	// Setup Login
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please Sign in to your Google Account to Access this Application");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	private final static String LOGIN_STATUS_COOKIE = "false";
	private final static String AUTH_ID_COOKIE = "";

	private static boolean authPage = false;
	private static boolean loginStatus = false;

	final String url = "punch";

	private WorkDay work;

	// private final Grid grid = new Grid(5, 5);

	private PunchServiceAsync punchService;
	private LoginServiceOAuthAsync loginService;
	private static List<WorkDay> WORKDAYS = Arrays.asList(new WorkDay(), new WorkDay(), new WorkDay());

	private static List<WorkDay> days;

	public void onModuleLoad() {

		/*
		 * 
		 * //Test if the App is loading from a OAuth Callback or loading for the
		 * first time. if ((Window.Location.getParameter("code") != null) &&
		 * (Window.Location.getParameter("state") != null)) {
		 * 
		 * Window.alert("Hello");
		 * 
		 * //long duration = (60*60*1000); //Date expires = new
		 * Date(System.currentTimeMillis() + duration);
		 * 
		 * 
		 * //Cookies.setCookie("loggedIn", "true", expires); } else {
		 * Window.alert("No Parameters");
		 * 
		 * showApp();
		 * 
		 * String sessionID = Cookies.getCookie("loggedIn"); if
		 * (sessionID.equals(false) || sessionID == null) { loadLogin();
		 * 
		 * } else { showApp(); } }
		 * 
		 */

		
		 LoginServiceAsync loginService = GWT.create(LoginService.class);
		 loginService.login(GWT.getHostPageBaseURL(), new
		 AsyncCallback<LoginInfo>() {
		  
		 @Override public void onFailure(Throwable caught) { // TODO //
		 //Auto-generated method stub
		 
		 }
		 
		 @Override public void onSuccess(LoginInfo result) { loginInfo =
		 result; if (loginInfo.isLoggedIn()) { setUp(); showApp(); } else {
		 loadLogin(); }
		 
		 }
		 
		 });
		 
		 }
		 
		 // Shows the login page if the user needs to login. public void
		 public void loadLogin() 
		 { signInLink.setHref(loginInfo.getLoginUrl());
		 loginPanel.add(loginLabel); loginPanel.add(signInLink);
		 RootPanel.get("content").add(loginPanel);
		 
		 }
		 
		 

	
/*
	public void loadLogin() {

		loginService = GWT.create(LoginServiceOAuth.class);

		loginService.getAuthUrl(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);

			}

			@Override
			public void onSuccess(String result) {
				Window.Location.replace(result);

			}

		});

		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginLabel);

		// Window.alert(authURL);

	}
	*/

	// Method that shows the actual app after login.
	public void showApp() {

		punchService = GWT.create(PunchService.class);

		final HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setWidth("100%");
		headerPanel.setSpacing(5);
		final PushButton mainViewButton = new PushButton("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(0);

			}

		});
		final PushButton manualPunchViewButton = new PushButton("Edit Time", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(1);
				Window.alert(Cookies.getCookie("test"));

			}
		});
		final PushButton reportsViewButton = new PushButton("Reporting", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(2);

			}
		});
		mainViewButton.setSize("150px", "50px");
		manualPunchViewButton.setSize("150px", "50px");
		reportsViewButton.setSize("150px", "50px");

		Image clockImage = new Image("images/clock-small.png");
		clockImage.setPixelSize(74, 96);
		headerPanel.add(clockImage);
		headerPanel.add(mainViewButton);
		headerPanel.add(manualPunchViewButton);
		headerPanel.add(reportsViewButton);
		headerPanel.add(new Label(loginInfo.getNickname()));
		signOutLink.setHref(loginInfo.getLogoutUrl());
		headerPanel.add(signOutLink);
		headerPanel.setBorderWidth(5);

		Button b = new Button("Click Me to Get Work", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				punchService.getWork("jacob", new AsyncCallback<WorkDay>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(WorkDay result) {
						// TODO Auto-generated method stub
						Window.alert("You completed: " + result.getTaskCompleted() + "\nYour Notes are: "
								+ result.getNotes() + "\nYou clocked in at: " + result.getStartTime()
								+ "\nAnd clocked out at: " + result.getEndTime());

					}

				});

			}
		});

		Button b4 = new Button("Batch Query", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				punchService.getWorkDayBulk(new AsyncCallback<List<WorkDay>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<WorkDay> result) {
						// TODO Auto-generated method stub
						String output = "";
						for (int i = 0; i < result.size(); i++) {
							WorkDay workDay = result.get(i);
							output += workDay.getNotes();
						}
						Window.alert(output);

					}
				});

			}

		});

		RootPanel.get("header").add(headerPanel);

		setContent(0);

		// Temporary Buttons for Testing
		RootPanel.get("content").add(b);
		RootPanel.get("content").add(b4);

	}

	// Changes the content of the page to the different views.
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

			reportView.table.setRowCount(15);

			// reportView.table.setRowData(0, days);

			reportView.table.flush();

			Window.setTitle("Reports");
			break;

		}

	}

	public void setUp() {

		// Add Submit Button Logic

		mainView.submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Date dateIn = new Date(mainView.timeIn.getValue());
				Date dateOut = new Date(mainView.timeOut.getValue());

				Date breakStart = new Date(mainView.mealStart.getValue());
				Date breakEnd = new Date(mainView.mealEnd.getValue());

				// work = new WorkDay(loginInfo.getNickname(), dateIn, dateOut,
				// mainView.taskList.getSelectedItemText(),
				// mainView.notes.getValue());

				work = new WorkDay("jacob", dateIn, dateOut, mainView.taskList.getSelectedItemText(),
						mainView.notes.getValue());

				punchService.addWork(work, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						Window.alert("Work Saved");

					}
				});

			}
		});

	}

	public void saveAuthCookies() {
		Cookies.setCookie("", "");

	}
}
