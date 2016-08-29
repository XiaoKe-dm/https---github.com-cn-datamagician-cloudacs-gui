package org.acs.core.model;

import java.util.List;

public class LANDevice {

	private VersionObject LANEthernetInterfaceNumberOfEntries;
	private VersionObject LANUSBInterfaceNumberOfEntries;
	private VersionObject LANWLANConfigurationNumberOfEntries;
	private LANHostConfigManagement LANHostConfigManagement;
	private List<LANEthernetInterfaceConfig> LANEthernetInterfaceConfig;
	private List<WLANConfiguration> WLANConfiguration;
	private Hosts Hosts;
	public VersionObject getLANEthernetInterfaceNumberOfEntries() {
		return LANEthernetInterfaceNumberOfEntries;
	}
	public void setLANEthernetInterfaceNumberOfEntries(VersionObject lANEthernetInterfaceNumberOfEntries) {
		LANEthernetInterfaceNumberOfEntries = lANEthernetInterfaceNumberOfEntries;
	}
	public VersionObject getLANUSBInterfaceNumberOfEntries() {
		return LANUSBInterfaceNumberOfEntries;
	}
	public void setLANUSBInterfaceNumberOfEntries(VersionObject lANUSBInterfaceNumberOfEntries) {
		LANUSBInterfaceNumberOfEntries = lANUSBInterfaceNumberOfEntries;
	}
	public VersionObject getLANWLANConfigurationNumberOfEntries() {
		return LANWLANConfigurationNumberOfEntries;
	}
	public void setLANWLANConfigurationNumberOfEntries(VersionObject lANWLANConfigurationNumberOfEntries) {
		LANWLANConfigurationNumberOfEntries = lANWLANConfigurationNumberOfEntries;
	}
	public LANHostConfigManagement getLANHostConfigManagement() {
		return LANHostConfigManagement;
	}
	public void setLANHostConfigManagement(LANHostConfigManagement lANHostConfigManagement) {
		LANHostConfigManagement = lANHostConfigManagement;
	}
	public List<LANEthernetInterfaceConfig> getLANEthernetInterfaceConfig() {
		return LANEthernetInterfaceConfig;
	}
	public void setLANEthernetInterfaceConfig(List<LANEthernetInterfaceConfig> lANEthernetInterfaceConfig) {
		LANEthernetInterfaceConfig = lANEthernetInterfaceConfig;
	}
	public List<WLANConfiguration> getWLANConfiguration() {
		return WLANConfiguration;
	}
	public void setWLANConfiguration(List<WLANConfiguration> wLANConfiguration) {
		WLANConfiguration = wLANConfiguration;
	}
	public Hosts getHosts() {
		return Hosts;
	}
	public void setHosts(Hosts hosts) {
		Hosts = hosts;
	}
	
}
