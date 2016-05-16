package com.jacobacon.time_manager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.time_manager.shared.WorkDay;

@RemoteServiceRelativePath("punch")
public interface PunchService extends RemoteService {
	public void saveWork(WorkDay work);

	public String addWork(WorkDay work);

	public WorkDay getWork(String id);
	
	public WorkDay getWorkQuery();
	
	public List<WorkDay> getWorkDayBulk();
	
	public List<String> testList();

}
