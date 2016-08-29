package org.acs.core.service;

import java.io.File;

import org.acs.core.model.Preset;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseService;

public interface FilesService extends BaseService{

	String getAllFiles();
	
	String removeFilesByName(String name);

	String updateFile(File file, String fileType, String oui, String productClass, String version);

	String getFilesByDevice(String productClass, String oui);

	PageResults<String> getAllFilesPage(String query, int cPage);

}
