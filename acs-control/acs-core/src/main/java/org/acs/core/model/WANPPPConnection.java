package org.acs.core.model;

public class WANPPPConnection {

	private VersionObject Enable;
	private VersionObject ConnectionStatus;
	private VersionObject PossibleConnectionTypes;
	private VersionObject ConnectionType;
	private VersionObject Name;
	private VersionObject Uptime;
	private VersionObject LastConnectionError;
	private VersionObject IdleDisconnectTime;
	private VersionObject RSIPAvailable;
	private VersionObject NATEnabled;
	private VersionObject pppMTU;
	private VersionObject Username;
	private VersionObject Password;
	private VersionObject PPPEncryptionProtocol;
	private VersionObject PPPCompressionProtocol;
	private VersionObject PPPAuthenticationProtocol;
	private VersionObject ExternalIPAddress;
	private VersionObject RemoteIPAddress;
	private VersionObject CurrentMRUSize;
	private VersionObject DNSEnabled;
	private VersionObject DNSOverrideAllowed;
	private VersionObject DNSServers;
	private VersionObject MACAddress;
	private VersionObject MACAddressOverride;
	private VersionObject TransportType;
	private VersionObject PPPoEServiceName;
	private VersionObject ConnectionTrigger;
	private VersionObject RouteProtocolRx;
	private VersionObject PPPLCPEcho;
	private VersionObject PPPLCPEchoRetry;
	private VersionObject PortMappingNumberOfEntries;
	private VersionObject ConfigType;
	private VersionObject PortMapping;
	private WANPPPConnectionStats Stats;
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getConnectionStatus() {
		return ConnectionStatus;
	}
	public void setConnectionStatus(VersionObject connectionStatus) {
		ConnectionStatus = connectionStatus;
	}
	public VersionObject getPossibleConnectionTypes() {
		return PossibleConnectionTypes;
	}
	public void setPossibleConnectionTypes(VersionObject possibleConnectionTypes) {
		PossibleConnectionTypes = possibleConnectionTypes;
	}
	public VersionObject getConnectionType() {
		return ConnectionType;
	}
	public void setConnectionType(VersionObject connectionType) {
		ConnectionType = connectionType;
	}
	public VersionObject getName() {
		return Name;
	}
	public void setName(VersionObject name) {
		Name = name;
	}
	public VersionObject getUptime() {
		return Uptime;
	}
	public void setUptime(VersionObject uptime) {
		Uptime = uptime;
	}
	public VersionObject getLastConnectionError() {
		return LastConnectionError;
	}
	public void setLastConnectionError(VersionObject lastConnectionError) {
		LastConnectionError = lastConnectionError;
	}
	public VersionObject getIdleDisconnectTime() {
		return IdleDisconnectTime;
	}
	public void setIdleDisconnectTime(VersionObject idleDisconnectTime) {
		IdleDisconnectTime = idleDisconnectTime;
	}
	public VersionObject getRSIPAvailable() {
		return RSIPAvailable;
	}
	public void setRSIPAvailable(VersionObject rSIPAvailable) {
		RSIPAvailable = rSIPAvailable;
	}
	public VersionObject getNATEnabled() {
		return NATEnabled;
	}
	public void setNATEnabled(VersionObject nATEnabled) {
		NATEnabled = nATEnabled;
	}
	public VersionObject getPppMTU() {
		return pppMTU;
	}
	public void setPppMTU(VersionObject pppMTU) {
		this.pppMTU = pppMTU;
	}
	public VersionObject getUsername() {
		return Username;
	}
	public void setUsername(VersionObject username) {
		Username = username;
	}
	public VersionObject getPassword() {
		return Password;
	}
	public void setPassword(VersionObject password) {
		Password = password;
	}
	public VersionObject getPPPEncryptionProtocol() {
		return PPPEncryptionProtocol;
	}
	public void setPPPEncryptionProtocol(VersionObject pPPEncryptionProtocol) {
		PPPEncryptionProtocol = pPPEncryptionProtocol;
	}
	public VersionObject getPPPCompressionProtocol() {
		return PPPCompressionProtocol;
	}
	public void setPPPCompressionProtocol(VersionObject pPPCompressionProtocol) {
		PPPCompressionProtocol = pPPCompressionProtocol;
	}
	public VersionObject getPPPAuthenticationProtocol() {
		return PPPAuthenticationProtocol;
	}
	public void setPPPAuthenticationProtocol(VersionObject pPPAuthenticationProtocol) {
		PPPAuthenticationProtocol = pPPAuthenticationProtocol;
	}
	public VersionObject getExternalIPAddress() {
		return ExternalIPAddress;
	}
	public void setExternalIPAddress(VersionObject externalIPAddress) {
		ExternalIPAddress = externalIPAddress;
	}
	public VersionObject getRemoteIPAddress() {
		return RemoteIPAddress;
	}
	public void setRemoteIPAddress(VersionObject remoteIPAddress) {
		RemoteIPAddress = remoteIPAddress;
	}
	public VersionObject getCurrentMRUSize() {
		return CurrentMRUSize;
	}
	public void setCurrentMRUSize(VersionObject currentMRUSize) {
		CurrentMRUSize = currentMRUSize;
	}
	public VersionObject getDNSEnabled() {
		return DNSEnabled;
	}
	public void setDNSEnabled(VersionObject dNSEnabled) {
		DNSEnabled = dNSEnabled;
	}
	public VersionObject getDNSOverrideAllowed() {
		return DNSOverrideAllowed;
	}
	public void setDNSOverrideAllowed(VersionObject dNSOverrideAllowed) {
		DNSOverrideAllowed = dNSOverrideAllowed;
	}
	public VersionObject getDNSServers() {
		return DNSServers;
	}
	public void setDNSServers(VersionObject dNSServers) {
		DNSServers = dNSServers;
	}
	public VersionObject getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(VersionObject mACAddress) {
		MACAddress = mACAddress;
	}
	public VersionObject getMACAddressOverride() {
		return MACAddressOverride;
	}
	public void setMACAddressOverride(VersionObject mACAddressOverride) {
		MACAddressOverride = mACAddressOverride;
	}
	public VersionObject getTransportType() {
		return TransportType;
	}
	public void setTransportType(VersionObject transportType) {
		TransportType = transportType;
	}
	public VersionObject getPPPoEServiceName() {
		return PPPoEServiceName;
	}
	public void setPPPoEServiceName(VersionObject pPPoEServiceName) {
		PPPoEServiceName = pPPoEServiceName;
	}
	public VersionObject getConnectionTrigger() {
		return ConnectionTrigger;
	}
	public void setConnectionTrigger(VersionObject connectionTrigger) {
		ConnectionTrigger = connectionTrigger;
	}
	public VersionObject getRouteProtocolRx() {
		return RouteProtocolRx;
	}
	public void setRouteProtocolRx(VersionObject routeProtocolRx) {
		RouteProtocolRx = routeProtocolRx;
	}
	public VersionObject getPPPLCPEcho() {
		return PPPLCPEcho;
	}
	public void setPPPLCPEcho(VersionObject pPPLCPEcho) {
		PPPLCPEcho = pPPLCPEcho;
	}
	public VersionObject getPPPLCPEchoRetry() {
		return PPPLCPEchoRetry;
	}
	public void setPPPLCPEchoRetry(VersionObject pPPLCPEchoRetry) {
		PPPLCPEchoRetry = pPPLCPEchoRetry;
	}
	public VersionObject getPortMappingNumberOfEntries() {
		return PortMappingNumberOfEntries;
	}
	public void setPortMappingNumberOfEntries(VersionObject portMappingNumberOfEntries) {
		PortMappingNumberOfEntries = portMappingNumberOfEntries;
	}
	public VersionObject getConfigType() {
		return ConfigType;
	}
	public void setConfigType(VersionObject configType) {
		ConfigType = configType;
	}
	public VersionObject getPortMapping() {
		return PortMapping;
	}
	public void setPortMapping(VersionObject portMapping) {
		PortMapping = portMapping;
	}
	public WANPPPConnectionStats getStats() {
		return Stats;
	}
	public void setStats(WANPPPConnectionStats stats) {
		Stats = stats;
	}
	
	
}
