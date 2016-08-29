package org.acs.core.model;

import java.util.List;

public class WLANConfiguration {
	private VersionObject Enable;
	private VersionObject Status;
	private VersionObject BSSID;
	private VersionObject MaxBitRate;
	private VersionObject Channel;
	private VersionObject SSID;
	private VersionObject BeaconType;
	private VersionObject MACAddressControlEnabled;
	private VersionObject Standard;
	private VersionObject WEPKeyIndex;
	private VersionObject WEPEncryptionLevel;
	private VersionObject BasicEncryptionModes;
	private VersionObject BasicAuthenticationMode;
	private VersionObject WPAEncryptionModes;
	private VersionObject WPAAuthenticationMode;
	private VersionObject IEEE11iEncryptionModes;
	private VersionObject IEEE11iAuthenticationMode;
	private VersionObject PossibleChannels;
	private VersionObject BasicDataTransmitRates;
	private VersionObject RadioEnabled;
	private VersionObject RegulatoryDomain;
	private VersionObject TotalBytesSent;
	private VersionObject TotalBytesReceived;
	private VersionObject TotalPacketsSent;
	private VersionObject TotalPacketsReceived;
	private VersionObject AssociatedDevice;
	private List<WEPKey> WEPKey;
	private List<PreSharedKey> PreSharedKey;
	public VersionObject getEnable() {
		return Enable;
	}
	public void setEnable(VersionObject enable) {
		Enable = enable;
	}
	public VersionObject getStatus() {
		return Status;
	}
	public void setStatus(VersionObject status) {
		Status = status;
	}
	public VersionObject getBSSID() {
		return BSSID;
	}
	public void setBSSID(VersionObject bSSID) {
		BSSID = bSSID;
	}
	public VersionObject getMaxBitRate() {
		return MaxBitRate;
	}
	public void setMaxBitRate(VersionObject maxBitRate) {
		MaxBitRate = maxBitRate;
	}
	public VersionObject getChannel() {
		return Channel;
	}
	public void setChannel(VersionObject channel) {
		Channel = channel;
	}
	public VersionObject getSSID() {
		return SSID;
	}
	public void setSSID(VersionObject sSID) {
		SSID = sSID;
	}
	public VersionObject getBeaconType() {
		return BeaconType;
	}
	public void setBeaconType(VersionObject beaconType) {
		BeaconType = beaconType;
	}
	public VersionObject getMACAddressControlEnabled() {
		return MACAddressControlEnabled;
	}
	public void setMACAddressControlEnabled(VersionObject mACAddressControlEnabled) {
		MACAddressControlEnabled = mACAddressControlEnabled;
	}
	public VersionObject getStandard() {
		return Standard;
	}
	public void setStandard(VersionObject standard) {
		Standard = standard;
	}
	public VersionObject getWEPKeyIndex() {
		return WEPKeyIndex;
	}
	public void setWEPKeyIndex(VersionObject wEPKeyIndex) {
		WEPKeyIndex = wEPKeyIndex;
	}
	public VersionObject getWEPEncryptionLevel() {
		return WEPEncryptionLevel;
	}
	public void setWEPEncryptionLevel(VersionObject wEPEncryptionLevel) {
		WEPEncryptionLevel = wEPEncryptionLevel;
	}
	public VersionObject getBasicEncryptionModes() {
		return BasicEncryptionModes;
	}
	public void setBasicEncryptionModes(VersionObject basicEncryptionModes) {
		BasicEncryptionModes = basicEncryptionModes;
	}
	public VersionObject getBasicAuthenticationMode() {
		return BasicAuthenticationMode;
	}
	public void setBasicAuthenticationMode(VersionObject basicAuthenticationMode) {
		BasicAuthenticationMode = basicAuthenticationMode;
	}
	public VersionObject getWPAEncryptionModes() {
		return WPAEncryptionModes;
	}
	public void setWPAEncryptionModes(VersionObject wPAEncryptionModes) {
		WPAEncryptionModes = wPAEncryptionModes;
	}
	public VersionObject getWPAAuthenticationMode() {
		return WPAAuthenticationMode;
	}
	public void setWPAAuthenticationMode(VersionObject wPAAuthenticationMode) {
		WPAAuthenticationMode = wPAAuthenticationMode;
	}
	public VersionObject getIEEE11iEncryptionModes() {
		return IEEE11iEncryptionModes;
	}
	public void setIEEE11iEncryptionModes(VersionObject iEEE11iEncryptionModes) {
		IEEE11iEncryptionModes = iEEE11iEncryptionModes;
	}
	public VersionObject getIEEE11iAuthenticationMode() {
		return IEEE11iAuthenticationMode;
	}
	public void setIEEE11iAuthenticationMode(VersionObject iEEE11iAuthenticationMode) {
		IEEE11iAuthenticationMode = iEEE11iAuthenticationMode;
	}
	public VersionObject getPossibleChannels() {
		return PossibleChannels;
	}
	public void setPossibleChannels(VersionObject possibleChannels) {
		PossibleChannels = possibleChannels;
	}
	public VersionObject getBasicDataTransmitRates() {
		return BasicDataTransmitRates;
	}
	public void setBasicDataTransmitRates(VersionObject basicDataTransmitRates) {
		BasicDataTransmitRates = basicDataTransmitRates;
	}
	public VersionObject getRadioEnabled() {
		return RadioEnabled;
	}
	public void setRadioEnabled(VersionObject radioEnabled) {
		RadioEnabled = radioEnabled;
	}
	public VersionObject getRegulatoryDomain() {
		return RegulatoryDomain;
	}
	public void setRegulatoryDomain(VersionObject regulatoryDomain) {
		RegulatoryDomain = regulatoryDomain;
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
	public VersionObject getAssociatedDevice() {
		return AssociatedDevice;
	}
	public void setAssociatedDevice(VersionObject associatedDevice) {
		AssociatedDevice = associatedDevice;
	}
	public List<WEPKey> getWEPKey() {
		return WEPKey;
	}
	public void setWEPKey(List<WEPKey> wEPKey) {
		WEPKey = wEPKey;
	}
	public List<PreSharedKey> getPreSharedKey() {
		return PreSharedKey;
	}
	public void setPreSharedKey(List<PreSharedKey> preSharedKey) {
		PreSharedKey = preSharedKey;
	}
	
	
}
