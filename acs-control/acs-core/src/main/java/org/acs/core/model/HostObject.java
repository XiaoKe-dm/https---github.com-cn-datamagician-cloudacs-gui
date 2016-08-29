package org.acs.core.model;

public class HostObject {

	private VersionObject IPAddress;
	private VersionObject AddressSource;
	private VersionObject LeaseTimeRemaining;
	private VersionObject MACAddress;
	private VersionObject HostName;
	private VersionObject InterfaceType;
	private VersionObject Active;
	public VersionObject getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(VersionObject iPAddress) {
		IPAddress = iPAddress;
	}
	public VersionObject getAddressSource() {
		return AddressSource;
	}
	public void setAddressSource(VersionObject addressSource) {
		AddressSource = addressSource;
	}
	public VersionObject getLeaseTimeRemaining() {
		return LeaseTimeRemaining;
	}
	public void setLeaseTimeRemaining(VersionObject leaseTimeRemaining) {
		LeaseTimeRemaining = leaseTimeRemaining;
	}
	public VersionObject getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(VersionObject mACAddress) {
		MACAddress = mACAddress;
	}
	public VersionObject getHostName() {
		return HostName;
	}
	public void setHostName(VersionObject hostName) {
		HostName = hostName;
	}
	public VersionObject getInterfaceType() {
		return InterfaceType;
	}
	public void setInterfaceType(VersionObject interfaceType) {
		InterfaceType = interfaceType;
	}
	public VersionObject getActive() {
		return Active;
	}
	public void setActive(VersionObject active) {
		Active = active;
	}
	
	
}
