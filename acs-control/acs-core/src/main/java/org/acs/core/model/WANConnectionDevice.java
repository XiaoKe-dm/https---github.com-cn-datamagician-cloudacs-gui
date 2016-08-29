package org.acs.core.model;

import java.util.List;

public class WANConnectionDevice {

	private VersionObject WANIPConnectionNumberOfEntries;
	private VersionObject WANPPPConnectionNumberOfEntries;
	private WANDSLLinkConfig WANDSLLinkConfig;
	private WANATMF5LoopbackDiagnostics WANATMF5LoopbackDiagnostics;
	private WANPTMLinkConfig WANPTMLinkConfig;
	private WANEthernetLinkConfig WANEthernetLinkConfig;
	private List<WANIPConnection> WANIPConnection;
	private List<WANPPPConnection> WANPPPConnection;
	
	public VersionObject getWANIPConnectionNumberOfEntries() {
		return WANIPConnectionNumberOfEntries;
	}
	public void setWANIPConnectionNumberOfEntries(VersionObject wANIPConnectionNumberOfEntries) {
		WANIPConnectionNumberOfEntries = wANIPConnectionNumberOfEntries;
	}
	public VersionObject getWANPPPConnectionNumberOfEntries() {
		return WANPPPConnectionNumberOfEntries;
	}
	public void setWANPPPConnectionNumberOfEntries(VersionObject wANPPPConnectionNumberOfEntries) {
		WANPPPConnectionNumberOfEntries = wANPPPConnectionNumberOfEntries;
	}
	public WANDSLLinkConfig getWANDSLLinkConfig() {
		return WANDSLLinkConfig;
	}
	public void setWANDSLLinkConfig(WANDSLLinkConfig wANDSLLinkConfig) {
		WANDSLLinkConfig = wANDSLLinkConfig;
	}
	public WANATMF5LoopbackDiagnostics getWANATMF5LoopbackDiagnostics() {
		return WANATMF5LoopbackDiagnostics;
	}
	public void setWANATMF5LoopbackDiagnostics(WANATMF5LoopbackDiagnostics wANATMF5LoopbackDiagnostics) {
		WANATMF5LoopbackDiagnostics = wANATMF5LoopbackDiagnostics;
	}
	public WANPTMLinkConfig getWANPTMLinkConfig() {
		return WANPTMLinkConfig;
	}
	public void setWANPTMLinkConfig(WANPTMLinkConfig wANPTMLinkConfig) {
		WANPTMLinkConfig = wANPTMLinkConfig;
	}
	public WANEthernetLinkConfig getWANEthernetLinkConfig() {
		return WANEthernetLinkConfig;
	}
	public void setWANEthernetLinkConfig(WANEthernetLinkConfig wANEthernetLinkConfig) {
		WANEthernetLinkConfig = wANEthernetLinkConfig;
	}
	public List<WANIPConnection> getWANIPConnection() {
		return WANIPConnection;
	}
	public void setWANIPConnection(List<WANIPConnection> wANIPConnection) {
		WANIPConnection = wANIPConnection;
	}
	public List<WANPPPConnection> getWANPPPConnection() {
		return WANPPPConnection;
	}
	public void setWANPPPConnection(List<WANPPPConnection> wANPPPConnection) {
		WANPPPConnection = wANPPPConnection;
	}
	
	
}
