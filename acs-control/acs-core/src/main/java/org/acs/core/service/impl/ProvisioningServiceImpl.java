package org.acs.core.service.impl;

import java.util.Map;

import org.acs.core.model.Configuration;
import org.acs.core.model.Precondition;
import org.acs.core.model.Preset;
import org.acs.core.model.Provisioning;
import org.acs.core.service.ProvisioningService;
import org.acs.utils.base.PageResults;
import org.acs.utils.tools.ACSConfiguration;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.NBIStaticVariable;
import org.springframework.stereotype.Repository;

@Repository("provisioningService")
public class ProvisioningServiceImpl implements ProvisioningService {

	@Override
	public String getAllProvisioning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResults<Provisioning> getProvisioningPage(String query, int cPage) {
		// TODO Auto-generated method stub
		PageResults<Provisioning> provisioningPage = new PageResults<Provisioning>();
		provisioningPage.setCurrentPage(cPage);
		String limit = "limit="+PageResults.pageSize+"&skip="+PageResults.pageSize*(cPage-1);
		if(query!=null&&!"".equals(query))query = query + "&";
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.ACCOUNT+"/?"+query+limit;
		Map<String,Object> resultMap = HttpUtil.getContentByGetOnPage(url);
		String json = (String) resultMap.get(HttpUtil.RESULT);
		
		provisioningPage.setJsonResults(json);
		long totalCount = (long) resultMap.get(HttpUtil.TOTAL);
		provisioningPage.setTotalCount(totalCount);
		provisioningPage.setPageCountBySize(totalCount);
		
		return provisioningPage;
	}

	@Override
	public String removeProvisioningById(String name) {
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.ACCOUNT+"/";
		String state = HttpUtil.getContentByDelete(url+name);
		return state;
	}

	@Override
	public String addProvisioning(Provisioning provisioning) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.ACCOUNT+"/"+provisioning.getName();
		String params = "";
		if(provisioning.getOperId()==null){
			params = "{ \"mac\": \""+provisioning.getMac()+"\",\"username\":\""+provisioning.getUsername()+"@"+provisioning.getDomain()+"\",\"pwd\":\""+provisioning.getPassword()+"\"}";
		}else{
			params = "{ \"mac\": \""+provisioning.getMac()+"\",\"username\":\""+provisioning.getUsername()+"@"+provisioning.getDomain()+"\",\"pwd\":\""+provisioning.getPassword()+"\",\"oper\":\""+provisioning.getOperId()+"\"}";
		}
		String state = HttpUtil.getContentByPut(url, params);
		return state;
	}

	@Override
	public String findProvisioningById(String name) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.ACCOUNT+"/?";
		String provisioning = HttpUtil.getContentByGet(url+"query={\"_id\":\""+name+"\"}");
		// TODO Auto-generated method stub
		return provisioning;
	}


}
