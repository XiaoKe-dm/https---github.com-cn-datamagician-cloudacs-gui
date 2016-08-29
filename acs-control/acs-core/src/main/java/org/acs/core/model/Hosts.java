package org.acs.core.model;

import java.util.List;

public class Hosts {

	private VersionObject HostNumberOfEntries;
	private List<HostObject> Host;
	public VersionObject getHostNumberOfEntries() {
		return HostNumberOfEntries;
	}
	public void setHostNumberOfEntries(VersionObject hostNumberOfEntries) {
		HostNumberOfEntries = hostNumberOfEntries;
	}
	public List<HostObject> getHost() {
		return Host;
	}
	public void setHost(List<HostObject> host) {
		Host = host;
	}
	
	
}
