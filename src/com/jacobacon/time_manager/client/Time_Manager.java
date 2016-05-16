package com.jacobacon.time_manager.client;

import java.util.Date;
import java.util.List;

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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jacobacon.time_manager.shared.Employee;
import com.jacobacon.time_manager.shared.WorkDay;

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

	@SuppressWarnings("unused")
	private Employee employee = null;

	private WorkDay work;
	// = new WorkDay("test", new Date(), new Date(), "Work", "Completed Stuff");

	// private DateBox dateBox = new DateBox();

	private final Grid grid = new Grid(5, 5);

	private PunchServiceAsync punchService;

	public void onModuleLoad() {

		// dateBox.setValue(new Date());
		// dateBox.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat(PredefinedFormat.TIME_FULL)));

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

		punchService.addWork(work, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Window.alert(result);

			}
		});

	}

	

	
	//Shows the login page if the user needs to login.
	public void loadLogin() {
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);
	}

	//Method that shows the actual app after login.
	public void showApp() {

		// Create RequestBuilder for POST

		punchService = GWT.create(PunchService.class);
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
		final PushButton manualPunchViewButton = new PushButton("Edit Time", new ClickHandler() {

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
		manualPunchViewButton.setSize("150px", "50px");
		reportsViewButton.setSize("150px", "50px");

		Image clockImage = new Image("images/clock-small.png");
		clockImage.setPixelSize(74, 96);
		headerPanel.add(clockImage);
		headerPanel.add(mainViewButton);
		headerPanel.add(manualPunchViewButton);
		headerPanel.add(reportsViewButton);
		signOutLink.setHref(loginInfo.getLogoutUrl());
		headerPanel.add(signOutLink);
		headerPanel.add(new Label(loginInfo.getNickname() + loginInfo.getUserId()));
		headerPanel.setBorderWidth(5);

		Button b = new Button("Click Me to Get Work", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// StringBuilder sb = new StringBuilder();
				// sb.append("key1=JACOB");
				// sb.append("&key2=YAY");
				// sb.append("&key3=10:30");
				// postData(url, builder, sb.toString());

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

		Button b2 = new Button("Click Me To Add Work", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				Date dateIn = new Date(mainView.timeIn.getValue());
				Date dateOut = new Date(mainView.timeOut.getValue());

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
						Window.alert("Work Added");

					}
				});

			}
		});
		
		Button b3 = new Button("CLick me to test the query", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				punchService.getWorkQuery(new AsyncCallback<WorkDay>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(WorkDay result) {
						// TODO Auto-generated method stub
						Window.alert(result.getNotes() + result.getId());
						
					}
					
				});
				
			}
		}){
			
		};
		
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
						for(int i = 0; i < result.size(); i++){
							WorkDay workDay = result.get(i);
							output += workDay.getNotes();
						}
						Window.alert(output);
						
					}
				});
				
			}
			
		});
		


		RootPanel.get("header").add(headerPanel);

		RootPanel.get("content").add(b);
		RootPanel.get("content").add(b2);
		RootPanel.get("content").add(b3);
		RootPanel.get("content").add(b4);
		

		// Put some values in the grid cells.
		for (int row = 0; row < 5; ++row) {
			for (int col = 0; col < 5; ++col)
				grid.setText(row, col, "" + row + ", " + col);
		}

		// Just for good measure, let's put a button in the center.
		grid.setWidget(2, 2, new Button("Does nothing, but could"));

		// You can use the CellFormatter to affect the layout of the grid's
		// cells.
		grid.getCellFormatter().setWidth(0, 2, "256px");

		grid.setVisible(false);

		RootPanel.get("content").add(grid);
		// RootPanel.get("content").add(timePicker);

		RootPanel.get("content").add(mainView.getMainPanel());

		// Still working on stuff so disable some buttons.

	}

	//Changes the content of the page to the different views.
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
	
	//Old Method of sending the data to the server. Use PunchService instead.
	@Deprecated
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
}
