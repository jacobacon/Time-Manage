package com.jacobacon.timemanage.client.ui;

import java.util.HashSet;
import java.util.Set;

import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
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

	@UiHandler("register")
	void doClick(ClickEvent event) {
		register();
	}

	public AdminView() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Admin");
	}

	public void register() {

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

}
