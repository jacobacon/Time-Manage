package com.jacobacon.timemanage.client.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface HomeResources extends ClientBundle {

	public interface MyCss extends CssResource {

		public String background();

	}

	@Source("Home.css")
	MyCss style();

}
