package org.acs.core.model;

import java.util.List;

public class Layer2Bridging {

	private VersionObject MaxBridgeEntries;
	private VersionObject MaxFilterEntries;
	private VersionObject MaxMarkingEntries;
	private VersionObject BridgeNumberOfEntries;
	private VersionObject FilterNumberOfEntries;
	private VersionObject MarkingNumberOfEntries;
	private VersionObject AvailableInterfaceNumberOfEntries;
	private List<Bridge> Bridge;
	private List<Filter> Filter;
	private List<AvailableInterface> AvailableInterface;
	public VersionObject getMaxBridgeEntries() {
		return MaxBridgeEntries;
	}
	public void setMaxBridgeEntries(VersionObject maxBridgeEntries) {
		MaxBridgeEntries = maxBridgeEntries;
	}
	public VersionObject getMaxFilterEntries() {
		return MaxFilterEntries;
	}
	public void setMaxFilterEntries(VersionObject maxFilterEntries) {
		MaxFilterEntries = maxFilterEntries;
	}
	public VersionObject getMaxMarkingEntries() {
		return MaxMarkingEntries;
	}
	public void setMaxMarkingEntries(VersionObject maxMarkingEntries) {
		MaxMarkingEntries = maxMarkingEntries;
	}
	public VersionObject getBridgeNumberOfEntries() {
		return BridgeNumberOfEntries;
	}
	public void setBridgeNumberOfEntries(VersionObject bridgeNumberOfEntries) {
		BridgeNumberOfEntries = bridgeNumberOfEntries;
	}
	public VersionObject getFilterNumberOfEntries() {
		return FilterNumberOfEntries;
	}
	public void setFilterNumberOfEntries(VersionObject filterNumberOfEntries) {
		FilterNumberOfEntries = filterNumberOfEntries;
	}
	public VersionObject getMarkingNumberOfEntries() {
		return MarkingNumberOfEntries;
	}
	public void setMarkingNumberOfEntries(VersionObject markingNumberOfEntries) {
		MarkingNumberOfEntries = markingNumberOfEntries;
	}
	public VersionObject getAvailableInterfaceNumberOfEntries() {
		return AvailableInterfaceNumberOfEntries;
	}
	public void setAvailableInterfaceNumberOfEntries(VersionObject availableInterfaceNumberOfEntries) {
		AvailableInterfaceNumberOfEntries = availableInterfaceNumberOfEntries;
	}
	public List<Bridge> getBridge() {
		return Bridge;
	}
	public void setBridge(List<Bridge> bridge) {
		Bridge = bridge;
	}
	public List<Filter> getFilter() {
		return Filter;
	}
	public void setFilter(List<Filter> filter) {
		Filter = filter;
	}
	public List<AvailableInterface> getAvailableInterface() {
		return AvailableInterface;
	}
	public void setAvailableInterface(List<AvailableInterface> availableInterface) {
		AvailableInterface = availableInterface;
	}
	
	
}
