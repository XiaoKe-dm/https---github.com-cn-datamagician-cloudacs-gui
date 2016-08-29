package org.acs.core.model;

public class WANPPPConnectionStats {

	private VersionObject EthernetBytesSent;
	private VersionObject EthernetBytesReceived;
	private VersionObject EthernetPacketsSent;
	private VersionObject EthernetPacketsReceived;
	public VersionObject getEthernetBytesSent() {
		return EthernetBytesSent;
	}
	public void setEthernetBytesSent(VersionObject ethernetBytesSent) {
		EthernetBytesSent = ethernetBytesSent;
	}
	public VersionObject getEthernetBytesReceived() {
		return EthernetBytesReceived;
	}
	public void setEthernetBytesReceived(VersionObject ethernetBytesReceived) {
		EthernetBytesReceived = ethernetBytesReceived;
	}
	public VersionObject getEthernetPacketsSent() {
		return EthernetPacketsSent;
	}
	public void setEthernetPacketsSent(VersionObject ethernetPacketsSent) {
		EthernetPacketsSent = ethernetPacketsSent;
	}
	public VersionObject getEthernetPacketsReceived() {
		return EthernetPacketsReceived;
	}
	public void setEthernetPacketsReceived(VersionObject ethernetPacketsReceived) {
		EthernetPacketsReceived = ethernetPacketsReceived;
	}
	
	
}
