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

import org.acs.core.model.Configuration;
import org.acs.core.model.Device;
import org.acs.core.model.Precondition;
import org.acs.core.model.Preset;
import org.acs.core.model.Provisioning;
import org.acs.core.service.DeviceService;
import org.acs.core.service.PresetsService;
import org.acs.core.service.ProvisioningService;
import org.acs.core.service.TaskService;
import org.acs.utils.tools.HttpUtil;
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
@RequestMapping("provisioning")
public class ProvisioningController {

	@Autowired
	private ProvisioningService provisioningService;
	
	@RequestMapping("/provisioningIndex")
	public String presetsIndex(){
		return "provisioning/provisioning_manager";
	}
	
	@RequestMapping("/getAllProvisioning")
	public void getAllProvisioning(HttpServletResponse response){
		String resultJson = provisioningService.getAllProvisioning();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getProvisioningPage")
	public void getProvisioningPage(String currentPage,String queryColumn,String queryValue,HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		String query = "";
		
		/*if(queryValue!=null&&!"".equals(queryValue.trim())){
			int docLength = 0;
			queryValue = queryValue.trim();
			for(int i=0;i<queryValue.length();i++){
				if(":".equals(String.valueOf(queryValue.charAt(i))))++docLength;
			}
			if(docLength==0){
				StringBuffer s1=new StringBuffer(queryValue);
				for(int index=2;index<s1.length();index+=3){
					s1.insert(index, ":");
				}
				queryValue = s1.toString();
			}
		}*/
		if(user.isManager()){
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\""+queryColumn+"\":\"/"+queryValue+"/i\"}";
			}
		}else{
			if(queryValue!=null&&!"".equals(queryValue.trim())){
				query = "query={\"oper\":\""+user.getOperId()+"\",\""+queryColumn+"\":\"/"+queryValue+"/i\"}";
			}else{
				query = "query={\"oper\":\""+user.getOperId()+"\"}";
			}
		}
		
		/*if(queryValue!=null&&!"".equals(queryValue.trim())){
			query = "query={\"_id\":\"/"+queryValue+"/i\"}";
		}*/
		PageResults<Provisioning> provisioningPage= provisioningService.getProvisioningPage(query,cPage);
//		String resultJson = presetsService.getPresetsPage(query,cPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(provisioningPage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/removeProvisioningById")
	public void removeProvisioningById(String name,HttpServletResponse response){
		String state = provisioningService.removeProvisioningById(name);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addProvisioning")
	public void addProvisioning(String mac,String domain,String username,String password,HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		Provisioning provisioning = new Provisioning();
		provisioning.setName(mac.replaceAll(":", ""));
		String macTemp = "";
		if(mac.indexOf(":")<0){
			for (int i = 0 ; i <mac.length(); i++ ) {
				if(i!=0&&i%2==0)macTemp = macTemp +":"+String.valueOf(mac.charAt(i));
				else macTemp = macTemp + String.valueOf(mac.charAt(i));
			}
			mac = macTemp;
		}
		provisioning.setMac(mac);
		provisioning.setUsername(username);
		provisioning.setPassword(password);
		provisioning.setDomain(domain);
		if(!user.isManager())provisioning.setOperId(user.getOperId()+"");
		String state = provisioningService.addProvisioning(provisioning);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/findProvisioningById")
	public void findProvisioningById(String name,HttpServletResponse response){
		String state = provisioningService.findProvisioningById(name);
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
