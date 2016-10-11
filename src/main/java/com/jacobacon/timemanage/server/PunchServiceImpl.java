package com.jacobacon.timemanage.server;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.Work;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.shared.WorkDay;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class PunchServiceImpl extends RemoteServiceServlet implements PunchService {

	static {
		ObjectifyService.register(WorkDay.class);
	}

	@Override
	public void saveWorkDay(final WorkDay workDay) {
		ofy().transact(new VoidWork() {

			@Override
			public void vrun() {
				ofy().save().entity(workDay).now();

			}

		});

	}

	@Override
	public WorkDay getWorkDay(final String name) {
		return ofy().transact(new Work<WorkDay>() {

			@Override
			public WorkDay run() {
				WorkDay result = ofy().load().key(Key.create(WorkDay.class, name)).now();
				if (result != null) {
					return result;
				} else {
					return null;
				}
			}

		});
	}

	@Override
	public WorkDay getWorkDay(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<WorkDay> getWorkDays() {
		// TODO Auto-generated method stub
		return null;
	}

}
