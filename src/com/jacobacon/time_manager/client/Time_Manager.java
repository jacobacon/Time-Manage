package com.jacobacon.time_manager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

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

	public void onModuleLoad() {

		// Create RequestBuilder for POST
		final String url = "punch";
		final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
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
		headerPanel.setBorderWidth(5);

		// Initialize Arrays
		// String names[] = { "Jacob", "Peter", "Megan", "Jeff" };

		// Create Form Panel, and set its attributes.
		// final FormPanel form = new FormPanel();
		// form.setAction("/login");
		// form.setMethod(FormPanel.METHOD_POST);

		// Create a Horizontal Panel to hold the elements.
		// HorizontalPanel panel = new HorizontalPanel();
		// panel.getElement().setId("test");
		// panel.getElement().setAttribute("align", "center");
		// form.setWidget(panel);

		// Create name list.
		// final ListBox nameList = new ListBox();
		// nameList.setName("nameListElement");
		// panel.add(nameList);

		// Create List of Names
		// for (int i = 0; i < names.length; i++) {
		// nameList.addItem(names[i], names[i]);
		// }

		// Create Punch in and Punch out.
		// final UTCTimeBox timeIn = new
		// UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
		// final UTCTimeBox timeOut = new
		// UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
		// final Hidden hiddenPunchIn = new Hidden("hiddenPunchIn");
		// final Hidden hiddenPunchOut = new Hidden("hiddenPunchOut");

		// timeIn.setValue(UTCTimeBox.getValueForNextHour());
		// timeIn.setTitle("Punch In");

		// timeOut.setValue(UTCTimeBox.getValueForNextHour());
		// timeOut.setTitle("Punch Out");

		// panel.add(timeIn);
		// panel.add(timeOut);
		// panel.add(hiddenPunchIn);
		// panel.add(hiddenPunchOut);

		// panel.add(new Button("Submit", new ClickHandler() {

		// @Override
		// public void onClick(ClickEvent event) {

		// Hacky way to send value of punch to server
		// String hiddenIn = String.valueOf(timeIn.getValue());
		// hiddenPunchIn.setValue(hiddenIn);
		// String hiddenOut = String.valueOf(timeOut.getValue());
		// hiddenPunchOut.setValue(hiddenOut);

		// Window.alert("Your Manual Punch Has Been Submitted." +
		// hiddenPunchIn.getValue());

		// form.submit();

		// }
		// }));

		// Focus the cursor on the name field when the app loads
		// nameList.setFocus(true);

		// RootPanel.get().add(form);

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
				postData(url, builder, mainView.tb.getValue());

			}

		});

		RootPanel.get("header").add(headerPanel);

		RootPanel.get("content").add(b);

		RootPanel.get("content").add(mainView.getMainPanel());

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

}
