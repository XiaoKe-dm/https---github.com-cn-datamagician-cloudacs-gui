package org.acs.utils.model;

import java.util.List;

public class DeviceGroup extends AbstractEntity{

	private String name;
	private List<Oper> operList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Oper> getOperList() {
		return operList;
	}

	public void setOperList(List<Oper> operList) {
		this.operList = operList;
	}
	
	
}
