package com.jacobacon.timemanage.client.ui;

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
import com.google.gwt.user.client.ui.PopupPanel;
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

	@UiField
	Select userSelect;

	// private Select userSelect = new Select();

	@UiHandler("register")
	void doClick(ClickEvent event) {
		register();
		userSelect.refresh();
		userSelect.render();
	}

	@UiHandler("modifyButton")
	void doModify(ClickEvent event) {
		Notify.notify("Modify User Panel will be shown.");
		EditPopup editPopup = new EditPopup();
		
		editPopup.add(new Label("Test"));
		
		editPopup.setGlassEnabled(true);
		editPopup.show();
		editPopup.center();
		
	}

	public AdminView() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Admin");

		addUsers();

		// rightPanel.add(userSelect);
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

		loginService.getUserList(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<String> result) {
				String[] userList = result.toArray(new String[result.size()]);
				log.info(userList.length + " Names Found.");

				if (userList.length != 0) {

					for (int i = 0; i < userList.length; i++) {
						Option option = new Option();
						option.setText(userList[i]);
						option.setId(userList[i]);
						option.setValue(userList[i]);

						userSelect.add(option);

					}

					userSelect.refresh();
					userSelect.render();
				} else {
					Notify.notify("No Users Found.");
				}
			}
		});

	}
	
	private static class EditPopup extends PopupPanel{
		public EditPopup(){
			super(true);
			
			setWidget(new Label("Click outside to close this."));
		}
		
		
	}
}
