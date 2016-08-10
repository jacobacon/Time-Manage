package com.jacobacon.time_manager.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MobileView extends Composite {

	private static MobileViewUiBinder uiBinder = GWT.create(MobileViewUiBinder.class);

	interface MobileViewUiBinder extends UiBinder<Widget, MobileView> {
	}

	public MobileView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	TextBox loginBox;
	
	@UiHandler("loginBox")
	void getValue(ValueChangeEvent<String> event){
		Window.alert(event.getValue());
		
	}
	
	

}
