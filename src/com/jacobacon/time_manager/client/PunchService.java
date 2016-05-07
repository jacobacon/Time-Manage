package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("punch")
public interface PunchService extends RemoteService{
	public void saveWork(WorkDay work);
	
	public String addWork(WorkDay work);
	
	public WorkDay getWork(Long id);
	
	

}
