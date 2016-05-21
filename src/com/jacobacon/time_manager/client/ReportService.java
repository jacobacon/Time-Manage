package com.jacobacon.time_manager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.time_manager.shared.WorkDay;

@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {
	public void saveWork(WorkDay work);

	public List<WorkDay> getWorkDayBulk();

}
