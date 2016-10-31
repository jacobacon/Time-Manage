package com.jacobacon.timemanage.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
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

	@UiField
	HorizontalPanel scroll;

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

		scroll.add(cal);
		hp.add(scroll);

	}

	protected void addEvents() {

		punchService.getWorkDaysFiltered("name", AppView.getUserData().getName(), new AsyncCallback<List<WorkDay>>() {

			@Override
			public void onFailure(Throwable thrown) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<WorkDay> result) {
				ArrayList<WorkDay> workdays = new ArrayList<WorkDay>(result);
				if (workdays.size() == 0) {
					Notify.notify("No Work Found");
				} else {
					for (int i = 0; i < workdays.size(); i++) {

						WorkDay workDay = workdays.get(i);

						Event workEvent = new Event("Work: ", workDay.getJob());

						workEvent.setStart(workDay.getTimeIn());
						workEvent.setEnd(workDay.getTimeOut());
						workEvent.setDurationEditable(false);
						workEvent.setEditable(false);
						workEvent.setStartEditable(false);

						cal.addEvent(workEvent);

					}

				}

			}

		});
	}

}