package org.acs.utils.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.dao.OperDao;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.tools.ACSConfiguration;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("operService")
public class OperServiceImpl extends BaseServiceImpl implements OperService{

	@Autowired
	private OperDao operdao;

	@Override
	public List<Oper> findAllOperAndGroup(Oper oper) {
		// TODO Auto-generated method stub
		return operdao.findAllOperAndGroup(oper);
	}
	@Override
	public PageResults<Oper> findAllOperAndGroupPage(String currentPage,String queryValue) {
		// TODO Auto-generated method stub
		PageResults<Oper> operPage = new PageResults<Oper>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("limit", PageResults.pageSize);
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		params.put("start", (cPage-1)*PageResults.pageSize);
		if(queryValue!=null&&!"".equals(queryValue))params.put("name", "%"+queryValue+"%");
		List<Oper> operList = operdao.findByParams(Oper.class, params);
		List<Oper> newOperList = new ArrayList<Oper>();
		for(Oper oper:operList){
			newOperList.addAll(operdao.findAllOperAndGroup(oper));
		}
		operPage.setResults(newOperList);
		operPage.setCurrentPage(cPage);
		long totalCount = operdao.findAllOperCount(params);
		operPage.setPageCountBySize(totalCount);
		return operPage;
	}

	@Override
	public void insertOperModule(Oper oper_module) {
		// TODO Auto-generated method stub
		operdao.insertOperModule(oper_module);
	}

	@Override
	public void deleteOperModule(Oper oper) {
		// TODO Auto-generated method stub
		operdao.deleteOperModule(oper);
	}

	@Override
	public List<Oper> findModlesByUser(User user) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", user.getOperId());
		// TODO Auto-generated method stub
		List<Oper> operList = operdao.findByParams(Oper.class, params);
		if(operList!=null&&operList.size()>0){
			operList = operdao.findOperModule(operList.get(0));
		}
		return operList;
	}
	@Override
	public String insertByNBI(Oper oper) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.OPERS+"/"+oper.getSid();
		String[] domains = oper.getDomains().split(",");
		StringBuffer domainStr = new StringBuffer();
		domainStr = domainStr.append("[");
		for(int i=0;i<domains.length;i++){
			if(i!=0)domainStr.append(",");
			domainStr.append("\"").append(domains[i].trim()).append("\"");
		}
		domainStr = domainStr.append("]");
		String params = "{ \"operatorName\": \""+oper.getName()+"\", \"domains\":"+domainStr.toString()+"}";
		String state = HttpUtil.getContentByPut(url, params);
		return state;
	}
	@Override
	public String deleteByNBI(Oper oper) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.OPERS+"/"+oper.getSid();
		String state = HttpUtil.getContentByDelete(url);
		return state;
	}

}
