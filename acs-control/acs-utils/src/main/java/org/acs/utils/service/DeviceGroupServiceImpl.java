package org.acs.utils.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acs.utils.dao.DeviceGroupDao;
import org.acs.utils.dao.OperDao;
import org.acs.utils.model.Oper;
import org.acs.utils.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deviceGroupService")
public class DeviceGroupServiceImpl extends BaseServiceImpl implements DeviceGroupService{

	@Autowired
	private DeviceGroupDao deviceGroupdao;

}
