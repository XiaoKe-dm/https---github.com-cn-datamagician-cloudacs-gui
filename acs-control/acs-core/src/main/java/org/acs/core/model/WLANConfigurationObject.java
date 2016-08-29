package org.acs.core.model;

import java.util.Date;
import java.util.List;

public class WLANConfigurationObject {

	private ParameterObject ssid;
	private ParameterObject standard;
	private ParameterObject channel;
	private ParameterObject possibleChannels;
	private ParameterObject preSharedKey;
	private ParameterObject wPAEncryptionModes;
	private ParameterObject wPAAuthenticationMode;
	private ParameterObject basicEncryptionModes;
	private ParameterObject basicAuthenticationMode;
	private String status;
	private boolean enable;
	public ParameterObject getSsid() {
		return ssid;
	}
	public void setSsid(ParameterObject ssid) {
		this.ssid = ssid;
	}
	public ParameterObject getStandard() {
		return standard;
	}
	public void setStandard(ParameterObject standard) {
		this.standard = standard;
	}
	public ParameterObject getChannel() {
		return channel;
	}
	public void setChannel(ParameterObject channel) {
		this.channel = channel;
	}
	public ParameterObject getPossibleChannels() {
		return possibleChannels;
	}
	public void setPossibleChannels(ParameterObject possibleChannels) {
		this.possibleChannels = possibleChannels;
	}
	public ParameterObject getPreSharedKey() {
		return preSharedKey;
	}
	public void setPreSharedKey(ParameterObject preSharedKey) {
		this.preSharedKey = preSharedKey;
	}
	public ParameterObject getwPAEncryptionModes() {
		return wPAEncryptionModes;
	}
	public void setwPAEncryptionModes(ParameterObject wPAEncryptionModes) {
		this.wPAEncryptionModes = wPAEncryptionModes;
	}
	public ParameterObject getwPAAuthenticationMode() {
		return wPAAuthenticationMode;
	}
	public void setwPAAuthenticationMode(ParameterObject wPAAuthenticationMode) {
		this.wPAAuthenticationMode = wPAAuthenticationMode;
	}
	public ParameterObject getBasicEncryptionModes() {
		return basicEncryptionModes;
	}
	public void setBasicEncryptionModes(ParameterObject basicEncryptionModes) {
		this.basicEncryptionModes = basicEncryptionModes;
	}
	public ParameterObject getBasicAuthenticationMode() {
		return basicAuthenticationMode;
	}
	public void setBasicAuthenticationMode(ParameterObject basicAuthenticationMode) {
		this.basicAuthenticationMode = basicAuthenticationMode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
}
