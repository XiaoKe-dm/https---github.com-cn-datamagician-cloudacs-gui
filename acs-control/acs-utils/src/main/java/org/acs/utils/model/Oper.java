package org.acs.utils.model;

import java.util.List;

public class Oper extends AbstractEntity{

	private String name;
	private String moduleId;
	private List<DeviceGroup> deviceGroupList;
	private String nbiUrl;
	private String[] ipRange;
	private String domains;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public List<DeviceGroup> getDeviceGroupList() {
		return deviceGroupList;
	}

	public void setDeviceGroupList(List<DeviceGroup> deviceGroupList) {
		this.deviceGroupList = deviceGroupList;
	}

	public String getNbiUrl() {
		return nbiUrl;
	}

	public void setNbiUrl(String nbiUrl) {
		this.nbiUrl = nbiUrl;
	}

	public String[] getIpRange() {
		return ipRange;
	}

	public void setIpRange(String[] ipRange) {
		this.ipRange = ipRange;
	}

	public String getDomains() {
		return domains;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}
	
	
}
