package org.acs.core.service;

import org.acs.core.model.Provisioning;
import org.acs.utils.base.PageResults;

public interface ProvisioningService {

	String getAllProvisioning();

	PageResults<Provisioning> getProvisioningPage(String query, int cPage);

	String removeProvisioningById(String name);

	String addProvisioning(Provisioning provisioning);

	String findProvisioningById(String name);

}
