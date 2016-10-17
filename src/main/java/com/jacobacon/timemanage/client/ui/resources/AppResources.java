package com.jacobacon.timemanage.client.ui.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppResources extends ClientBundle {

	public interface MyCss extends CssResource {

		public String background();
		
		public String box();
		
		public String button();
		
		public String panel();
		
		public String center();

	}

	@Source("App.css")
	MyCss style();

}
