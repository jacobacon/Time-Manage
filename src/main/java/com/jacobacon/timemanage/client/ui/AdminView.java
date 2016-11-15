package com.jacobacon.timemanage.client.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;

public class AdminView extends Composite {

	private static AdminViewUiBinder uiBinder = GWT.create(AdminViewUiBinder.class);

	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	private static Logger log = LoggerFactory.getLogger(AdminView.class);

	interface AdminViewUiBinder extends UiBinder<Widget, AdminView> {
	}

	@UiField
	TextBox username;

	@UiField
	TextBox name;

	@UiField
	TextBox password;

	@UiField
	Select perms;

	@UiField
	VerticalPanel rightPanel;

	private Select userSelect = new Select();

	@UiHandler("register")
	void doClick(ClickEvent event) {
		register();
	}

	public AdminView() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Admin");

		addUsers();

		rightPanel.add(userSelect);
		rightPanel.add(new Label("Blah"));

		userSelect.refresh();
		userSelect.render();
	}

	private void register() {

		Set<String> roles = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		// permissions.add("admin");
		Window.alert(perms.getSelectedItem().getValue());

		loginService.register(username.getValue(), password.getValue(), name.getValue(), roles, permissions,
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						if (result.equals("created")) {
							Notify.notify("Created User: " + username.getValue());

						} else {
							Notify.notify("Error: ", result);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Notify.notify("Couldn't Create User");
						log.warn("Couldn't Create User");

					}
				});
	}

	private void addUsers() {

		/*
		loginService.getUserList(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				log.error("Couldn't get users.");

			}

			@Override
			public void onSuccess(List<String> result) {
				Window.alert(result.size() + " Objects found.");

				ArrayList<String> users = new ArrayList<String>(result);
				Window.alert(users.size() + "User Names");
				for (int i = 0; i < 5; i++) {
					Option option = new Option();
					option.setText("Banana " + i);
					option.setId("banana " + i);
					option.setValue("banana " + i);
					userSelect.add(option);
				}
			}
		});
		
		
*/
		String [] user = {"Dave", "Joe","Bob","Jeff","Bob","Pat","Gabe"};
		for (int i = 0; i < user.length; i++) {
			Option option = new Option();
			option.setText(user[i]);
			option.setId(user[i].toLowerCase() + i);
			option.setValue(user[i]);

			userSelect.add(option);
		}

	}
}
