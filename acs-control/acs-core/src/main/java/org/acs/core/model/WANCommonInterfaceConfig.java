package org.acs.core.model;

public class WANCommonInterfaceConfig {

	private VersionObject EnabledForInternet;
	private VersionObject WANAccessType;
	private VersionObject Layer1UpstreamMaxBitRate;
	private VersionObject Layer1DownstreamMaxBitRate;
	private VersionObject PhysicalLinkStatus;
	private VersionObject TotalBytesSent;
	private VersionObject TotalBytesReceived;
	private VersionObject TotalPacketsSent;
	private VersionObject TotalPacketsReceived;
	private VersionObject MaximumActiveConnections;
	private VersionObject NumberOfActiveConnections;
	public VersionObject getEnabledForInternet() {
		return EnabledForInternet;
	}
	public void setEnabledForInternet(VersionObject enabledForInternet) {
		EnabledForInternet = enabledForInternet;
	}
	public VersionObject getWANAccessType() {
		return WANAccessType;
	}
	public void setWANAccessType(VersionObject wANAccessType) {
		WANAccessType = wANAccessType;
	}
	public VersionObject getLayer1UpstreamMaxBitRate() {
		return Layer1UpstreamMaxBitRate;
	}
	public void setLayer1UpstreamMaxBitRate(VersionObject layer1UpstreamMaxBitRate) {
		Layer1UpstreamMaxBitRate = layer1UpstreamMaxBitRate;
	}
	public VersionObject getLayer1DownstreamMaxBitRate() {
		return Layer1DownstreamMaxBitRate;
	}
	public void setLayer1DownstreamMaxBitRate(VersionObject layer1DownstreamMaxBitRate) {
		Layer1DownstreamMaxBitRate = layer1DownstreamMaxBitRate;
	}
	public VersionObject getPhysicalLinkStatus() {
		return PhysicalLinkStatus;
	}
	public void setPhysicalLinkStatus(VersionObject physicalLinkStatus) {
		PhysicalLinkStatus = physicalLinkStatus;
	}
	public VersionObject getTotalBytesSent() {
		return TotalBytesSent;
	}
	public void setTotalBytesSent(VersionObject totalBytesSent) {
		TotalBytesSent = totalBytesSent;
	}
	public VersionObject getTotalBytesReceived() {
		return TotalBytesReceived;
	}
	public void setTotalBytesReceived(VersionObject totalBytesReceived) {
		TotalBytesReceived = totalBytesReceived;
	}
	public VersionObject getTotalPacketsSent() {
		return TotalPacketsSent;
	}
	public void setTotalPacketsSent(VersionObject totalPacketsSent) {
		TotalPacketsSent = totalPacketsSent;
	}
	public VersionObject getTotalPacketsReceived() {
		return TotalPacketsReceived;
	}
	public void setTotalPacketsReceived(VersionObject totalPacketsReceived) {
		TotalPacketsReceived = totalPacketsReceived;
	}
	public VersionObject getMaximumActiveConnections() {
		return MaximumActiveConnections;
	}
	public void setMaximumActiveConnections(VersionObject maximumActiveConnections) {
		MaximumActiveConnections = maximumActiveConnections;
	}
	public VersionObject getNumberOfActiveConnections() {
		return NumberOfActiveConnections;
	}
	public void setNumberOfActiveConnections(VersionObject numberOfActiveConnections) {
		NumberOfActiveConnections = numberOfActiveConnections;
	}
	
	
}
