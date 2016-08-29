package org.acs.core.service;

import org.acs.core.model.Task;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseService;

public interface TaskService extends BaseService{

	String getAllTasks(String operId);
	
	String destroyTaskById(String taskId);

	String showTaskById(String id);

	String deviceRebootById(String id);

	String deviceFactoryResetById(String id);
	
	String setParameterValues(String id, String key, String value,String type);

	String addTask(String operId,Task task);
	
	String deviceRefreshObjectById(String id,String objectName);

	String retryTask(String id);

	String refreshDeviceById(String id);

	String refreshObject(String id,String name);

	String downloadFile(String id, String fileId,String fileName);

	PageResults<Task> getAllTasksPage(String query, int cPage);

	String setParameterListValues(String operId,String id, String[] keyList, String[] valueList, String[] typeList);

}
