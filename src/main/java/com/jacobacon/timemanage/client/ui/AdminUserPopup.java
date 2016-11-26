package com.jacobacon.timemanage.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AdminUserPopup extends Composite {

	private static AdminUserPopupUiBinder uiBinder = GWT.create(AdminUserPopupUiBinder.class);

	interface AdminUserPopupUiBinder extends UiBinder<Widget, AdminUserPopup> {
	}

	public AdminUserPopup() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("saveButton")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

}
