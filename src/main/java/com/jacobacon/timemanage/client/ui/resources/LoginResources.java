package com.jacobacon.timemanage.client.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface LoginResources extends ClientBundle {

	@Source("resources/clock.png")
	ImageResource clock();

	public interface MyCss extends CssResource {
		String blackText();

		String redText();

		String loginButton();

		String box();

		String background();

		String clock();

	}

	@Source("Login.css")
	MyCss style();

}
