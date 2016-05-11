package com.jacobacon.time_manager.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;
import com.jacobacon.time_manager.client.PunchService;
import com.jacobacon.time_manager.shared.WorkDay;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class PunchServiceImpl extends RemoteServiceServlet implements PunchService {
	private static final long serialVersionUID = 1L;

	@Override
	public void saveWork(WorkDay work) {
		Window.alert(work.getNotes());
		// TODO Auto-generated method stub
	}

	@Override
	public String addWork(WorkDay work) {

		ObjectifyService.register(WorkDay.class);

		ObjectifyService.ofy().save().entity(work).now();

		// TODO Auto-generated method stub
		return "The Work Has Been Saved";
	}

	public WorkDay getWork(String id) {

		WorkDay fetched = ofy().load().type(WorkDay.class).id(id).now();

		return fetched;

	}
}
