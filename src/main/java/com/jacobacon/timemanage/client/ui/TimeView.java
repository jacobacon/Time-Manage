package com.jacobacon.timemanage.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.client.services.PunchServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.AppResources;
import com.jacobacon.timemanage.shared.WorkDay;

public class TimeView extends Composite {

	Logger log = LoggerFactory.getLogger(TimeView.class);

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);

	private static PunchServiceAsync punchService = GWT.create(PunchService.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	@UiTemplate("TimeView.ui.xml")
	interface HomeUiBinder extends UiBinder<Widget, TimeView> {
	}

	@UiField(provided = true)
	final AppResources res;
	
	@UiField
	HorizontalPanel hp;

	FullCalendar cal = new FullCalendar("timeViewCal", ViewOption.agendaWeek, false);
	

	public TimeView() {
		this.res = GWT.create(AppResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Time-Log");
		cal.addLoadHandler(new LoadHandler() {
			
			@Override
			public void onLoad(LoadEvent event) {
				addEvents();
				
			}
		});
		hp.add(cal);
	}

	@UiHandler("testButton")
	public void testButton(ClickEvent event) {

		punchService.getWorkDays(new AsyncCallback<List<WorkDay>>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<WorkDay> result) {
				String output = "";

				for (int i = 0; i < result.size(); i++) {
					output += result.get(i).getName();
				}
				Window.alert(output);

			}
		});
	}
	
	protected void addEvents(){
		
	}

}