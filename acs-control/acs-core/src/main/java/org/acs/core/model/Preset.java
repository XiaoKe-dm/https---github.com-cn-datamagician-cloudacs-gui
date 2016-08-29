package org.acs.core.model;

import java.util.List;

public class Preset {

	private String presetName;
	private String moduleName;
	private List<Precondition> preconditionList;
	private List<Configuration> configurations;
	public String getPresetName() {
		return presetName;
	}
	public void setPresetName(String presetName) {
		this.presetName = presetName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<Precondition> getPreconditionList() {
		return preconditionList;
	}
	public void setPreconditionList(List<Precondition> preconditionList) {
		this.preconditionList = preconditionList;
	}
	public List<Configuration> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}
	
	
}
