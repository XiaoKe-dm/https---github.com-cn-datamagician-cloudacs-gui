package org.acs.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.core.model.Device;
import org.acs.core.model.Modules;
import org.acs.core.model.Task;
import org.acs.core.service.DeviceService;
import org.acs.core.service.ModulesService;
import org.acs.core.service.TaskService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.model.User;
import org.acs.utils.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("main")
public class MainController {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ModulesService modulesService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/index")
	public String mainIndex(){
		return "main";
	}
	@RequestMapping("/getTopDevice")
	public void getTopDevice(HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		List<Device> deviceList = deviceService.getAllDevice(user);
		List<Device> unknownDeviceList = new ArrayList<Device>();
		List<Device> activeDeviceList = new ArrayList<Device>();
		
		deviceList = sortDeviceList(deviceList);
		int unknownDeviceNum = 0;
	//	System.out.println(deviceList.size());
		for(Device device:deviceList){
			if(device.isOnline())activeDeviceList.add(device);
			/*Date timePoint = DateUtil.countHours(new Date(), 1, 1);
			if(device.getLastInform().after(timePoint)){
				activeDeviceList.add(device);
			}*/
			if(device.getModules()==null||"Unknown".equals(device.getModules())){
				unknownDeviceNum++;
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("totalDevice", deviceList);
		resultMap.put("unknownDeviceNum", unknownDeviceNum);
		resultMap.put("activeDevice", activeDeviceList);
		String resultJson = JSON.toJSONString(resultMap);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Device> sortDeviceList(List<Device> deviceList) {
		// TODO Auto-generated method stub
		for(int i=0;i<deviceList.size();i++){
			for(int j=i+1;j<deviceList.size();j++){
				if(deviceList.get(j).getLastInform().after(deviceList.get(i).getLastInform())){    //把小的值交换到后面  
                    Device temp = deviceList.get(i);  
                    deviceList.set(i,deviceList.get(j));
                    deviceList.set(j,temp);
                }  
			}
		}
		return deviceList;
	}
	@RequestMapping("/getTopModule")
	public void getTopModule(HttpServletResponse response){
		List<Modules> modulesList = modulesService.findAll(Modules.class);
		List<Modules> newModulesList = new ArrayList<Modules>();
		List<Modules> returnModulesList = new ArrayList<Modules>();
		for(Modules modules:modulesList){
			Modules newModules = new Modules();
			newModules.setName(modules.getName());
			List<Device> deviceList = modulesService.findDeviceByModules(modules.getName());
			newModules.setDeviceNum(deviceList.size());
			newModulesList.add(newModules);
		}
		newModulesList = sortModuleList(newModulesList);
		for(int i=0;i<newModulesList.size();i++){
			if(i<5){
				returnModulesList.add(newModulesList.get(i));
			}
		}
		String resultJson = JSON.toJSONString(returnModulesList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Modules> sortModuleList(List<Modules> modulesList) {
		// TODO Auto-generated method stub
		for(int i=0;i<modulesList.size();i++){
			for(int j=i+1;j<modulesList.size();j++){
				if(modulesList.get(j).getDeviceNum()>modulesList.get(i).getDeviceNum()){
					Modules temp = modulesList.get(i);  
                    modulesList.set(i,modulesList.get(j));
                    modulesList.set(j,temp);
                }  
			}
		}
		return modulesList;
	}
	@RequestMapping("/getTopTask")
	public void getTopTask(HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		String tasks = taskService.getAllTasks(operId);
		JSONArray jsonArray = JSON.parseArray(tasks);
		List<Device> deviceList = new ArrayList<Device>();
		List<Task> taskList = new ArrayList<Task>();
		List<Task> returnTaskList = new ArrayList<Task>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jobj = jsonArray.getJSONObject(i);
			Task task = new Task();
			task.setDevice(jobj.getString("device"));
			task.setTaskName(jobj.getString("name"));
			task.setTime(jobj.getString("timestamp").replace("T", " ").replace("Z", ""));
			taskList.add(task);
		}
		taskList = sortTaskList(taskList);
		for(int i=0;i<taskList.size();i++){
			if(i<5){
				returnTaskList.add(taskList.get(i));
			}
		}
		String resultJson = JSON.toJSONString(returnTaskList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Task> sortTaskList(List<Task> taskList) {
		// TODO Auto-generated method stub
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		for(int i=0;i<taskList.size();i++){
			for(int j=i+1;j<taskList.size();j++){
				if(DateUtil.StringToDate(taskList.get(j).getTime(), format).after(DateUtil.StringToDate(taskList.get(i).getTime(),format))){    //把小的值交换到后面  
					Task temp = taskList.get(i);  
                    taskList.set(i,taskList.get(j));
                    taskList.set(j,temp);
                }
			}
		}
		return taskList;
	}
}
