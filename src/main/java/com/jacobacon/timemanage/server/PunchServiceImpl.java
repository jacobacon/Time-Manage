package com.jacobacon.timemanage.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
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
	public List<WorkDay> getAllWorkDays() {
		List<WorkDay> workdays;
		Query<WorkDay> q = ofy().load().type(WorkDay.class);
		workdays = new ArrayList<WorkDay>(q.list());
		return workdays;
	}
	
	@Override
	public List<WorkDay> getWorkDaysFiltered(String filterType, String filterParam){
		List<WorkDay> workdays;
		Query<WorkDay> q = ofy().load().type(WorkDay.class).filter(filterType, filterParam);
		workdays = new ArrayList<WorkDay>(q.list());
		return workdays;
	}
	
	
	@Override
	public List<WorkDay> test(){
		List<WorkDay> list = new ArrayList<WorkDay>();
		
		list.add(new WorkDay());
		list.add(new WorkDay());
		list.add(new WorkDay());
		list.add(new WorkDay());
		list.add(new WorkDay());
		return list;
	}

}
