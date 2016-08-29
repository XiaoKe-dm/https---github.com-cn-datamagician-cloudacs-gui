package org.acs.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Device {

	private String id;
	private Date registered;
	private Date lastInform;
	private String lastInformStr;
	private Summary summary;
	private String modules;
	private List<String> tags;
	private boolean isOnline;
	private ParameterObject username;
	private ParameterObject password;
	private String ssid;
	private ParameterObject name;
	private ParameterObject dns;
	private String modulationType;
	private String upCurrRate;
	private String downCurrRate;
	private String upMaxRate;
	private String downMaxRate;
	private String informInterval;
	private String connectionRequestURL;
	private String upTime;
	private List<ParameterObject> usernameList;
	private List<ParameterObject> passwordList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegistered() {
		return registered;
	}
	public void setRegistered(Date registered) {
		this.registered = registered;
	}
	public Date getLastInform() {
		return lastInform;
	}
	public void setLastInform(Date lastInform) {
		this.lastInform = lastInform;
	}
	public String getLastInformStr() {
		return lastInformStr;
	}
	public void setLastInformStr(String lastInformStr) {
		this.lastInformStr = lastInformStr;
	}
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	public ParameterObject getUsername() {
		return username;
	}
	public void setUsername(ParameterObject username) {
		this.username = username;
	}
	public ParameterObject getPassword() {
		return password;
	}
	public void setPassword(ParameterObject password) {
		this.password = password;
	}
	public ParameterObject getName() {
		return name;
	}
	public void setName(ParameterObject name) {
		this.name = name;
	}
	public ParameterObject getDns() {
		return dns;
	}
	public void setDns(ParameterObject dns) {
		this.dns = dns;
	}
	public String getModulationType() {
		return modulationType;
	}
	public void setModulationType(String modulationType) {
		this.modulationType = modulationType;
	}
	public String getUpCurrRate() {
		return upCurrRate;
	}
	public void setUpCurrRate(String upCurrRate) {
		this.upCurrRate = upCurrRate;
	}
	public String getDownCurrRate() {
		return downCurrRate;
	}
	public void setDownCurrRate(String downCurrRate) {
		this.downCurrRate = downCurrRate;
	}
	public String getUpMaxRate() {
		return upMaxRate;
	}
	public void setUpMaxRate(String upMaxRate) {
		this.upMaxRate = upMaxRate;
	}
	public String getDownMaxRate() {
		return downMaxRate;
	}
	public void setDownMaxRate(String downMaxRate) {
		this.downMaxRate = downMaxRate;
	}
	public String getInformInterval() {
		return informInterval;
	}
	public void setInformInterval(String informInterval) {
		this.informInterval = informInterval;
	}
	public String getConnectionRequestURL() {
		return connectionRequestURL;
	}
	public void setConnectionRequestURL(String connectionRequestURL) {
		this.connectionRequestURL = connectionRequestURL;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	public List<ParameterObject> getUsernameList() {
		return usernameList;
	}
	public void setUsernameList(List<ParameterObject> usernameList) {
		this.usernameList = usernameList;
	}
	public List<ParameterObject> getPasswordList() {
		return passwordList;
	}
	public void setPasswordList(List<ParameterObject> passwordList) {
		this.passwordList = passwordList;
	}
	public void analyzeName(List<ParameterObject> nameList){
		if(nameList!=null&&nameList.size()>0)setName(nameList.get(0));
	}
	public void analyzeUsernameAndPassword(List<ParameterObject> usernameList,List<ParameterObject> passwordList){
		setUsernameList(usernameList);
		setPasswordList(passwordList);
		ParameterObject _username = null;
		ParameterObject _password = null;
		for(ParameterObject upo:usernameList){
			if(upo.getValue()!=null&&!"".equals(upo.getValue())){
				for(ParameterObject ppo:passwordList){
					if(upo.getKey().replace(".Username", "").equals(ppo.getKey().replace(".Password", ""))&&ppo.getValue()!=null&&!"".equals(ppo.getValue())){
						_username = upo;
						_password = ppo;
						break;
					}
				}
			}
		}
		if(_password==null&&usernameList!=null&&usernameList.size()>0){
			_username = usernameList.get(0);
			if(passwordList!=null&&passwordList.size()>0)_password = passwordList.get(0);
		}
		setUsername(_username);
		setPassword(_password);
	}
	public void analyzeDNS(List<ParameterObject> DNSServersList){
		if(DNSServersList!=null&&DNSServersList.size()>0){
			for(ParameterObject pobj:DNSServersList){
				if(pobj.getValue()!=null&&!"".equals(pobj.getValue().trim()))setDns(pobj);
			}
		}
	}
	public void analyzeModulationType(List<ParameterObject> modulationTypeList){
		if(modulationTypeList!=null&&modulationTypeList.size()>0){
			for(ParameterObject pobj:modulationTypeList){
				if(pobj.getValue()!=null&&!"".equals(pobj.getValue().trim()))setModulationType(pobj.getValue().trim());
			}
		}
	}
	public void analyzeCurrRate(List<ParameterObject> upstreamCurrRateList,List<ParameterObject> downstreamCurrRateList,List<ParameterObject> wandslStatusList){
		String _upCurrRate = "";
		String _downCurrRate = "";
		String key = "";
		for(ParameterObject wpo:wandslStatusList){
			if("up".equals(wpo.getValue().toLowerCase().trim())){
				key = wpo.getKey();
			}
		}
		if(!"".equals(key)){
			key = key.replaceAll(".Status", "");
			
			String _upKey = key + ".UpstreamCurrRate";
			String _downKey = key + ".DownstreamCurrRate";
			
			_upCurrRate = getCurrRate(_upKey,upstreamCurrRateList);
			_downCurrRate = getCurrRate(_downKey,downstreamCurrRateList);
		}
		
		
		/*for(ParameterObject upo:upstreamCurrRateList){
			if(upo.getValue()!=null&&!"".equals(upo.getValue())){
				for(ParameterObject dpo:downstreamCurrRateList){
					if(upo.getKey().indexOf("1")>=0&&dpo.getValue().indexOf("1")>=0){
						_upCurrRate = upo.getValue();
						_downCurrRate = dpo.getValue();
					}
				}
			}
		}
		if("".equals(_downCurrRate)&&upstreamCurrRateList!=null&&upstreamCurrRateList.size()>0){
			_upCurrRate = upstreamCurrRateList.get(0).getValue();
		}*/
		if("".equals(_upCurrRate))_upCurrRate = "0";
		if("".equals(_downCurrRate))_downCurrRate = "0";
		if(!"".equals(_upCurrRate)&&Integer.parseInt(_upCurrRate)>=100000)_upCurrRate = Integer.parseInt(_upCurrRate)/1000+"";
		if(!"".equals(_downCurrRate)&&Integer.parseInt(_downCurrRate)>=100000)_downCurrRate = Integer.parseInt(_downCurrRate)/1000+"";
		setUpCurrRate(_upCurrRate);
		setDownCurrRate(_downCurrRate);
	}
	private String getCurrRate(String key,List<ParameterObject> currRateList){
		String _currRate = "";
		for(ParameterObject upo:currRateList){
			if(key.equals(upo.getKey())){
				_currRate = upo.getValue();
			}
		}
		return _currRate;
	}
	public void analyzeMaxRate(List<ParameterObject> upstreamMaxRateList,List<ParameterObject> downstreamMaxRateList){
		String _upMaxRate = "";
		String _downMaxRate = "";
		for(ParameterObject upo:upstreamMaxRateList){
			if(upo.getValue()!=null&&!"".equals(upo.getValue())){
				for(ParameterObject dpo:downstreamMaxRateList){
					if(upo.getKey().indexOf("1")>=0&&dpo.getKey().indexOf("1")>=0){
						_upMaxRate = upo.getValue();
						_downMaxRate = dpo.getValue();
					}
				}
			}
		}
		if("".equals(_downMaxRate)&&upstreamMaxRateList!=null&&upstreamMaxRateList.size()>0){
			_upMaxRate = upstreamMaxRateList.get(0).getValue();
		}
		if(!"".equals(_upMaxRate)&&Integer.parseInt(_upMaxRate)>=100000)_upMaxRate = Integer.parseInt(_upMaxRate)/1000+"";
		if(!"".equals(_downMaxRate)&&Integer.parseInt(_downMaxRate)>=100000)_downMaxRate = Integer.parseInt(_downMaxRate)/1000+"";
		setUpMaxRate(_upMaxRate);
		setDownMaxRate(_downMaxRate);
	}
	public void analyzeIP(List<ParameterObject> ipList) {
		// TODO Auto-generated method stub
		if(this.getSummary().getIp()==null||"".equals(this.getSummary().getIp().trim())||this.getSummary().getIp().indexOf("null")>=0){
			for(ParameterObject ipObject:ipList){
//				System.out.println("====IP:"+ipObject.getValue());
				if(ipObject.getValue()!=null&&!"".equals(ipObject.getValue().trim())&&this.getSummary().getIp().indexOf("null")>=0){
					this.getSummary().setIp(ipObject.getValue().trim());
				}
			}
		}
	}
	public void analyzeMac(List<ParameterObject> macList) {
		// TODO Auto-generated method stub
		if(this.getSummary().getMac()==null||"".equals(this.getSummary().getMac().trim())||this.getSummary().getIp().indexOf("null")>=0){
			for(ParameterObject macObject:macList){
				if(macObject.getValue()!=null&&!"".equals(macObject.getValue().trim())){
					this.getSummary().setMac(macObject.getValue().trim());
				}
			}
		}
	}
	
	
}
