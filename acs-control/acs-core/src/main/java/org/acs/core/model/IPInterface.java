package org.acs.core.model;


public class IPInterface {

	private VersionObject Enable;
	private VersionObject IPInterfaceAddressingType;
	private VersionObject IPInterfaceIPAddress;
	private VersionObject IPInterfaceSubnetMask;
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getIPInterfaceAddressingType() {
		return IPInterfaceAddressingType;
	}
	public void setIPInterfaceAddressingType(VersionObject iPInterfaceAddressingType) {
		IPInterfaceAddressingType = iPInterfaceAddressingType;
	}
	public VersionObject getIPInterfaceIPAddress() {
		return IPInterfaceIPAddress;
	}
	public void setIPInterfaceIPAddress(VersionObject iPInterfaceIPAddress) {
		IPInterfaceIPAddress = iPInterfaceIPAddress;
	}
	public VersionObject getIPInterfaceSubnetMask() {
		return IPInterfaceSubnetMask;
	}
	public void setIPInterfaceSubnetMask(VersionObject iPInterfaceSubnetMask) {
		IPInterfaceSubnetMask = iPInterfaceSubnetMask;
	}
	
}
