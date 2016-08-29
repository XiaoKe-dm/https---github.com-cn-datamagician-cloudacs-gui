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
import org.acs.core.service.DeviceService;
import org.acs.core.service.PresetsService;
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
@RequestMapping("presets")
public class PresetsController {

	@Autowired
	private PresetsService presetsService;
	
	@RequestMapping("/presetsIndex")
	public String presetsIndex(){
		return "presets/presets_manager";
	}
	
	@RequestMapping("/getAllPresets")
	public void getAllPresets(HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		String resultJson = presetsService.getAllPresets(operId);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getPresetsPage")
	public void getPresetsPage(String currentPage,String queryColumn,String queryValue,HttpSession session,HttpServletResponse response){
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		String query = "";
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
		PageResults<Preset> presetPage= presetsService.getPresetsPage(query,cPage);
//		String resultJson = presetsService.getPresetsPage(query,cPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(presetPage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/removePresetById")
	public void removePresetById(String id,HttpServletResponse response){
		String state = presetsService.removePresetById(id);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addPresets")
	public void addPresets(Preset preset,HttpSession session,HttpServletResponse response){
		
		User user = (User)session.getAttribute(User.SESSION_USERNAME);
		String operId = null;
		if(!user.isManager()){
			operId = user.getOperId()+"";
		}
		
		String state = presetsService.addPresets(operId,preset);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/findPresetsById")
	public void findPresetsById(String id,HttpServletResponse response){
		String state = presetsService.findPresetsById(id);
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
