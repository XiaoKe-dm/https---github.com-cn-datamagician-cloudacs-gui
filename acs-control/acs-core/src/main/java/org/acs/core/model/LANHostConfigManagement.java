package org.acs.core.model;

import java.util.List;

public class LANHostConfigManagement {

	private VersionObject DHCPServerConfigurable;
	private VersionObject DHCPServerEnable;
	private VersionObject DHCPRelay;
	private VersionObject MaxAddress;
	private VersionObject MinAddress;
	private VersionObject ReservedAddresses;
	private VersionObject SubnetMask;
	private VersionObject DNSServers;
	private VersionObject DomainName;
	private VersionObject IPRouters;
	private VersionObject DHCPLeaseTime;
	private VersionObject IPInterfaceNumberOfEntries;
	private List<IPInterface> IPInterface;
	private List<DHCPConditionalServingPool> DHCPConditionalServingPool;
	public VersionObject getDHCPServerConfigurable() {
		return DHCPServerConfigurable;
	}
	public void setDHCPServerConfigurable(VersionObject dHCPServerConfigurable) {
		DHCPServerConfigurable = dHCPServerConfigurable;
	}
	public VersionObject getDHCPServerEnable() {
		return DHCPServerEnable;
	}
	public void setDHCPServerEnable(VersionObject dHCPServerEnable) {
		DHCPServerEnable = dHCPServerEnable;
	}
	public VersionObject getDHCPRelay() {
		return DHCPRelay;
	}
	public void setDHCPRelay(VersionObject dHCPRelay) {
		DHCPRelay = dHCPRelay;
	}
	public VersionObject getMaxAddress() {
		return MaxAddress;
	}
	public void setMaxAddress(VersionObject maxAddress) {
		MaxAddress = maxAddress;
	}
	public VersionObject getMinAddress() {
		return MinAddress;
	}
	public void setMinAddress(VersionObject minAddress) {
		MinAddress = minAddress;
	}
	public VersionObject getReservedAddresses() {
		return ReservedAddresses;
	}
	public void setReservedAddresses(VersionObject reservedAddresses) {
		ReservedAddresses = reservedAddresses;
	}
	public VersionObject getSubnetMask() {
		return SubnetMask;
	}
	public void setSubnetMask(VersionObject subnetMask) {
		SubnetMask = subnetMask;
	}
	public VersionObject getDNSServers() {
		return DNSServers;
	}
	public void setDNSServers(VersionObject dNSServers) {
		DNSServers = dNSServers;
	}
	public VersionObject getDomainName() {
		return DomainName;
	}
	public void setDomainName(VersionObject domainName) {
		DomainName = domainName;
	}
	public VersionObject getIPRouters() {
		return IPRouters;
	}
	public void setIPRouters(VersionObject iPRouters) {
		IPRouters = iPRouters;
	}
	public VersionObject getDHCPLeaseTime() {
		return DHCPLeaseTime;
	}
	public void setDHCPLeaseTime(VersionObject dHCPLeaseTime) {
		DHCPLeaseTime = dHCPLeaseTime;
	}
	public VersionObject getIPInterfaceNumberOfEntries() {
		return IPInterfaceNumberOfEntries;
	}
	public void setIPInterfaceNumberOfEntries(VersionObject iPInterfaceNumberOfEntries) {
		IPInterfaceNumberOfEntries = iPInterfaceNumberOfEntries;
	}
	public List<IPInterface> getIPInterface() {
		return IPInterface;
	}
	public void setIPInterface(List<IPInterface> iPInterface) {
		IPInterface = iPInterface;
	}
	public List<DHCPConditionalServingPool> getDHCPConditionalServingPool() {
		return DHCPConditionalServingPool;
	}
	public void setDHCPConditionalServingPool(List<DHCPConditionalServingPool> dHCPConditionalServingPool) {
		DHCPConditionalServingPool = dHCPConditionalServingPool;
	}
	
}
