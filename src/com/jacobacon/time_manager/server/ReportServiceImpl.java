package com.jacobacon.time_manager.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;
import com.jacobacon.time_manager.client.service.ReportService;
import com.jacobacon.time_manager.shared.WorkDay;

public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {
	private static final long serialVersionUID = 1L;

	@Override
	public void saveWork(WorkDay work) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WorkDay> getWorkDayBulk() {

		ObjectifyService.register(WorkDay.class);
		/*
		 * List<WorkDay> list = .load().type(WorkDay.class).list();
		 * 
		 * 
		 */

		List<WorkDay> list = ofy().load().type(WorkDay.class).filter("employeeName", "jacob").list();

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i).getNotes());

		}

		ArrayList<WorkDay> array = new ArrayList<WorkDay>(list);

		return array;
	}

}
