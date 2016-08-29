package org.acs.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.core.model.Device;
import org.acs.core.model.Task;
import org.acs.core.service.DeviceService;
import org.acs.core.service.ModulesService;
import org.acs.core.service.TaskService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ModulesService modulesService;
	
	@RequestMapping("/taskIndex")
	public String taskIndex(){
		//List<Device> deviceList = modulesService.getAllDevice();
		//modelMap.addAttribute("tasksList", deviceList);
		return "task/task_manager";
	}
	
	@RequestMapping("/getAllTasks")
	public void getAllTasks(HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		String resultJson = taskService.getAllTasks(operId);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getAllTasksPage")
	public void getAllTasksPage(String currentPage,String queryColumn,String queryValue,HttpSession session,HttpServletResponse response){
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		String query = "";
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		
		if(user.isManager()){
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\""+queryColumn+"\":\""+queryValue+"/i\"}";
			}
		}else{
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\"_oper\":\""+user.getOperId()+"\",\""+queryColumn+"\":\""+queryValue+"/i\"}";
			}else{
				query = "query={\"_oper\":\""+user.getOperId()+"\"}";
			}
		}
		PageResults<Task> presetPage = taskService.getAllTasksPage(query,cPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(presetPage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/showTaskById")
	public void showTaskById(String id,HttpServletResponse response){
		String resultJson = taskService.showTaskById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/destroyTaskById")
	public void destroyTaskById(String taskId,String deviceId,HttpServletResponse response){
		String state = taskService.destroyTaskById(taskId);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/deviceRebootById")
	public void deviceRebootById(String id,HttpServletResponse response){
		String state = taskService.deviceRebootById(id);
		String resultJson = "";
		if(state==HttpUtil.SUCCESS){
			resultJson = state;
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/deviceFactoryResetById")
	public void deviceFactoryResetById(String id,HttpServletResponse response){
		String state = taskService.deviceFactoryResetById(id);
		String resultJson = "";
		if(state==HttpUtil.SUCCESS){
			resultJson = state;
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/refreshDeviceById")
	public void refreshDeviceById(String id,HttpServletResponse response){
		String state = taskService.refreshDeviceById(id);
		String resultJson = "";
		if(state==HttpUtil.SUCCESS){
			resultJson = state;
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/updateParameterValues")
	public void updateParameterValues(String id,String key,String value,String type,HttpServletResponse response){
		String resultJson = taskService.setParameterValues(id,key,value,type);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/updateParameterListValues")
	public void updateParameterListValues(String id,@RequestParam("keyList[]")String[] keyList,@RequestParam("valueList[]")String[] valueList,@RequestParam("typeList[]")String[] typeList,HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		String resultJson = taskService.setParameterListValues(operId,id,keyList,valueList,typeList);
		boolean isReboot = false;
		for(String key:keyList){
			if(key.toLowerCase().indexOf("username")>=0||key.toLowerCase().indexOf("password")>=0)isReboot=true;
		}
		if(isReboot){
			if(HttpUtil.SUCCESS.equals(resultJson)||HttpUtil.QUEYED.equals(resultJson)){
				taskService.deviceRebootById(id);
			}
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addTask")
	public void addTask(Task task,HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		
		if(task.getDeviceList()==null||task.getDeviceList().size()<=0){
			List<Device> deviceList = modulesService.findDeviceByModules(task.getModuleName());
			List<String> deviceIdList = new ArrayList<String>();
			for(Device device:deviceList){
				deviceIdList.add(device.getId());
			}
			task.setDeviceList(deviceIdList);
		}
		String state = taskService.addTask(operId,task);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/retryTask")
	public void retryTask(String id,HttpServletResponse response){
		String state = taskService.retryTask(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/refreshObject")
	public void refreshObject(String id,String name,HttpServletResponse response){
		String state = taskService.refreshObject(id,name);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/downloadFile")
	public void downloadFile(String id,String fileId,String fileName,HttpServletResponse response){
		String state = taskService.downloadFile(id,fileId,fileName);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
