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

import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.service.DeviceGroupService;
import org.acs.utils.service.OperService;
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
@RequestMapping("deviceGroup")
public class DeviceGroupController {

	@Autowired
	private DeviceGroupService deviceGroupService;
	
	
}
