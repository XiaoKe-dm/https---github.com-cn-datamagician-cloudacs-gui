package org.acs.core.model;

import java.util.List;

public class Task {

	private String taskName;
	private String moduleName;
	private String device;
	private String time;
	private String retries;
	private Fault fault;
	private List<String> deviceList;
	private List<Configuration> configurations;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<String> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}
	public List<Configuration> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRetries() {
		return retries;
	}
	public void setRetries(String retries) {
		this.retries = retries;
	}
	public Fault getFault() {
		return fault;
	}
	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	
	
}
