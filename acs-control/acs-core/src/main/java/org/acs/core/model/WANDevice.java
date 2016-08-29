package org.acs.core.model;

import java.util.List;

public class WANDevice {

	private VersionObject WANConnectionNumberOfEntries;
	private WANCommonInterfaceConfig WANCommonInterfaceConfig;
	private WANDSLInterfaceConfig WANDSLInterfaceConfig;
	private WANEthernetInterfaceConfig WANEthernetInterfaceConfig;
	private List<WANConnectionDevice> WANConnectionDevice;
	public VersionObject getWANConnectionNumberOfEntries() {
		return WANConnectionNumberOfEntries;
	}
	public void setWANConnectionNumberOfEntries(VersionObject wANConnectionNumberOfEntries) {
		WANConnectionNumberOfEntries = wANConnectionNumberOfEntries;
	}
	public WANCommonInterfaceConfig getWANCommonInterfaceConfig() {
		return WANCommonInterfaceConfig;
	}
	public void setWANCommonInterfaceConfig(WANCommonInterfaceConfig wANCommonInterfaceConfig) {
		WANCommonInterfaceConfig = wANCommonInterfaceConfig;
	}
	public WANDSLInterfaceConfig getWANDSLInterfaceConfig() {
		return WANDSLInterfaceConfig;
	}
	public void setWANDSLInterfaceConfig(WANDSLInterfaceConfig wANDSLInterfaceConfig) {
		WANDSLInterfaceConfig = wANDSLInterfaceConfig;
	}
	public WANEthernetInterfaceConfig getWANEthernetInterfaceConfig() {
		return WANEthernetInterfaceConfig;
	}
	public void setWANEthernetInterfaceConfig(WANEthernetInterfaceConfig wANEthernetInterfaceConfig) {
		WANEthernetInterfaceConfig = wANEthernetInterfaceConfig;
	}
	public List<WANConnectionDevice> getWANConnectionDevice() {
		return WANConnectionDevice;
	}
	public void setWANConnectionDevice(List<WANConnectionDevice> wANConnectionDevice) {
		WANConnectionDevice = wANConnectionDevice;
	}
	
	
}
