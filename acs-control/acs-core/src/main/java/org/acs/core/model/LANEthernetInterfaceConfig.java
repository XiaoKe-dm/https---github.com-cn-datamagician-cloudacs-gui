package org.acs.core.model;


public class LANEthernetInterfaceConfig {
	
	private VersionObject Enable;
	private VersionObject Status;
	private VersionObject MACAddress;
	private VersionObject MACAddressControlEnabled;
	private VersionObject MaxBitRate;
	private VersionObject DuplexMode;
	private LANEthernetInterfaceConfigStats Stats;
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getStatus() {
		return Status;
	}
	public void setStatus(VersionObject status) {
		Status = status;
	}
	public VersionObject getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(VersionObject mACAddress) {
		MACAddress = mACAddress;
	}
	public VersionObject getMACAddressControlEnabled() {
		return MACAddressControlEnabled;
	}
	public void setMACAddressControlEnabled(VersionObject mACAddressControlEnabled) {
		MACAddressControlEnabled = mACAddressControlEnabled;
	}
	public VersionObject getMaxBitRate() {
		return MaxBitRate;
	}
	public void setMaxBitRate(VersionObject maxBitRate) {
		MaxBitRate = maxBitRate;
	}
	public VersionObject getDuplexMode() {
		return DuplexMode;
	}
	public void setDuplexMode(VersionObject duplexMode) {
		DuplexMode = duplexMode;
	}
	public LANEthernetInterfaceConfigStats getStats() {
		return Stats;
	}
	public void setStats(LANEthernetInterfaceConfigStats stats) {
		Stats = stats;
	}
	
	
	
}
