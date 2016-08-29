package org.acs.core.service;

import java.util.List;
import java.util.Map;

import org.acs.core.model.Device;
import org.acs.core.model.Modules;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseService;

public interface ModulesService extends BaseService{

	String insertByNBI(Modules modules);

	String deleteByNBI(Modules modules);

	List<Device> findDeviceByModules(String modulesName);
	
	String findParameterByModules(String modulesName);

	PageResults<Modules> findModulesByParamsPage(Map<String, Object> params);
}
