package org.acs.core.service.impl;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.acs.core.dao.ModulesDao;
import org.acs.core.model.Device;
import org.acs.core.model.Modules;
import org.acs.core.model.Summary;
import org.acs.core.service.ModulesService;
import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseServiceImpl;
import org.acs.utils.tools.ACSConfiguration;
import org.acs.utils.tools.DateUtil;
import org.acs.utils.tools.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Repository("modulesService")
public class ModulesServiceImpl extends BaseServiceImpl implements ModulesService{

	@Autowired
	private ModulesDao modulesDao;

	@Override
	public String insertByNBI(Modules modules) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.MODELS+"/"+modules.getName();
		String params = "{ \"oui\": \""+URLEncoder.encode(modules.getOui())+"\"";
		if(modules.getProductClass()!=null&&!"".equals(modules.getProductClass().trim())){
			params = params + ",\"productClass\": \"" +URLEncoder.encode(modules.getProductClass())+"\"";
		}
		if(modules.getManufacturer()!=null&&!"".equals(modules.getManufacturer().trim())){
			params = params + ",\"manufacture\": \"" +URLEncoder.encode(modules.getManufacturer())+"\"";
		}
		params = params + "}";
		String state = HttpUtil.getContentByPut(url, params);
		return state;
	}

	@Override
	public String deleteByNBI(Modules modules) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.MODELS+"/"+modules.getName();
		String state = HttpUtil.getContentByDelete(url);
		return state;
	}
	@Override
	public List<Device> findDeviceByModules(String modulesName){
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/?query={\"_model\":\""+modulesName+"\"}"+"&"+NBIStaticVariable.PROJECTION+"_id";
		String modulesJson = HttpUtil.getContentByGet(url);
		JSONArray jsonArray = JSON.parseArray(modulesJson);
		List<Device> deviceList = new ArrayList<Device>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jobj = jsonArray.getJSONObject(i);
			Device device = new Device();
			device.setId(jobj.getString("_id"));
			//device.setRegistered(DateUtil.formatByMongod(jobj.getString("_registered")));
			//device.setLastInform(DateUtil.formatByMongod(jobj.getString("_lastInform")));
			/*Summary summary = new Summary();
			if(jobj.getString("summary.serialNumber")!=null)summary.setSerialNumber(jobj.getString("summary.serialNumber"));
			if(jobj.getString("summary.manufacturer")!=null)summary.setManufacturer(jobj.getString("summary.manufacturer"));
			if(jobj.getString("summary.productClass")!=null)summary.setProductClass(jobj.getString("summary.productClass"));
			if(jobj.getString("summary.oui")!=null)summary.setOui(jobj.getString("summary.oui"));
			if(jobj.getJSONObject("summary.hardwareVersion")!=null)summary.setHardwareVersion(jobj.getJSONObject("summary.hardwareVersion").getString("_value"));
			if(jobj.getJSONObject("summary.softwareVersion")!=null)summary.setSoftwareVersion(jobj.getJSONObject("summary.softwareVersion").getString("_value"));
			if(jobj.getJSONObject("summary.wlanSsid")!=null)summary.setWlanSsid(jobj.getJSONObject("summary.wlanSsid").getString("_value"));
			if(jobj.getJSONObject("summary.ip")!=null)summary.setIp(jobj.getJSONObject("summary.ip").getString("_value"));
			if(jobj.getJSONObject("summary.mac")!=null)summary.setMac(jobj.getJSONObject("summary.mac").getString("_value"));
			device.setSummary(summary);*/
			deviceList.add(device);
		}
		return deviceList;
	}
	@Override
	public String findParameterByModules(String modulesName){
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.MODELS+"/?query={\"name\":\""+modulesName+"\"}";
		String modulesJson = HttpUtil.getContentByGet(url);
		JSONArray jsonArray = JSON.parseArray(modulesJson);
		String resultJson = "";
		if(jsonArray!=null&&jsonArray.size()>0){
			JSONObject jobj = jsonArray.getJSONObject(0);
			resultJson = JSON.toJSONString(jsonArray);
		}
		return resultJson;
	}

	@Override
	public PageResults<Modules> findModulesByParamsPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		PageResults<Modules> modulesPage = new PageResults<Modules>();
		modulesPage.setResults(modulesDao.findByParams(Modules.class, params));
		long totalCount = modulesDao.findCount(Modules.class, params);
		modulesPage.setTotalCount(totalCount);
		modulesPage.setPageCountBySize(totalCount);
		return modulesPage;
	}
	
}
