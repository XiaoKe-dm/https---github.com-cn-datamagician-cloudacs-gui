package org.acs.core.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acs.core.model.Configuration;
import org.acs.core.model.Preset;
import org.acs.core.model.Task;
import org.acs.core.service.TaskService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseServiceImpl;
import org.acs.utils.tools.ACSConfiguration;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

@Repository("taskService")
public class TaskServiceImpl extends BaseServiceImpl implements TaskService{

	@Override
	public String getAllTasks(String operId) {
		// TODO Auto-generated method stub
		String query = "";
		if(operId!=null){
			query = "query={\"_oper\":\""+operId+"\"}";
		}
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.TASK+"/?"+query+"&sort={\"timestamp\":-1}";
		String json = HttpUtil.getContentByGet(url);
		return json;
	}
	@Override
	public String destroyTaskById(String taskId) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.TASK+"/"+taskId;
		System.out.println(url);
		String state = HttpUtil.getContentByDelete(url);
		System.out.println("state:=="+state);
		return state;
	}

	@Override
	public String showTaskById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.TASK;
		String query = "query={\"device\":\""+id+"\"}&sort={\"timestamp\":-1}";
		String json = HttpUtil.getContentByGet(url+"/?"+query);
//		System.out.println(json);
		return json;
	}
	@Override
	public String deviceRebootById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		//String option = "timeout=3000&"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{ \"name\": \"reboot\" }";
		String state = HttpUtil.getContentByPost(url, params);
		return state;
	}
	@Override
	public String deviceRefreshObjectById(String id,String objectName) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		//String option = "timeout=3000&"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{ \"name\": \"refreshObject\" ,\"objectName\":\""+objectName+"\"}";
		String state = HttpUtil.getContentByPost(url, params);
		return state;
	}
	@Override
	public String deviceFactoryResetById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		//String option = "timeout=3000&"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{ \"name\": \"factoryReset\" }";
		String state = HttpUtil.getContentByPost(url, params);
		return state;
	}
	@Override
	public String setParameterValues(String id, String key, String value,String type) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{\"name\":\"setParameterValues\", \"parameterValues\":[[";
		params = params + "\""+key+"\",\""+value+"\",\""+type+"\"]]}";
        // 表单参数与get形式一样
		String state = HttpUtil.getContentByPost(url,params);
		return state;
	}
	public String setParameterValuesByparams(String id, String params) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
//		String params = "{\"name\":\"setParameterValues\", \"parameterValues\":[[";
//		params = params + "\""+key+"\",\""+value+"\",\"xsd:string\"]]}";
        // 表单参数与get形式一样
		String state = HttpUtil.getContentByPost(url,params);
		return state;
	}
	public String addObjectById(String id,String objectName){
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{\"name\":\"addObject\", \"objectName\":\""+objectName+"\"}";
        // 表单参数与get形式一样
		String state = HttpUtil.getContentByPost(url,params);
		return state;
	}
	@Override
	public String addTask(String operId,Task task) {
		// TODO Auto-generated method stub
		String name = task.getTaskName();
		List<String> deviceIdList = task.getDeviceList();
		Map<String,String> stateMap = new HashMap<String,String>();
		if("setParameterValues".equals(name)){
			String paramter = "";
			for(Configuration configuration:task.getConfigurations()){
				paramter = paramter + "," + "[\""+configuration.getName()+"\",\""+configuration.getValue()+"\"]";
			}
			paramter = "["+paramter.substring(1)+"]";
			String params = "";
			if(operId!=null){
				params = "{\"_oper\":\""+operId+"\",\"name\":\"setParameterValues\", \"parameterValues\":[" +paramter +"]}";
			}else{
				params = "{\"name\":\"setParameterValues\", \"parameterValues\":[" +paramter +"]}";
			}
			for(String deviceId:deviceIdList){
				setParameterValuesByparams(deviceId,params);
			}
//		}else if("refreshObject".equals(name)){
//			for(String deviceId:deviceIdList){
//				
//			}
		}else if("addObject".equals(name)){
			String objectName = "";
			if(task.getConfigurations().size()>0){
				objectName = task.getConfigurations().get(0).getName();
			}
			if(objectName!=null&&!"".equals(objectName)){
				for(String deviceId:deviceIdList){
					String state = addObjectById(deviceId,objectName);
					stateMap.put(deviceId, state);
				}
			}
		}else if("reboot".equals(name)){
			for(String deviceId:deviceIdList){
				String state = deviceRebootById(deviceId);
				stateMap.put(deviceId, state);
			}
		}else if("FactoryReset".equals(name)){
			for(String deviceId:deviceIdList){
				String state = deviceFactoryResetById(deviceId);
				stateMap.put(deviceId, state);
			}
		}
		String resultJson = JSON.toJSONString(stateMap);
		return resultJson;
	}
	@Override
	public String retryTask(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.TASK+"/"+id+"/retry?"+NBIStaticVariable.CONNECTION_REQUEST;
		String state = HttpUtil.getContentByPost(url,null);
		System.out.println(state);
		return state;
	}
	@Override
	public String refreshDeviceById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{\"name\":\"refreshObject\", \"objectName\":\"\"}";
		String state = HttpUtil.getContentByPost(url,params);
		System.out.println(state);
		return state;
	}
	@Override
	public String refreshObject(String id,String name) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "{\"name\":\"refreshObject\", \"objectName\":\""+name+"\"}";
		String state = HttpUtil.getContentByPost(url,params);
		System.out.println(state);
		return state;
	}
	@Override
	public String downloadFile(String id, String fileId,String fileName) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		System.out.println("file:"+fileId+",filename:"+fileName);
		String params = "{\"name\":\"download\", \"file\":\""+fileId+"\", \"filename\":\""+fileName+"\"}";
		String state = HttpUtil.getContentByPost(url,params);
		System.out.println(state);
		return state;
	}
	@Override
	public PageResults<Task> getAllTasksPage(String query, int cPage) {
		// TODO Auto-generated method stub
		PageResults<Task> taskPage = new PageResults<Task>();
		taskPage.setCurrentPage(cPage);
		String limit = "limit="+PageResults.pageSize+"&skip="+PageResults.pageSize*(cPage-1);
		if(query!=null&&!"".equals(query))query = query + "&";
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.TASK+"/?"+query+limit+"&sort={\"timestamp\":-1}";
		Map<String,Object> resultMap = HttpUtil.getContentByGetOnPage(url);
		String json = (String) resultMap.get(HttpUtil.RESULT);
		
		taskPage.setJsonResults(json);
		long totalCount = (long) resultMap.get(HttpUtil.TOTAL);
		taskPage.setTotalCount(totalCount);
		taskPage.setPageCountBySize(totalCount);
		
		return taskPage;
	}
	@Override
	public String setParameterListValues(String operId,String id, String[] keyList, String[] valueList, String[] typeList) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TASK+"?"+NBIStaticVariable.CONNECTION_REQUEST;
		String params = "";
		if(operId!=null){
			params = "{\"_oper\":\""+operId+"\",\"name\":\"setParameterValues\", \"parameterValues\":[";
		}else{
			params = "{\"name\":\"setParameterValues\", \"parameterValues\":[";
		}
		for(int i=0;i<keyList.length;i++){
			if(keyList[i]!=null&&!"".equals(keyList[i].trim())&&valueList[i]!=null&&!"".equals(valueList[i].trim())){
				params = params + "[\""+keyList[i]+"\",\""+valueList[i]+"\",\""+typeList[i]+"\"],";
			}
			
		}
		params = params.substring(0, params.length()-1);
		params = params + "]}";
        // 表单参数与get形式一样
		String state = HttpUtil.getContentByPost(url,params);
		return state;
	}
	
}
