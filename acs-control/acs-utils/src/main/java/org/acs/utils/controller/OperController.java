package org.acs.utils.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.service.OperService;
import org.acs.utils.service.UserService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("oper")
public class OperController {

	@Autowired
	private OperService operService;
	
	@RequestMapping("/operIndex")
	public String operIndex(HttpServletResponse response){
		return "oper_manager";
	}
	@RequestMapping("/getAllOper")
	public void getAllOper(HttpServletResponse response){
		List<Oper> operList = operService.findAll(Oper.class);
		String resultJson = JSON.toJSONString(operList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getAllOperAndGroup")
	public void getAllOperAndGroup(String currentPage,String queryValue,HttpServletResponse response){
		PageResults<Oper> operPage = operService.findAllOperAndGroupPage(currentPage,queryValue);
		String resultJson = JSON.toJSONString(operPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/findOperById")
	public void findOperById(String sid,HttpServletResponse response){
		Oper oper = new Oper();
		oper.setSid(Long.parseLong(sid));
		List<Oper> operList = operService.findAllOperAndGroup(oper);
		String resultJson = JSON.toJSONString(operList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addOper")
	public void addOper(String sid,String name,String domains,String modulesList,HttpServletResponse response){
		String returnMsg = "success";
		Oper oper = new Oper();
		oper.setName(name);
		oper.setDomains(domains);
		if(sid==null||"".equals(sid.trim())){
			
			operService.insert(Oper.class, oper);
			for(String module:modulesList.split(",")){
				Oper oper_module = new Oper();
				oper_module.setSid(oper.getSid());
				oper_module.setModuleId(module);
				operService.insertOperModule(oper_module);
			}
			//System.out.println(oper.getSid()+"======");
			String state = "success";
			state = operService.insertByNBI(oper);
			if(!HttpUtil.SUCCESS.equals(state)){
				returnMsg = "error";
			}
		}else{
			oper.setSid(Long.parseLong(sid));
			operService.update(Oper.class, oper);
			operService.deleteOperModule(oper);
			for(String module:modulesList.split(",")){
				Oper oper_module = new Oper();
				oper_module.setSid(oper.getSid());
				oper_module.setModuleId(module);
				operService.insertOperModule(oper_module);
			}
			
			String state = "success";
			state = operService.insertByNBI(oper);
			if(!HttpUtil.SUCCESS.equals(state)){
				returnMsg = "error";
			}
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(returnMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/deleteOper")
	public void deleteOper(String sid,HttpServletResponse response){
		String returnMsg = "success";
		Oper oper = new Oper();
		oper.setSid(Long.parseLong(sid));
		List<Oper> operList = operService.findAllOperAndGroup(oper);
		if(operList!=null&&operList.size()>0){
			oper = operList.get(0);
			String state = "success";
			state = operService.deleteByNBI(oper);
			
			if(HttpUtil.SUCCESS.equals(state)){
				operService.delete(Oper.class, oper);
			}else{
				returnMsg = "error";
			}	
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/getDomainByUser")
	public void getDomainByUser(HttpServletRequest request,HttpServletResponse response){
		User user =  (User)request.getSession().getAttribute(User.SESSION_USERNAME);
		Map<String,Object> params = new HashMap<String, Object>();
		if(!user.isManager())params.put("sid", user.getOperId());
		List<Oper> operList = operService.findByParams(Oper.class, params);
		if(operList!=null&&operList.size()>0){
			String domains = "";
			if(!user.isManager()){
				domains = operList.get(0).getDomains();
			}else{
				for(Oper oper:operList){
					domains = domains + "," +oper.getDomains();
				}
			}
			String[] domainSplit = domains.split(",");
			List<String> domainListTemp = new ArrayList<String>();
			for(String domain:domainSplit){
				domainListTemp.add(domain);
			}
			List<String> domainList = new ArrayList<String>(new HashSet<String>(domainListTemp));
			String json = JSON.toJSONString(domainList);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
