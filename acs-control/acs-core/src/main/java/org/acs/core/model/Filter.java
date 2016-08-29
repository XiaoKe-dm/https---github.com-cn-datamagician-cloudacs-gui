package org.acs.core.model;

public class Filter {

	private VersionObject FilterKey;
	private VersionObject FilterEnable;
	private VersionObject FilterStatus;
	private VersionObject FilterBridgeReference;
	private VersionObject FilterInterface;
	private VersionObject SourceMACFromVendorClassIDFilter;
	private VersionObject SourceMACFromVendorClassIDFilterExclude;
	public VersionObject getFilterKey() {
		return FilterKey;
	}
	public void setFilterKey(VersionObject filterKey) {
		FilterKey = filterKey;
	}
	public VersionObject getFilterEnable() {
		return FilterEnable;
	}
	public void setFilterEnable(VersionObject filterEnable) {
		FilterEnable = filterEnable;
	}
	public VersionObject getFilterStatus() {
		return FilterStatus;
	}
	public void setFilterStatus(VersionObject filterStatus) {
		FilterStatus = filterStatus;
	}
	public VersionObject getFilterBridgeReference() {
		return FilterBridgeReference;
	}
	public void setFilterBridgeReference(VersionObject filterBridgeReference) {
		FilterBridgeReference = filterBridgeReference;
	}
	public VersionObject getFilterInterface() {
		return FilterInterface;
	}
	public void setFilterInterface(VersionObject filterInterface) {
		FilterInterface = filterInterface;
	}
	public VersionObject getSourceMACFromVendorClassIDFilter() {
		return SourceMACFromVendorClassIDFilter;
	}
	public void setSourceMACFromVendorClassIDFilter(VersionObject sourceMACFromVendorClassIDFilter) {
		SourceMACFromVendorClassIDFilter = sourceMACFromVendorClassIDFilter;
	}
	public VersionObject getSourceMACFromVendorClassIDFilterExclude() {
		return SourceMACFromVendorClassIDFilterExclude;
	}
	public void setSourceMACFromVendorClassIDFilterExclude(VersionObject sourceMACFromVendorClassIDFilterExclude) {
		SourceMACFromVendorClassIDFilterExclude = sourceMACFromVendorClassIDFilterExclude;
	}
	
	
}
