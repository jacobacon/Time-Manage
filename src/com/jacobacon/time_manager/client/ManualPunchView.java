package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ManualPunchView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();
	private final HTML defaultHTML = new HTML("<p>Hello!</p>");

	private final VerticalPanel headerPanel = new VerticalPanel();
	private final HTML headerHTML = new HTML("<header><nav><a href='/login'>Click Me!</a>" + "&nbsp;"
			+ "<a href='https://www.google.com'>Goes to Google!</a>" + "" + "" + "</nav></header>");

	public final TextBox tb = new TextBox();
	public final Button button = new Button();

	public ManualPunchView() {

		mainPanel.setSpacing(5);
		// mainPanel.getElement().getStyle().setBackgroundColor("green");
		mainPanel.setSize("300px", "300px");

		headerPanel.add(headerHTML);
		headerPanel.getElement().setAttribute("align", "center");

		mainPanel.add(defaultHTML);
		mainPanel.getElement().setAttribute("align", "center");

		tb.setValue("Hello");
		button.setText("Moo");

		mainPanel.add(tb);
		mainPanel.add(button);

	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

	public VerticalPanel getHeaderPanel() {
		return headerPanel;
	}

}
