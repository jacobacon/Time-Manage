package com.jacobacon.time_manager.client.mainview;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite{
	
	private final VerticalPanel mainPanel = new VerticalPanel();
	private final HTML text = new HTML("This is a test!");
	
	private final Button button = new Button("Click Me", new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Window.alert("Moo");
			
		}
	});
	
	final FormPanel form = new FormPanel();
	final VerticalPanel panel = new VerticalPanel();
	final TextBox tb = new TextBox();
	
	
	public MainView(){
		mainPanel.add(text);
		mainPanel.add(button);
		
		tb.setName("test");
		
		form.setAction("/login");
		form.setWidget(panel);
		form.setEncoding(FormPanel.METHOD_POST);
		
		panel.add(tb);
		panel.add(new Button("Submit", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Moo");
				form.submit();
				
			}
		}));
		
		mainPanel.add(panel);
		
	}
	
	
	
	public VerticalPanel getMainPanel(){
		return mainPanel;
	}

}
