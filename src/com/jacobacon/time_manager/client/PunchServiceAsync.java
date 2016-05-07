package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PunchServiceAsync {
	public void saveWork(WorkDay work, AsyncCallback<Void> async);
	
	public void addWork(WorkDay work, AsyncCallback<String> async);
	
	public void getWork(Long id, AsyncCallback<WorkDay> async);
}
