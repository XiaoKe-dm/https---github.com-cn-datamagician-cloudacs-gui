package org.acs.core.service.impl;



import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import org.acs.core.model.Preset;
import org.acs.core.service.FilesService;
import org.acs.utils.tools.NBIStaticVariable;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseServiceImpl;
import org.acs.utils.tools.ACSConfiguration;
import org.acs.utils.tools.HttpUtil;
import org.springframework.stereotype.Repository;

@Repository("filesService")
public class FilesServiceImpl extends BaseServiceImpl implements FilesService{

	@Override
	public String getAllFiles() {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.FILES+"/";
		String json = HttpUtil.getContentByGet(url);
		return json;
	}

	@Override
	public String removeFilesByName(String name) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.FILES+"/"+name;
		String state = HttpUtil.getContentByDelete(url);
		return state;
	}

	@Override
	public String updateFile(File file, String fileType, String oui, String productClass, String version) {
		// TODO Auto-generated method stub
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.FILES+"/"+file.getName();
		String state = HttpUtil.getContentByFileUpload2(url, file, fileType, oui, productClass, version);
		return state;
	}

	@Override
	public String getFilesByDevice(String productClass, String oui) {
		// TODO Auto-generated method stub
		String query = "query={";
		String querycol = "";
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.FILES+"/?";
		if(oui!=null&&!"".equals(oui.trim())){
			oui = URLEncoder.encode(oui);
			querycol = querycol + ",\"metadata.oui\":\""+oui+"\"";
		}
		if(productClass!=null&&!"".equals(productClass.trim())){
			productClass = URLEncoder.encode(productClass);
			querycol = querycol + ",\"metadata.productClass\":\""+productClass+"\"";
		}
		/*if(version!=null&&!"".equals(version.trim())){
			version = URLEncoder.encode(version);
			querycol = querycol + ",\"metadata.version\":\""+version+"\"";
		}*/
		query = query + querycol.substring(1)+"}";
		String json = HttpUtil.getContentByGet(url+query);
		return json;
	}

	@Override
	public PageResults<String> getAllFilesPage(String query, int cPage) {
		// TODO Auto-generated method stub
		PageResults<String> presetPage = new PageResults<String>();
		presetPage.setCurrentPage(cPage);
		String limit = "limit="+PageResults.pageSize+"&skip="+PageResults.pageSize*(cPage-1);
		if(query!=null&&!"".equals(query))query = query + "&";
		String url = ACSConfiguration.ace_nbi_url+NBIStaticVariable.FILES+"/?"+query+limit;
		Map<String,Object> resultMap = HttpUtil.getContentByGetOnPage(url);
		String json = (String) resultMap.get(HttpUtil.RESULT);
		
		presetPage.setJsonResults(json);
		long totalCount = (long) resultMap.get(HttpUtil.TOTAL);
		presetPage.setTotalCount(totalCount);
		presetPage.setPageCountBySize(totalCount);
		
		return presetPage;
	}
	
}
