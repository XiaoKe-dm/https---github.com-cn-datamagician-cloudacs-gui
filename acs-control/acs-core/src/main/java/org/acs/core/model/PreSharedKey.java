package org.acs.core.model;

public class PreSharedKey {

	private VersionObject PreSharedKey;
	private VersionObject KeyPassphrase;
	private VersionObject AssociatedDeviceMACAddress;
	public VersionObject getPreSharedKey() {
		return PreSharedKey;
	}
	public void setPreSharedKey(VersionObject preSharedKey) {
		PreSharedKey = preSharedKey;
	}
	public VersionObject getKeyPassphrase() {
		return KeyPassphrase;
	}
	public void setKeyPassphrase(VersionObject keyPassphrase) {
		KeyPassphrase = keyPassphrase;
	}
	public VersionObject getAssociatedDeviceMACAddress() {
		return AssociatedDeviceMACAddress;
	}
	public void setAssociatedDeviceMACAddress(VersionObject associatedDeviceMACAddress) {
		AssociatedDeviceMACAddress = associatedDeviceMACAddress;
	}
	
	
}
