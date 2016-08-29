package org.acs.core.model;

public class WANEthernetInterfaceConfig {

	private VersionObject Enable;
	private VersionObject Status;
	private VersionObject MACAddress;
	private VersionObject MaxBitRate;
	private VersionObject DuplexMode;
	private VersionObject ShapingRate;
	private VersionObject ShapingBurstSize;
	private WANEthernetInterfaceConfigStats Stats;
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
	public VersionObject getShapingRate() {
		return ShapingRate;
	}
	public void setShapingRate(VersionObject shapingRate) {
		ShapingRate = shapingRate;
	}
	public VersionObject getShapingBurstSize() {
		return ShapingBurstSize;
	}
	public void setShapingBurstSize(VersionObject shapingBurstSize) {
		ShapingBurstSize = shapingBurstSize;
	}
	public WANEthernetInterfaceConfigStats getStats() {
		return Stats;
	}
	public void setStats(WANEthernetInterfaceConfigStats stats) {
		Stats = stats;
	}
	
	
}
