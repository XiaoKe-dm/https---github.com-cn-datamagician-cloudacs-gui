package org.acs.core.model;

public class WANPTMLinkConfig {

	private VersionObject Enable;
	private VersionObject LinkStatus;
	private VersionObject MACAddress;
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getLinkStatus() {
		return LinkStatus;
	}
	public void setLinkStatus(VersionObject linkStatus) {
		LinkStatus = linkStatus;
	}
	public VersionObject getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(VersionObject mACAddress) {
		MACAddress = mACAddress;
	}
	
}
