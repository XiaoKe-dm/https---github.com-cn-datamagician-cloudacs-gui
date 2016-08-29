package org.acs.core.model;

import java.util.List;

public class InternetGatewayDevice {

	private VersionObject DeviceSummary;
	private DeviceInfo DeviceInfo;
	private ManagementServer ManagementServer;
	private List<WANDevice> WANDeviceList;
	private VersionObject LANDeviceNumberOfEntries;
	private VersionObject WANDeviceNumberOfEntries;
	private VersionObject Qsactoption;
	private VersionObject Configcontrol;
	private VersionObject ConfFileSerialNum;
	private VersionObject TftpIntervalHour;
	private VersionObject DeviceConfig;
	private InternetGatewayDeviceTime Time;
	private Layer2Bridging Layer2Bridging;
	private QueueManagement QueueManagement;
	private IPPingDiagnostics IPPingDiagnostics;
	private List<LANDevice> LANDeviceList;
	private Layer3Forwarding Layer3Forwarding;
	private VersionObject Services;
	public VersionObject getDeviceSummary() {
		return DeviceSummary;
	}
	public void setDeviceSummary(VersionObject deviceSummary) {
		DeviceSummary = deviceSummary;
	}
	public DeviceInfo getDeviceInfo() {
		return DeviceInfo;
	}
	public void setDeviceInfo(DeviceInfo deviceInfo) {
		DeviceInfo = deviceInfo;
	}
	public ManagementServer getManagementServer() {
		return ManagementServer;
	}
	public void setManagementServer(ManagementServer managementServer) {
		ManagementServer = managementServer;
	}
	public List<WANDevice> getWANDeviceList() {
		return WANDeviceList;
	}
	public void setWANDeviceList(List<WANDevice> wANDeviceList) {
		WANDeviceList = wANDeviceList;
	}
	public VersionObject getLANDeviceNumberOfEntries() {
		return LANDeviceNumberOfEntries;
	}
	public void setLANDeviceNumberOfEntries(VersionObject lANDeviceNumberOfEntries) {
		LANDeviceNumberOfEntries = lANDeviceNumberOfEntries;
	}
	public VersionObject getWANDeviceNumberOfEntries() {
		return WANDeviceNumberOfEntries;
	}
	public void setWANDeviceNumberOfEntries(VersionObject wANDeviceNumberOfEntries) {
		WANDeviceNumberOfEntries = wANDeviceNumberOfEntries;
	}
	public VersionObject getQsactoption() {
		return Qsactoption;
	}
	public void setQsactoption(VersionObject qsactoption) {
		Qsactoption = qsactoption;
	}
	public VersionObject getConfigcontrol() {
		return Configcontrol;
	}
	public void setConfigcontrol(VersionObject configcontrol) {
		Configcontrol = configcontrol;
	}
	public VersionObject getConfFileSerialNum() {
		return ConfFileSerialNum;
	}
	public void setConfFileSerialNum(VersionObject confFileSerialNum) {
		ConfFileSerialNum = confFileSerialNum;
	}
	public VersionObject getTftpIntervalHour() {
		return TftpIntervalHour;
	}
	public void setTftpIntervalHour(VersionObject tftpIntervalHour) {
		TftpIntervalHour = tftpIntervalHour;
	}
	public VersionObject getDeviceConfig() {
		return DeviceConfig;
	}
	public void setDeviceConfig(VersionObject deviceConfig) {
		DeviceConfig = deviceConfig;
	}
	public InternetGatewayDeviceTime getTime() {
		return Time;
	}
	public void setTime(InternetGatewayDeviceTime time) {
		Time = time;
	}
	public Layer2Bridging getLayer2Bridging() {
		return Layer2Bridging;
	}
	public void setLayer2Bridging(Layer2Bridging layer2Bridging) {
		Layer2Bridging = layer2Bridging;
	}
	public QueueManagement getQueueManagement() {
		return QueueManagement;
	}
	public void setQueueManagement(QueueManagement queueManagement) {
		QueueManagement = queueManagement;
	}
	public IPPingDiagnostics getIPPingDiagnostics() {
		return IPPingDiagnostics;
	}
	public void setIPPingDiagnostics(IPPingDiagnostics iPPingDiagnostics) {
		IPPingDiagnostics = iPPingDiagnostics;
	}
	public List<LANDevice> getLANDeviceList() {
		return LANDeviceList;
	}
	public void setLANDeviceList(List<LANDevice> lANDeviceList) {
		LANDeviceList = lANDeviceList;
	}
	public Layer3Forwarding getLayer3Forwarding() {
		return Layer3Forwarding;
	}
	public void setLayer3Forwarding(Layer3Forwarding layer3Forwarding) {
		Layer3Forwarding = layer3Forwarding;
	}
	public VersionObject getServices() {
		return Services;
	}
	public void setServices(VersionObject services) {
		Services = services;
	}
	
	
}
