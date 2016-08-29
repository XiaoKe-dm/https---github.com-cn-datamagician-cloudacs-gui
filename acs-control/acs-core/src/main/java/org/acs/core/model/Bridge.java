package org.acs.core.model;

public class Bridge {

	private VersionObject BridgeKey;
	private VersionObject BridgeEnable;
	private VersionObject BridgeStatus;
	private VersionObject BridgeName;
	private VersionObject VLANID;
	public VersionObject getBridgeKey() {
		return BridgeKey;
	}
	public void setBridgeKey(VersionObject bridgeKey) {
		BridgeKey = bridgeKey;
	}
	public VersionObject getBridgeEnable() {
		return BridgeEnable;
	}
	public void setBridgeEnable(VersionObject bridgeEnable) {
		BridgeEnable = bridgeEnable;
	}
	public VersionObject getBridgeStatus() {
		return BridgeStatus;
	}
	public void setBridgeStatus(VersionObject bridgeStatus) {
		BridgeStatus = bridgeStatus;
	}
	public VersionObject getBridgeName() {
		return BridgeName;
	}
	public void setBridgeName(VersionObject bridgeName) {
		BridgeName = bridgeName;
	}
	public VersionObject getVLANID() {
		return VLANID;
	}
	public void setVLANID(VersionObject vLANID) {
		VLANID = vLANID;
	}
	
	
}
