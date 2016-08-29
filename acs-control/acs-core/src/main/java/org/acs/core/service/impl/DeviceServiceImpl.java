package org.acs.core.service.impl;

import java.beans.Transient;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.acs.core.model.Device;
import org.acs.core.model.DeviceWifi;
import org.acs.core.model.Host;
import org.acs.core.model.ParameterObject;
import org.acs.core.model.Summary;
import org.acs.core.model.WLANConfigurationObject;
import org.acs.core.service.DeviceService;
import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.User;
import org.acs.utils.service.BaseServiceImpl;
import org.acs.utils.tools.ACSConfiguration;
import org.acs.utils.tools.DateUtil;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.NumberUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Repository("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl implements DeviceService{

	@Override
	public List<Device> getAllDevice(User user) {
		// TODO Auto-generated method stub
		String query = null;
		if(!user.isManager()){
				query = "query={\"_oper\":\""+user.getOperId()+"\"}";
		}
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String option = "_id,_model,_registered,_lastInform,summary.serialNumber,summary.manufacturer,summary.productClass,summary.oui,summary.hardwareVersion,summary.softwareVersion,summary.wlanSsid,summary.ip,summary.mac,InternetGatewayDevice.ManagementServer.PeriodicInformInterval";
		
		String requestURL = "";
		if(query==null)requestURL = url+"/?"+NBIStaticVariable.PROJECTION+option;
		else requestURL = url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option;
		String json = HttpUtil.getContentByGet(requestURL);
		JSONArray jsonArray = JSON.parseArray(json);
		List<Device> deviceList = new ArrayList<Device>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jobj = jsonArray.getJSONObject(i);
			Device device = new Device();
			device.setId(jobj.getString("_id"));
			if(jobj.getString("_model")!=null)device.setModules(jobj.getString("_model"));
			else device.setModules("Unknown");
			device.setRegistered(DateUtil.formatByMongod(jobj.getString("_registered")));
			device.setLastInform(DateUtil.formatByMongod(jobj.getString("_lastInform")));
			Summary summary = new Summary();
			summary.setSerialNumber(jobj.getString("summary.serialNumber"));
			summary.setManufacturer(jobj.getString("summary.manufacturer"));
			summary.setProductClass(jobj.getString("summary.productClass"));
			summary.setOui(jobj.getString("summary.oui"));
			if(jobj.getJSONObject("summary.hardwareVersion")!=null)summary.setHardwareVersion(jobj.getJSONObject("summary.hardwareVersion").getString("_value"));
//			else summary.setHardwareVersion(null);
			if(jobj.getJSONObject("summary.softwareVersion")!=null)summary.setSoftwareVersion(jobj.getJSONObject("summary.softwareVersion").getString("_value"));
//			else summary.setSoftwareVersion(null);
			if(jobj.getJSONObject("summary.wlanSsid")!=null)summary.setWlanSsid(jobj.getJSONObject("summary.wlanSsid").getString("_value"));
//			else summary.setWlanSsid(null);
			if(jobj.getJSONObject("summary.ip")!=null)summary.setIp(jobj.getJSONObject("summary.ip").getString("_value"));
			else summary.setIp(null);
			if(jobj.getJSONObject("summary.mac")!=null)summary.setMac(jobj.getJSONObject("summary.mac").getString("_value"));
			else summary.setMac(null);
			
			/*JSONObject informInterval= jobj.getJSONObject("InternetGatewayDevice").getJSONObject("ManagementServer").getJSONObject("PeriodicInformInterval");
			String inform = informInterval.getString("_timestamp").replaceAll("T", " ").replaceAll("Z", "");
			Date informTime = DateUtil.StringToDate(inform, "yyyy-MM-dd HH:mm:ss.SSS");
			int inform_value = informInterval.getIntValue("_value");
			informTime = DateUtil.countSecond(informTime, inform_value, 0);*/
			Date cDate = DateUtil.countHours(new Date(), 20, 1);
//			cDate = DateUtil.countHours(cDate, 4, 1);
			Date bDate = DateUtil.StringToDate(jobj.getString("_lastInform").replace("T", " ").replace("Z", " ").trim(), "yyyy-MM-dd HH:mm:ss");
			//System.out.println("lastInform:"+jobj.getString("_lastInform").replace("T", " ").replace("Z", " ").trim());
			//System.out.println(bDate+"==:=="+cDate);
			if(bDate.after(cDate)){
				device.setOnline(true);
			}else{
				device.setOnline(false);
			}
			/*if(summary.getIp()==null||summary.getMac()==null||"".equals(summary.getIp().trim())||"".equals(summary.getMac().trim())){
				String ipAMac = getDeviceIPAndMACById(jobj.getString("_id"));
				if(ipAMac!=null){
					String[] ipAndMac = ipAMac.split("_");
					if(ipAndMac.length>0){
						summary.setIp(ipAndMac[0]);
					}
					if(ipAndMac.length>1){
						summary.setMac(ipAndMac[1]);
					}
				}
			}*/
			device.setSummary(summary);
			deviceList.add(device);
		}
		return deviceList;
	}
	@Override
	public PageResults<Device> getDevicePage(String query,int currentPage) {
		// TODO Auto-generated method stub
		PageResults<Device> devicePage = new PageResults<Device>();
		devicePage.setCurrentPage(currentPage);
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String option = "_id,_model,_registered,_lastInform,summary.serialNumber,summary.manufacturer,summary.productClass,summary.oui,summary.hardwareVersion,summary.softwareVersion,summary.wlanSsid,summary.ip,summary.mac";
		String limit = "&limit="+PageResults.pageSize+"&skip="+PageResults.pageSize*(currentPage-1);
		if(query!=null&&!"".equals(query))query = query + "&";
		Map<String,Object> resultMap = HttpUtil.getContentByGetOnPage(url+"/?"+query+NBIStaticVariable.PROJECTION+option+limit);
		String json = (String) resultMap.get(HttpUtil.RESULT);
		List<Device> deviceList = getDeviceInfoForJSON(json);
		devicePage.setResults(deviceList);
		long totalCount = (long) resultMap.get(HttpUtil.TOTAL);
		devicePage.setTotalCount(totalCount);
		devicePage.setPageCountBySize(totalCount);
		return devicePage;
	}
	public List<Device> getDeviceInfoForJSON(String json){
		JSONArray jsonArray = JSON.parseArray(json);
		List<Device> deviceList = new ArrayList<Device>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jobj = jsonArray.getJSONObject(i);
			Device device = new Device();
			device.setId(jobj.getString("_id"));
			if(jobj.getString("_model")!=null)device.setModules(jobj.getString("_model"));
			else device.setModules("Unknown");
			device.setRegistered(DateUtil.formatByMongod(jobj.getString("_registered")));
			device.setLastInform(DateUtil.formatByMongod(jobj.getString("_lastInform")));
			Summary summary = new Summary();
			summary.setSerialNumber(jobj.getString("summary.serialNumber"));
			summary.setManufacturer(jobj.getString("summary.manufacturer"));
			summary.setProductClass(jobj.getString("summary.productClass"));
			summary.setOui(jobj.getString("summary.oui"));
			if(jobj.getJSONObject("summary.hardwareVersion")!=null)summary.setHardwareVersion(jobj.getJSONObject("summary.hardwareVersion").getString("_value"));
//			else summary.setHardwareVersion(null);
			if(jobj.getJSONObject("summary.softwareVersion")!=null)summary.setSoftwareVersion(jobj.getJSONObject("summary.softwareVersion").getString("_value"));
//			else summary.setSoftwareVersion(null);
			if(jobj.getJSONObject("summary.wlanSsid")!=null)summary.setWlanSsid(jobj.getJSONObject("summary.wlanSsid").getString("_value"));
//			else summary.setWlanSsid(null);
			if(jobj.getJSONObject("summary.ip")!=null)summary.setIp(jobj.getJSONObject("summary.ip").getString("_value"));
			else summary.setIp(null);
			if(jobj.getJSONObject("summary.mac")!=null)summary.setMac(jobj.getJSONObject("summary.mac").getString("_value"));
			else summary.setMac(null);
			if(summary.getIp()==null||summary.getIp().indexOf("null")>0||summary.getMac()==null||summary.getMac().indexOf("null")>0||"".equals(summary.getIp().trim())||"".equals(summary.getMac().trim())){
				String ipAMac = getDeviceIPAndMACById(jobj.getString("_id"));
				if(ipAMac!=null){
					String[] ipAndMac = ipAMac.split("_");
					if(ipAndMac.length>0){
						if(summary.getIp()==null||summary.getIp().indexOf("null")>0||"".equals(summary.getIp().trim()))summary.setIp(ipAndMac[0]);
					}
					if(ipAndMac.length>1){
						if(summary.getMac()==null||summary.getMac().indexOf("null")>0||"".equals(summary.getMac().trim()))summary.setMac(ipAndMac[1]);
					}
				}
			}
			
			
			Date cDate = DateUtil.countHours(new Date(), 20, 1);
			Date bDate = DateUtil.StringToDate(jobj.getString("_lastInform").replace("T", " ").replace("Z", " ").trim(), "yyyy-MM-dd HH:mm:ss");
			if(bDate.after(cDate)){
				device.setOnline(true);
			}else{
				device.setOnline(false);
			}
			
			device.setSummary(summary);
			deviceList.add(device);
		}
		return deviceList;
	}
	@Override
	public String getDeviceIPAndMACById(String id) {
		// TODO Auto-generated method stub
		id = URLDecoder.decode(id);
		String ip = null;
		String mac = null;
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "InternetGatewayDevice.WANDevice";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		JSONArray jsonArray = JSON.parseArray(json);
		JSONObject jobj = null;
		if(jsonArray.size()>0){
			JSONObject jobj1 = jsonArray.getJSONObject(0).getJSONObject("InternetGatewayDevice").getJSONObject("WANDevice");
			String jkey = getFirstItem(jobj1);
			if(jkey!=null)jobj = jobj1.getJSONObject(jkey).getJSONObject("WANConnectionDevice");
			
			JSONObject sjobj =null;
			if(jobj!=null){
				String firstKey = getFirstItem(jobj);
				if(firstKey!=null){
					 JSONObject firstObject = jobj.getJSONObject(firstKey);
					 int ipNumber = 0;
					 if(firstObject.getJSONObject("WANIPConnectionNumberOfEntries")!=null)ipNumber = Integer.parseInt(firstObject.getJSONObject("WANIPConnectionNumberOfEntries").getString("_value"));
					 int pppNumber = 0;
					 if(firstObject.getJSONObject("WANPPPConnectionNumberOfEntries")!=null)pppNumber = Integer.parseInt(firstObject.getJSONObject("WANPPPConnectionNumberOfEntries").getString("_value"));
					 String parmas = null;
					 if(ipNumber>0){
						 parmas = "WANIPConnection";
					 }
					 if(pppNumber>0){
						 parmas = "WANPPPConnection";
					 }
					 if(parmas!=null){
						 JSONObject wanIPObject = firstObject.getJSONObject(parmas); 
						 sjobj = wanIPObject.getJSONObject(getFirstItem(wanIPObject));
					 }
					if(sjobj!=null){
						ip = sjobj.getJSONObject("ExternalIPAddress").getString("_value");
						System.out.println(sjobj.getJSONObject("ExternalIPAddress"));
						System.out.println(sjobj.getJSONObject("MACAddress"));
						mac = sjobj.getJSONObject("MACAddress").getString("_value");
					}
				}
			}
		}
		
		
		System.out.println(ip+"_"+mac);
		
		return ip+"_"+mac;
	}
	private String getFirstItem(JSONObject jsonObject){
		String firstKey = null;
		if(jsonObject!=null){
			for (String key : jsonObject.keySet()) {
				if(NumberUtils.IsObjNumber(key)){
					firstKey = key;
				}
			}
		}
		return firstKey;
	}
	@Override
	public String getDeviceById(String id) {
		// TODO Auto-generated method stub
		id = URLDecoder.decode(id);
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String query = "query={\"_id\":\""+id+"\"}";
		/*String option1 = "_id,_model,_tags,_registered,_lastInform,summary.serialNumber,summary.manufacturer,summary.productClass,summary.oui,summary.hardwareVersion,summary.softwareVersion,summary.wlanSsid,summary.ip,summary.mac";
		String option2 = "InternetGatewayDevice";*/
		String json = HttpUtil.getContentByGet(url+"/?"+query);
//		List<ParameterObject> parameterList = new ArrayList<ParameterObject>();
		JSONArray jsonArray = JSON.parseArray(json);
		
		List<ParameterObject> nameList = new ArrayList<ParameterObject>();
		List<ParameterObject> usernameList = new ArrayList<ParameterObject>();
		List<ParameterObject> passwordList = new ArrayList<ParameterObject>();
		
		List<ParameterObject> DNSServersList = new ArrayList<ParameterObject>();
		List<ParameterObject> modulationTypeList = new ArrayList<ParameterObject>();
		List<ParameterObject> upstreamCurrRateList = new ArrayList<ParameterObject>();
		List<ParameterObject> wandslStatusList = new ArrayList<ParameterObject>();
		List<ParameterObject> downstreamCurrRateList = new ArrayList<ParameterObject>();
		List<ParameterObject> upstreamMaxRateList = new ArrayList<ParameterObject>();
		List<ParameterObject> downstreamMaxRateList = new ArrayList<ParameterObject>();
		boolean flag = false;
		
		List<ParameterObject> ipList = new ArrayList<ParameterObject>();
		List<ParameterObject> macList = new ArrayList<ParameterObject>();
		Device device = new Device();
		Summary summary = new Summary();
		if(jsonArray!=null&&jsonArray.size()>0){
			
			JSONObject jObj = jsonArray.getJSONObject(0);
			List<ParameterObject> parameterList = analysisParameters(jObj);
			//System.out.println("SIZE:"+parameterList.size());
			
			device.setId(jObj.getString("_id"));
			if(jObj.getString("_model")!=null)device.setModules(jObj.getString("_model"));
			else device.setModules("Unknown");
			device.setRegistered(DateUtil.formatByMongod(jObj.getString("_registered")));
			device.setLastInformStr(jObj.getString("_lastInform"));
			summary.setSerialNumber(jObj.getString("summary.serialNumber"));
			summary.setManufacturer(jObj.getString("summary.manufacturer"));
			summary.setProductClass(jObj.getString("summary.productClass"));
			
			ParameterObject p = new ParameterObject();
			if(jObj.getJSONObject("summary.dnsServers")!=null){
				p.setKey("summary.dnsServers");
				p.setValue(jObj.getJSONObject("summary.dnsServers").getString("_value"));
				p.setType("xsd:string");
				device.setDns(p);
			}
			if(p.getValue()==null||"".equals(p.getValue().trim()))flag = true;
			
			summary.setOui(jObj.getString("summary.oui"));
			if(jObj.getJSONObject("summary.hardwareVersion")!=null)summary.setHardwareVersion(jObj.getJSONObject("summary.hardwareVersion").getString("_value"));
//			else summary.setHardwareVersion(null);
			if(jObj.getJSONObject("summary.softwareVersion")!=null)summary.setSoftwareVersion(jObj.getJSONObject("summary.softwareVersion").getString("_value"));
//			else summary.setSoftwareVersion(null);
			if(jObj.getJSONObject("summary.wlanSsid")!=null)summary.setWlanSsid(jObj.getJSONObject("summary.wlanSsid").getString("_value"));
//			else summary.setWlanSsid(null);
			if(jObj.getJSONObject("summary.ip")!=null)summary.setIp(jObj.getJSONObject("summary.ip").getString("_value"));
			else summary.setIp(null);
			if(jObj.getJSONObject("summary.mac")!=null)summary.setMac(jObj.getJSONObject("summary.mac").getString("_value"));
			else summary.setMac(null);
			device.setSummary(summary);
			
			for(ParameterObject pObj:parameterList){
				String key = pObj.getKey();
				if(key.startsWith("InternetGatewayDevice.WANDevice.")){
					if(key.toLowerCase().indexOf(".name")>=0)nameList.add(pObj);
					else if(key.toLowerCase().indexOf(".username")>=0)usernameList.add(pObj);
					else if(key.toLowerCase().indexOf(".password")>=0)passwordList.add(pObj);
					else if(key.indexOf(".WANDSLInterfaceConfig.ModulationType")>=0)modulationTypeList.add(pObj);
					else if(key.indexOf(".UpstreamCurrRate")>=0)upstreamCurrRateList.add(pObj);
					else if(key.indexOf(".DownstreamCurrRate")>=0)downstreamCurrRateList.add(pObj);
					else if(key.indexOf(".WANDSLInterfaceConfig.Status")>=0)wandslStatusList.add(pObj);
					else if(key.indexOf(".UpstreamMaxRate")>=0)upstreamMaxRateList.add(pObj);
					else if(key.indexOf(".DownstreamMaxRate")>=0)downstreamMaxRateList.add(pObj);
					else if(key.indexOf(".DNSServers")>=0&&flag)DNSServersList.add(pObj);
					else if(key.indexOf(".WANConnectionDevice")>=0&&key.indexOf(".ExternalIPAddress")>=0&&(key.indexOf(".WANIPConnection")>=0||key.indexOf(".WANPPPConnection")>=0))ipList.add(pObj);
					else if(key.indexOf(".WANConnectionDevice")>=0&&key.indexOf(".MACAddress")>=0&&(key.indexOf(".WANIPConnection")>=0||key.indexOf(".WANPPPConnection")>=0)&&key.indexOf(".MACAddressOverride")<0)macList.add(pObj);
				}else if(key.startsWith("InternetGatewayDevice.ManagementServer.")){
					if(key.toLowerCase().indexOf(".connectionrequesturl")>=0)device.setConnectionRequestURL(pObj.getValue());
					else if(key.toLowerCase().indexOf(".periodicinforminterval")>=0)device.setInformInterval(pObj.getValue());
				}else if(key.startsWith("InternetGatewayDevice.DeviceInfo.")){
					if(key.toLowerCase().indexOf(".uptime")>=0){
						device.setUpTime(pObj.getValue());
					}
				}
			}
		}
		
		device.analyzeName(nameList);
		device.analyzeUsernameAndPassword(usernameList, passwordList);
		if(flag)device.analyzeDNS(DNSServersList);
		device.analyzeModulationType(modulationTypeList);
		device.analyzeCurrRate(upstreamCurrRateList, downstreamCurrRateList,wandslStatusList);
		device.analyzeMaxRate(upstreamMaxRateList, downstreamMaxRateList);
		device.analyzeIP(ipList);
		device.analyzeMac(macList);
		//System.out.println(JSON.toJSONString(device));
		/*String ip = jsonArray.getJSONObject(0).getJSONObject("summary.ip").getString("_value");
		String mac = jsonArray.getJSONObject(0).getJSONObject("summary.mac").getString("_value");
		if(ip==null||"".equals(ip)||mac==null||"".equals(mac)){
			String ipAMac = getDeviceIPAndMACById(id);
			if(ipAMac!=null){
				String[] ipAndMac = ipAMac.split("_");
				if(ipAndMac.length>=2){
				//	ipAndMac[0];
					//summary.setIp(ipAndMac[1]);
				}
			}
		}*/
//		System.out.println(json);
		
		return JSON.toJSONString(device,SerializerFeature.DisableCircularReferenceDetect);
	}
	@Override
	public String getDeviceUsernameById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "InternetGatewayDevice.WANDevice";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		return json;
	}

	@Override
	public String addTagById(String id, String tagName) {
		// TODO Auto-generated method stub
		//http://localhost:7557/devices/202BC1-BM632w-0000000/tags/testing\
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TAG+"/"+tagName;
		String state = HttpUtil.getContentByPost(url, null);
		return state;
	}

	@Override
	public String searchTagById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "_tags";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		return json;
	}

	@Override
	public String removeTagById(String id, String tagName) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE+"/"+id+NBIStaticVariable.TAG+"/"+tagName;
		String state = HttpUtil.getContentByDelete(url);
		return state;
	}


	@Override
	public String showParametersById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String query = "query={\"_id\":\""+id+"\"}";
		String json = HttpUtil.getContentByGet(url+"/?"+query);
		String resultJson = null;
		//System.out.println(json);
		
		JSONArray jsonArray = JSON.parseArray(json);
		
		if(jsonArray!=null&&jsonArray.size()>0){
			JSONObject jObj = jsonArray.getJSONObject(0);
			List<ParameterObject> parameterList = analysisParameters(jObj);
			resultJson = JSON.toJSONString(parameterList);
		}
		
		return resultJson;
	}
	@Override
	public String getDeviceHostById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "InternetGatewayDevice.LANDevice";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		JSONArray jsonArray = JSON.parseArray(json);
		List<String> associatedMACList = new ArrayList<String>();
		List<Host> hostList = new ArrayList<Host>();
		if(jsonArray!=null&&jsonArray.size()>0){
			JSONObject jObj = jsonArray.getJSONObject(0);
			
			JSONObject jobj1 = jObj.getJSONObject("InternetGatewayDevice").getJSONObject("LANDevice");
			String jkey = getFirstItem(jobj1);
			
			JSONObject hosts = null;
			if(jkey!=null)hosts = jobj1.getJSONObject(jkey).getJSONObject("Hosts").getJSONObject("Host");
			if(hosts!=null){
				for (String key : hosts.keySet()) {
					if(NumberUtils.IsObjNumber(key)){
						JSONObject hostObj = hosts.getJSONObject(key);
						Host host = new Host();
						host.setIp(hostObj.getJSONObject("IPAddress").getString("_value"));
						host.setMac(hostObj.getJSONObject("MACAddress").getString("_value"));
						host.setName(hostObj.getJSONObject("HostName").getString("_value"));
						host.setActive(Boolean.parseBoolean(hostObj.getJSONObject("Active").getString("_value")));
						host.setType(hostObj.getJSONObject("InterfaceType").getString("_value"));
						hostList.add(host);
					}
				}
			}
			
			List<ParameterObject> parameterList = analysisParameters(jObj);
			//System.out.println("SIZE:"+parameterList.size());
			for(ParameterObject pObj:parameterList){
				String key = pObj.getKey();
				if(key.indexOf(".WLANConfiguration")>=0){
					if(key.indexOf(".AssociatedDevice")>=0){
						if(key.indexOf(".AssociatedDeviceMACAddress")>=0){
							if(pObj.getValue()!=null&&!"".equals(pObj.getValue().trim()))associatedMACList.add(pObj.getValue());
						}
					}
				}
			}
			if(associatedMACList.size()>0){
				for(Host h:hostList){
					for(String mac:associatedMACList){
						if(h.getMac().toLowerCase().trim().equals(mac.toLowerCase()))h.setType("WIFI");
					}
					if(h.getType().startsWith("802"))h.setType("WIFI");
				}
			}else{
				for(Host h:hostList){
					if(h.getType().startsWith("802"))h.setType("WIFI");
				}
			}
			
		}
		String resultJson = JSON.toJSONString(hostList);
		return resultJson;
	}
	@Override
	public String getDeviceWifiById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "InternetGatewayDevice.LANDevice";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		List<WLANConfigurationObject> WLANConfigurationList = new ArrayList<WLANConfigurationObject>();
		JSONArray jsonArray = JSON.parseArray(json);
		if(jsonArray!=null&&jsonArray.size()>0){
			JSONObject jObj = jsonArray.getJSONObject(0);
			JSONObject jobj1 = jObj.getJSONObject("InternetGatewayDevice").getJSONObject("LANDevice");
			String jkey = getFirstItem(jobj1);
			
			JSONObject WLANConfigurations = null;
			if(jkey!=null)WLANConfigurations = jobj1.getJSONObject(jkey).getJSONObject("WLANConfiguration");
			
			if(WLANConfigurations!=null){
				for (String key : WLANConfigurations.keySet()) {
					if(NumberUtils.IsObjNumber(key)){
						JSONObject WLANConfiguration = WLANConfigurations.getJSONObject(key);
						String status = "";
						boolean enable = false;
						if(WLANConfiguration.getJSONObject("Status")!=null)status = WLANConfiguration.getJSONObject("Status").getString("_value");
						if(WLANConfiguration.getJSONObject("Enable")!=null)enable = WLANConfiguration.getJSONObject("Enable").getBoolean("_value");
						if("up".equals(status.toLowerCase())&&enable){
							WLANConfigurationObject wifiConfig = new WLANConfigurationObject();
							
							if(WLANConfiguration.getJSONObject("SSID")!=null){
								ParameterObject ssidParameterObject = new ParameterObject();
								ssidParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".SSID");
								ssidParameterObject.setValue(WLANConfiguration.getJSONObject("SSID").getString("_value"));
								ssidParameterObject.setType(WLANConfiguration.getJSONObject("SSID").getString("_type"));
								ssidParameterObject.setWritable(WLANConfiguration.getJSONObject("SSID").getBoolean("_writable"));
								wifiConfig.setSsid(ssidParameterObject);
							}
							if(WLANConfiguration.getJSONObject("Standard")!=null){
								ParameterObject standardParameterObject = new ParameterObject();
								standardParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".Standard");
								standardParameterObject.setValue(WLANConfiguration.getJSONObject("Standard").getString("_value"));
								standardParameterObject.setType(WLANConfiguration.getJSONObject("Standard").getString("_type"));
								standardParameterObject.setWritable(WLANConfiguration.getJSONObject("Standard").getBoolean("_writable"));
								wifiConfig.setStandard(standardParameterObject);
							}
							if(WLANConfiguration.getJSONObject("Channel")!=null){
								ParameterObject channelParameterObject = new ParameterObject();
								channelParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".Channel");
								channelParameterObject.setValue(WLANConfiguration.getJSONObject("Channel").getString("_value"));
								channelParameterObject.setType(WLANConfiguration.getJSONObject("Channel").getString("_type"));
								channelParameterObject.setWritable(WLANConfiguration.getJSONObject("Channel").getBoolean("_writable"));
								wifiConfig.setChannel(channelParameterObject);
							}
							if(WLANConfiguration.getJSONObject("PossibleChannels")!=null){
								ParameterObject possibleChannelsParameterObject = new ParameterObject();
								possibleChannelsParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".PossibleChannels");
								possibleChannelsParameterObject.setValue(WLANConfiguration.getJSONObject("PossibleChannels").getString("_value"));
								possibleChannelsParameterObject.setType(WLANConfiguration.getJSONObject("PossibleChannels").getString("_type"));
								possibleChannelsParameterObject.setWritable(WLANConfiguration.getJSONObject("PossibleChannels").getBoolean("_writable"));
								wifiConfig.setPossibleChannels(possibleChannelsParameterObject);
							}
							if(WLANConfiguration.getJSONObject("PreSharedKey")!=null){
								JSONObject preSharedKeyObj = WLANConfiguration.getJSONObject("PreSharedKey");
								String preSharedKeyObjkey = getFirstItem(preSharedKeyObj);
								
								JSONObject keyPassphraseObj = WLANConfiguration.getJSONObject("PreSharedKey").getJSONObject(preSharedKeyObjkey).getJSONObject("KeyPassphrase");
								ParameterObject preSharedKeyParameterObject = new ParameterObject();
//								preSharedKeyParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".PreSharedKey."+preSharedKeyObjkey+".KeyPassphrase");
								preSharedKeyParameterObject.setKey("summary.wlanPassphrase");
								preSharedKeyParameterObject.setValue(keyPassphraseObj.getString("_value"));
								preSharedKeyParameterObject.setType(keyPassphraseObj.getString("_type"));
								preSharedKeyParameterObject.setWritable(keyPassphraseObj.getBoolean("_writable"));
								wifiConfig.setPreSharedKey(preSharedKeyParameterObject);
							}
							if(WLANConfiguration.getJSONObject("WPAEncryptionModes")!=null){
								ParameterObject wPAEncryptionModesParameterObject = new ParameterObject();
								wPAEncryptionModesParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".WPAEncryptionModes");
								wPAEncryptionModesParameterObject.setValue(WLANConfiguration.getJSONObject("WPAEncryptionModes").getString("_value"));
								wPAEncryptionModesParameterObject.setType(WLANConfiguration.getJSONObject("WPAEncryptionModes").getString("_type"));
								wPAEncryptionModesParameterObject.setWritable(WLANConfiguration.getJSONObject("WPAEncryptionModes").getBoolean("_writable"));
								wifiConfig.setwPAEncryptionModes(wPAEncryptionModesParameterObject);
							}
							if(WLANConfiguration.getJSONObject("WPAAuthenticationMode")!=null){
								ParameterObject wPAAuthenticationModeParameterObject = new ParameterObject();
								wPAAuthenticationModeParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".WPAAuthenticationMode");
								wPAAuthenticationModeParameterObject.setValue(WLANConfiguration.getJSONObject("WPAAuthenticationMode").getString("_value"));
								wPAAuthenticationModeParameterObject.setType(WLANConfiguration.getJSONObject("WPAAuthenticationMode").getString("_type"));
								wPAAuthenticationModeParameterObject.setWritable(WLANConfiguration.getJSONObject("WPAAuthenticationMode").getBoolean("_writable"));
								wifiConfig.setwPAAuthenticationMode(wPAAuthenticationModeParameterObject);
							}
							if(WLANConfiguration.getJSONObject("BasicEncryptionModes")!=null){
								ParameterObject basicEncryptionModesParameterObject = new ParameterObject();
								basicEncryptionModesParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".BasicEncryptionModes");
								basicEncryptionModesParameterObject.setValue(WLANConfiguration.getJSONObject("BasicEncryptionModes").getString("_value"));
								basicEncryptionModesParameterObject.setType(WLANConfiguration.getJSONObject("BasicEncryptionModes").getString("_type"));
								basicEncryptionModesParameterObject.setWritable(WLANConfiguration.getJSONObject("BasicEncryptionModes").getBoolean("_writable"));
								wifiConfig.setBasicEncryptionModes(basicEncryptionModesParameterObject);
							}
							if(WLANConfiguration.getJSONObject("BasicAuthenticationMode")!=null){
								ParameterObject basicAuthenticationModeParameterObject = new ParameterObject();
								basicAuthenticationModeParameterObject.setKey("InternetGatewayDevice.LANDevice."+jkey+".WLANConfiguration."+key+".BasicAuthenticationMode");
								basicAuthenticationModeParameterObject.setValue(WLANConfiguration.getJSONObject("BasicAuthenticationMode").getString("_value"));
								basicAuthenticationModeParameterObject.setType(WLANConfiguration.getJSONObject("BasicAuthenticationMode").getString("_type"));
								basicAuthenticationModeParameterObject.setWritable(WLANConfiguration.getJSONObject("BasicAuthenticationMode").getBoolean("_writable"));
								wifiConfig.setBasicAuthenticationMode(basicAuthenticationModeParameterObject);
							}
							WLANConfigurationList.add(wifiConfig);
						}
					}
				}
			}
		}
		json = JSON.toJSONString(WLANConfigurationList);
		return json;
	}
	public List<ParameterObject> analysisParameters(JSONObject jObj){
		List<ParameterObject> parameterList = new ArrayList<ParameterObject>();
		listJSON(jObj,"",parameterList);
		return parameterList;
	}
	public void listJSON(JSONObject jObj,String ukey,List<ParameterObject> parameterList){
		Iterator<String> it = jObj.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(!key.startsWith("X_")){
				Object obj = jObj.get(key);
				if(obj instanceof JSONObject){
					JSONObject val = (JSONObject)obj;
					if(val.containsKey("_type")&&val.containsKey("_value")&&val.containsKey("_writable")){
						ParameterObject pObj = new ParameterObject();
						pObj.setKey((ukey+"."+key).substring(1));
						pObj.setType(val.getString("_type"));
						pObj.setValue(val.getString("_value"));
						pObj.setWritable(val.getBooleanValue("_writable"));
						//System.out.println(pObj.getKey()+":"+pObj.getValue());
						parameterList.add(pObj);
					}else{
						listJSON((JSONObject)obj,ukey+"."+key,parameterList);
					}
				}
			}
			//listJSON(val,key,parameterList);
		}
		//listJSON
		/*if(json["_writable"]!=undefined&&json["_type"]!=undefined&&json["_value"]!=undefined){
			parameterList.push(createParameter(ukey,json["_value"],json["_type"],json["_writable"]));
		}
	    for(var key in json){
	    	if(key.indexOf("X_")!=0&&typeof(json[key])=="object"){
	    		if(ukey==null||ukey=="")listJSON(json[key],key);
	    		else listJSON(json[key],ukey + "."+key);
	    	}
	    } */ 
	}
	@Override
	public String getDeviceModuleById(String id) {
		// TODO Auto-generated method stub
		String module = null;
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.DEVICE;
		String query = "query={\"_id\":\""+id+"\"}";
		String option = "_model";
		String json = HttpUtil.getContentByGet(url+"/?"+query+"&"+NBIStaticVariable.PROJECTION+option);
		JSONArray jsonArray = JSON.parseArray(json);
		if(jsonArray!=null&&jsonArray.size()>0){
			JSONObject jobj = jsonArray.getJSONObject(0);
			if(jobj.getString("_model")!=null)module = jobj.getString("_model");
		}
		return module;
	}
}
