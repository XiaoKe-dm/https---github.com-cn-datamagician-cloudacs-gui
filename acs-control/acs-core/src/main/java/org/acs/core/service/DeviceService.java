package org.acs.core.service;

import java.util.List;

import org.acs.core.model.Device;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.User;
import org.acs.utils.service.BaseService;

public interface DeviceService extends BaseService{

	List<Device> getAllDevice(User user);

	String getDeviceById(String id);

	String addTagById(String id, String tagId);

	String searchTagById(String id);

	String removeTagById(String id, String tagName);

	String showParametersById(String id);
	
	String getDeviceIPAndMACById(String id);
	
	PageResults<Device> getDevicePage(String query,int currentPage);

	String getDeviceHostById(String id);
	
	String getDeviceUsernameById(String id);

	String getDeviceWifiById(String id);

	String getDeviceModuleById(String id);

}
