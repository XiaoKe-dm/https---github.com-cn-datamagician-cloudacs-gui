package org.acs.core.model;

public class Layer3Forwarding {

	private VersionObject DefaultConnectionService;
	private VersionObject ForwardNumberOfEntries;
	//private List<Forwarding> Forwarding
	public VersionObject getDefaultConnectionService() {
		return DefaultConnectionService;
	}
	public void setDefaultConnectionService(VersionObject defaultConnectionService) {
		DefaultConnectionService = defaultConnectionService;
	}
	public VersionObject getForwardNumberOfEntries() {
		return ForwardNumberOfEntries;
	}
	public void setForwardNumberOfEntries(VersionObject forwardNumberOfEntries) {
		ForwardNumberOfEntries = forwardNumberOfEntries;
	}
	
}
