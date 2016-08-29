package org.acs.utils.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.service.UserService;
import org.acs.utils.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String userLogin(User user,HttpServletRequest request,HttpSession session,RedirectAttributes attr){
		user = userService.isUserExist(user);
		if(user == null){
			attr.addAttribute(User.LOGIN_MEG,User.UNSUCCESS_INFO);
			return "redirect:/";
		}else{
			session.setAttribute(User.SESSION_USERNAME, user);
			return "main";
		}
	}
	@RequestMapping(value = "loginOut")
	public String userLoginOut(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute(User.SESSION_USERNAME);
		if(session.getAttribute(User.SESSION_USERNAME)!=null){
			session.removeAttribute("User.SESSION_USERNAME");
		}
		return "redirect:/";
		
	}
	@RequestMapping("/getAllUser")  
	public String getAllUser(ModelMap modelMap){
		//List<User> userList = userService.findAll(User.class);
		//modelMap.addAttribute("userList", userList);
//		for(User user:userList){
//			System.out.println(user.getSid()+":"+user.getUsername()+":"+user.getPassword());
//		}
		return "user_manager";
	}
	@RequestMapping("/getAllUserJson")  
	public void getAllUserJson(HttpServletResponse response){
		List<User> userList = userService.findAll(User.class);
		String resultJson = JSON.toJSONString(userList);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getAllUserPageJson")  
	public void getAllUserPageJson(String currentPage,String queryValue,HttpServletResponse response){
		PageResults<User> userPage = userService.findAllUserPage(currentPage,queryValue);
//		List<User> userList = userService.findAll(User.class);
		String resultJson = JSON.toJSONString(userPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/operIndex")
	public String operIndex(HttpServletResponse response){
		return "oper_manager";
	}
	@RequestMapping("/getAllOper")
	public void getAllOper(HttpServletResponse response){
		List<Oper> operList = userService.findAllOper();
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
	@RequestMapping("/addUser")  
    public void saveUser(User user,HttpServletResponse response){
        user.setPassword(MD5Util.string2MD5(User.DEFAULT_PASSWORD));  
        if(user.isManager()||"".equals(user.getOper().trim())){
        	user.setOper(null);
        }
        if(user.getSid()==0){
        	userService.insert(User.class, user);
        }else{
        	user.setPassword(null);
        	user.setUsername(null);
        	if(user.getOper()!=null)user.setOperId(Integer.parseInt(user.getOper()));
        	userService.update(User.class, user);
        }
        
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@RequestMapping("/updateUserBySid")  
	public void updateUserBySid(long sid,HttpServletResponse response){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", sid);
		
		List<User> userList = userService.findByParams(User.class, params);
		User user = null;
		String msg = "unsuccess";
		if(userList!=null&&userList.size()>0){
			user = userList.get(0);
			msg = "success";
		}
		List<Oper> operList = userService.findAllOper();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("msg", msg);
		resultMap.put("user", user);
		resultMap.put("operList", operList);
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
	@RequestMapping("/refreshUser")  
	public void refreshUser(long sid,HttpServletResponse response){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", sid);
		
		List<User> userList = userService.findByParams(User.class, params);
		User user = null;
		String msg = "unsuccess";
		if(userList!=null&&userList.size()>0){
			user = userList.get(0);
			user.setPassword(MD5Util.string2MD5(User.DEFAULT_PASSWORD));
			userService.update(User.class, user);
			msg = "success";
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("msg", msg);
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
	@RequestMapping("/deleteUser")  
	public void deleteUser(User user,HttpServletResponse response){
		userService.delete(User.class, user);
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updataPassword")  
	public void updataPassword(String oldPassword,String newPassword,String confirmPassword,HttpSession session,HttpServletResponse response){
		String msg = "success";
		if("".equals(newPassword.trim()))msg = "The new password can not be empty";
		if("".equals(confirmPassword.trim()))msg = "Two passwords are not consistent!";
		if(!newPassword.equals(confirmPassword)){
			msg = "Two passwords are not consistent!";
		}
		User user = (User) session.getAttribute(User.SESSION_USERNAME);
		user = userService.findByPrimaryKey(User.class, user.getSid());
		if(!user.getPassword().equals(MD5Util.string2MD5(oldPassword))){
			msg = "Login password error!";
		}else{
			user.setPassword(MD5Util.string2MD5(newPassword));
			userService.update(User.class, user);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("msg", msg);
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
	
	@RequestMapping("/countUser")  
	public void countUser(){
		long userList = userService.findCount(User.class, null);
		System.out.println(userList+"==============");
	}
	
}
