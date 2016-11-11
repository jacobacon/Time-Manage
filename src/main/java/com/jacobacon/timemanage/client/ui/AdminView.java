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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.server.shiro.User;

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
		/*loginService.getUsers(new AsyncCallback<List<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				log.error("Couldn't get users.");

			}

			@Override
			public void onSuccess(List<User> result) {
				ArrayList<User> users = new ArrayList<User>(result);
				for (int i = 0; i < users.size(); i++) {
					Option option = new Option();
					option.setText(users.get(i).getName());
					option.setId(users.get(i).getName());
					option.setValue(users.get(i).getName());

					userSelect.add(option);

				}

			}
		});
		*/
		
		for(int i = 0; i < 15; i++){
			Option option = new Option();
			option.setText("Test: " + i);
			option.setId("test " + i);
			option.setValue("Test: " + i);
			
			userSelect.add(option);
		}
		
		
	}
}
