package org.acs.core.model;

public class Fault {

	private String faultcode;
	private String faultstring;
	private Fault detail;
	
	public String getFaultcode() {
		return faultcode;
	}
	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}
	public String getFaultstring() {
		return faultstring;
	}
	public void setFaultstring(String faultstring) {
		this.faultstring = faultstring;
	}
	public Fault getDetail() {
		return detail;
	}
	public void setDetail(Fault detail) {
		this.detail = detail;
	}
	
	
}
