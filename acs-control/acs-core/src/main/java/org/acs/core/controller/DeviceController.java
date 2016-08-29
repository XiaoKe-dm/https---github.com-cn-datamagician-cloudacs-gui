package org.acs.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.core.model.Device;
import org.acs.core.service.DeviceService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.NumberUtils;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping("/deviceIndex")
	public String deviceIndex(String currentPage,String queryType,String queryColumn,String queryValue,ModelMap modelMap,HttpSession session){
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		String query = "";
		String endStr = "/i";
		if("mac".equals(queryColumn)){
			queryColumn = "summary.mac";
			/*int docLength = 0;
			queryValue = queryValue.trim();
			if(queryValue.length()==12){
				for(int i=0;i<queryValue.length();i++){
					if(":".equals(String.valueOf(queryValue.charAt(i))))++docLength;
				}
			}
			if(docLength==0){
				StringBuffer s1=new StringBuffer(queryValue);
				for(int index=2;index<s1.length();index+=3){
					s1.insert(index, ":");
				}
				queryValue = s1.toString();
			}*/
			//System.out.println("queryValue:"+queryValue);
		}else if("serialNumber".equals(queryColumn)){
			queryColumn = "summary.serialNumber";
			queryValue = "/"+queryValue;
		}else if("productClass".equals(queryColumn)){
			queryColumn = "summary.productClass";
			queryValue = "/"+queryValue;
		}
		if(queryType!=null&&"unknownModel".equals(queryType)){
			queryColumn = "_model";
			queryValue = "Unknown";
			endStr = "";
		}else if(queryType!=null&&"actice".equals(queryType)){
			queryColumn = "_active";
			/*queryColumn = "summary.lastInform";
			//得到当前的年月日小时
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			int date = c.get(Calendar.DATE);
			int hour = c.get(Calendar.HOUR);
			
		    c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)-1);
		    
		    int oyear = c.get(Calendar.YEAR);
			int omonth = c.get(Calendar.MONTH)+1;
			int odate = c.get(Calendar.DATE);
			int ohour = c.get(Calendar.HOUR);
			queryValue = "";
			if(year==oyear) queryValue = "("+year+")";
			else queryValue = "("+oyear+"|"+year+")";
			if(month==omonth) {
				if(month<10)queryValue = queryValue+"-"+"(0"+month+")";
				else queryValue = queryValue+"-"+"("+month+")";
			}else queryValue = queryValue+"-"+"("+omonth+"|"+month+")";
			if(date==odate) queryValue = queryValue+"-"+"("+date+")";
			else queryValue = queryValue+"-"+"("+odate+"|"+date+")";*/
			queryValue = "1";
		}
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		if(user.isManager()){
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\""+queryColumn+"\":\""+queryValue+endStr+"\"}";
			}
		}else{
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\"_oper\":\""+user.getOperId()+"\",\""+queryColumn+"\":\""+queryValue+endStr+"\"}";
			}else{
				query = "query={\"_oper\":\""+user.getOperId()+"\"}";
			}
		}
		PageResults<Device> devicePage = deviceService.getDevicePage(query,cPage);
		
		/*if("actice".equals(queryType)){
			List<Device> deviceList = new ArrayList<Device>();
			for(Device device:devicePage.getResults()){
				if(device.isOnline()){
					deviceList.add(device);
				}
			}
			devicePage.setResults(deviceList);
		}*/
		
		modelMap.addAttribute("devicePage", devicePage);
		modelMap.addAttribute("queryType", queryType);
		return "device/device_manager";
	}
//	@RequestMapping("/getAlldevice")
//	public void getAlldevice(HttpServletResponse response){
//		String resultJson = JSON.toJSONString("");
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(resultJson);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@RequestMapping("/getAllDevice")
	public void getAllDevice(HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		List<Device> deviceList = deviceService.getAllDevice(user);
		String resultJson = JSON.toJSONString(deviceList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getDeviceById")
	public void getDeviceById(String id,HttpServletResponse response){
		String resultJson = deviceService.getDeviceById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getDeviceWifiById")
	public void getDeviceWifiById(String id,HttpServletResponse response){
		String resultJson = deviceService.getDeviceWifiById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/showParametersById")
	public void showParametersById(String id,HttpServletResponse response){
		String resultJson = deviceService.showParametersById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addTagById")
	public void addTagById(String id,String tagName,HttpServletResponse response){
		String state = deviceService.addTagById(id,tagName);
		String resultJson = "";
		if(state==HttpUtil.SUCCESS){
			resultJson = deviceService.searchTagById(id);
		}else{
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
	@RequestMapping("/removeTagById")
	public void removeTagById(String id,String tagName,HttpServletResponse response){
		String state = deviceService.removeTagById(id,tagName);
		String resultJson = "";
		if(state==HttpUtil.SUCCESS){
			resultJson = deviceService.searchTagById(id);
		}else{
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
	@RequestMapping("/getDeviceIPAndMACById")
	public void getDeviceIPAndMACById(String id,HttpServletResponse response){
		String ipAndMac = deviceService.getDeviceIPAndMACById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(ipAndMac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getDeviceHostById")
	public void getDeviceHostById(String id,HttpServletResponse response){
		String resultJson = deviceService.getDeviceHostById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getDeviceUsernameById")
	public void getDeviceUsernameById(String id,HttpServletResponse response){
		String resultJson = deviceService.getDeviceUsernameById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
