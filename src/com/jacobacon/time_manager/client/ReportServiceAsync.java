package com.jacobacon.time_manager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jacobacon.time_manager.shared.WorkDay;

public interface ReportServiceAsync {
	
	public void saveWork(WorkDay work, AsyncCallback<Void> async);
	
	public void getWorkDayBulk(AsyncCallback<List<WorkDay>> async);

}
