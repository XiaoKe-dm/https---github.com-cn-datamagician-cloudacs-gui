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
import org.acs.core.model.Modules;
import org.acs.core.service.ModulesService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.service.OperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("modules")
public class ModulesController {

	@Autowired
	private ModulesService modulesService;
	@Autowired
	private OperService operService;
	
	@RequestMapping("/modulesIndex")
	public String modulesIndex(){
		return "modules/modules_manager";
	}
	@RequestMapping("/getAllModules")
	public void getAllModules(HttpSession session,HttpServletResponse response){
		User user = (User) session.getAttribute(User.SESSION_USERNAME);
		List<Modules> modulesList = null;
		if(user.isManager()){
			modulesList = modulesService.findAll(Modules.class);
		}else{
			List<Oper> operList = operService.findModlesByUser(user);
			modulesList = new ArrayList<Modules>();
			for(Oper oper:operList){
				modulesList.add(modulesService.findByPrimaryKey(Modules.class, Long.parseLong(oper.getModuleId())));
			}
		}
		String resultJson = JSON.toJSONString(modulesList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getModulesPage")
	public void getModulesPage(String currentPage,String queryColumn,String queryValue,HttpSession session,HttpServletResponse response){
		User user = (User) session.getAttribute(User.SESSION_USERNAME);
		PageResults<Modules> modulesPage = null;
		Map<String,Object> params = new HashMap<String,Object>();
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		params.put("start", (cPage-1)*PageResults.pageSize);
		params.put("limit",PageResults.pageSize);
		if(queryValue!=null&&!"".equals(queryValue.trim()))params.put(queryColumn,"%"+queryValue.trim()+"%");
		if(!user.isManager()){
			List<Oper> operList = operService.findModlesByUser(user);
			List<String> modulesList = new ArrayList<String>();
			for(Oper oper:operList){
				modulesList.add(oper.getModuleId());
			}
			params.put("modulesList", modulesList);
		}
		modulesPage = modulesService.findModulesByParamsPage(params);
		modulesPage.setCurrentPage(cPage);
		String resultJson = JSON.toJSONString(modulesPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addModules")
	public void addModules(Modules modules,HttpServletResponse response){
		
		String state = "success";
		state = modulesService.insertByNBI(modules);
		if(HttpUtil.SUCCESS.equals(state)){
			modulesService.insert(Modules.class, modules);
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/removeModulesById")
	public void addModules(String sid,String name,HttpServletResponse response){
		Modules modules = new Modules();
		modules.setSid(Long.parseLong(sid));
		modules.setName(name);
		String state = "success";
		state = modulesService.deleteByNBI(modules);
		if(HttpUtil.SUCCESS.equals(state)){
			modulesService.delete(Modules.class, modules);
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/findDeviceByModules")
	public void findDeviceByModules(String modulesName,HttpServletResponse response){
		List<Device> deviceList = modulesService.findDeviceByModules(modulesName);
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
	@RequestMapping("/findParameterByModules")
	public void findParameterByModules(String modulesName,HttpServletResponse response){
		String resultJson = modulesService.findParameterByModules(modulesName);
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
