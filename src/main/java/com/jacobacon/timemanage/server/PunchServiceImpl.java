package com.jacobacon.timemanage.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<WorkDay> getWorkDays() {
			return null;
	}
	
	
	@Override
	public List<String> test(){
		List<String> list = new ArrayList<String>();
		
		list.add("Test 1");
		list.add("Test 2");
		list.add("Test 3");
		list.add("Test 4");
		list.add("Test 5");
		return list;
	}

}
