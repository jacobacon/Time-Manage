package com.jacobacon.time_manager.server;




import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.jacobacon.time_manager.client.PunchService;
import com.jacobacon.time_manager.shared.WorkDay;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

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
		
		ObjectifyService.register(WorkDay.class);

		WorkDay fetched = ofy().load().type(WorkDay.class).first().now();
		
		if(fetched !=null)
			return fetched;

		else
			return null;

	}
	
	public WorkDay getWorkQuery(){
		
		WorkDay fetched = ofy().load().type(WorkDay.class).filter("employeeName", "jacob").first().now();
		return fetched;
	}
	
	@Override
	public List<WorkDay> getWorkDayBulk(){
		
		ObjectifyService.register(WorkDay.class);
		List<WorkDay> list = ofy().load().type(WorkDay.class).list();
		
		
		for(int i = 0; i < list.size(); i++){
			
			System.out.println(list.get(i).getNotes());
			
			
		}
		
		ArrayList<WorkDay> array = new ArrayList<WorkDay>(list);
		
		return array;
	}
	
	
	
	public List<String> testList(){
		
		List<String> list = new ArrayList<String>();
		
		list.add("Test One");
		list.add("Test Two");
		list.add("Test Three");
		
		
		return list;
	}
}
