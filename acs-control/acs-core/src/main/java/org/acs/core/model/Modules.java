package org.acs.core.model;

import org.acs.utils.model.AbstractEntity;

public class Modules extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5043213367497395594L;
	private String name;
	private String productClass;
	private String oui;
	private String manufacturer;
	private String description;
	private int deviceNum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getOui() {
		return oui;
	}
	public void setOui(String oui) {
		this.oui = oui;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(int deviceNum) {
		this.deviceNum = deviceNum;
	}
	
	
}
