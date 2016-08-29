package org.acs.core.service.impl;


import java.util.Map;

import org.acs.core.model.Configuration;
import org.acs.core.model.Device;
import org.acs.core.model.Precondition;
import org.acs.core.model.Preset;
import org.acs.core.service.PresetsService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseServiceImpl;
import org.acs.utils.tools.ACSConfiguration;
import org.springframework.stereotype.Repository;

@Repository("presetsService")
public class PresetsServiceImpl extends BaseServiceImpl implements PresetsService{

	@Override
	public String getAllPresets(String operId) {
		// TODO Auto-generated method stub
		String query = "";
		if(operId!=null){
			query = "?query={\"_oper\":\""+operId+"\"}";
		}
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.PRESET+"/"+query;
		String json = HttpUtil.getContentByGet(url);
		return json;
	}

	@Override
	public String removePresetById(String id) {
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.PRESET+"/";
		String state = HttpUtil.getContentByDelete(url+id);
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public String addPresets(String operId,Preset preset) {
		// TODO Auto-generated method stub
		
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.PRESET+"/"+preset.getPresetName();
		String params = "{ \"weight\": 0,";
		if(operId!=null){
			params = "{ \"_oper\":\""+operId+"\",\"weight\": 0,";
		}else{
			params = "{\",\"weight\": 0,";
		}
		String preconditionStr = "";
		String configurationStr = "";
		if(preset.getPreconditionList()!=null&&preset.getPreconditionList().size()>0){
			for(Precondition precondition:preset.getPreconditionList()){
				if(precondition.getName()!=null&&precondition.getValue()!=null&&precondition.getSymbol()!=null){
					preconditionStr = preconditionStr + ","+ "\\\"summary." + precondition.getName() + "\\\":";
					if("e".equals(precondition.getSymbol())){
						preconditionStr = preconditionStr + "\\\"" + precondition.getValue() + "\\\"";
					}else{
						preconditionStr = preconditionStr +"{\\\"\u0024"+precondition.getSymbol()+"\\\":\\\""+precondition.getValue()+"\\\"}";
					}
				}
				//System.out.println(preconditionStr);
			}
			preconditionStr = "\"precondition\": \"{" + preconditionStr.substring(1)+"}\"";
		}
		
		if(preset.getConfigurations()!=null&&preset.getConfigurations().size()>0){
			for(Configuration configuration:preset.getConfigurations()){
				if(configuration.getType()!=null&&configuration.getName()!=null&&configuration.getValue()!=null){
					String typekey="add_object";
					String valuekey = "object";
					if("Set".equals(configuration.getType())){
						typekey = "value";
						valuekey = "value";
					}else if("Remove Object".equals(configuration.getType())){
						typekey = "delete_object";
					}
					configurationStr =  configurationStr + "," + "{\"type\":\""+typekey+"\",\"name\":\""+configuration.getName()+"\",\""+valuekey+"\":\""+configuration.getValue()+"\"}";
				}
			}
			configurationStr = "\"configurations\": ["+configurationStr.substring(1)+"]";
		}
		if(!"".equals(configurationStr))configurationStr = ","+configurationStr;
		params = params + preconditionStr + configurationStr + "}";
		String state = HttpUtil.getContentByPut(url, params);
		return state;
	}

	@Override
	public String findPresetsById(String id) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.PRESET+"/?";
		String preset = HttpUtil.getContentByGet(url+"query={\"_id\":\""+id+"\"}");
		// TODO Auto-generated method stub
		return preset;
	}
	@Override
	public PageResults<Preset> getPresetsPage(String query, int cPage) {
		// TODO Auto-generated method stub
		PageResults<Preset> presetPage = new PageResults<Preset>();
		presetPage.setCurrentPage(cPage);
		String limit = "limit="+PageResults.pageSize+"&skip="+PageResults.pageSize*(cPage-1);
		if(query!=null&&!"".equals(query))query = query + "&";
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.PRESET+"/?"+query+limit;
		Map<String,Object> resultMap = HttpUtil.getContentByGetOnPage(url);
		String json = (String) resultMap.get(HttpUtil.RESULT);
		
		presetPage.setJsonResults(json);
		long totalCount = (long) resultMap.get(HttpUtil.TOTAL);
		presetPage.setTotalCount(totalCount);
		presetPage.setPageCountBySize(totalCount);
		
		return presetPage;
	}

}
