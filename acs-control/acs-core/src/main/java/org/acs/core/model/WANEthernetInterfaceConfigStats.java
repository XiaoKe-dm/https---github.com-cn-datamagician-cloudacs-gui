package org.acs.core.model;

public class WANEthernetInterfaceConfigStats {

	private VersionObject BytesSent;
	private VersionObject BytesReceived;
	private VersionObject PacketsSent;
	private VersionObject PacketsReceived;
	public VersionObject getBytesSent() {
		return BytesSent;
	}
	public void setBytesSent(VersionObject bytesSent) {
		BytesSent = bytesSent;
	}
	public VersionObject getBytesReceived() {
		return BytesReceived;
	}
	public void setBytesReceived(VersionObject bytesReceived) {
		BytesReceived = bytesReceived;
	}
	public VersionObject getPacketsSent() {
		return PacketsSent;
	}
	public void setPacketsSent(VersionObject packetsSent) {
		PacketsSent = packetsSent;
	}
	public VersionObject getPacketsReceived() {
		return PacketsReceived;
	}
	public void setPacketsReceived(VersionObject packetsReceived) {
		PacketsReceived = packetsReceived;
	}
	
	
}
