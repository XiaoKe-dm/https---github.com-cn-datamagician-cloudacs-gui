package org.acs.core.service;


import org.acs.core.model.Preset;
import org.acs.utils.base.PageResults;
import org.acs.utils.service.BaseService;

public interface PresetsService extends BaseService{

	String getAllPresets(String operId);

	String removePresetById(String id);

	String addPresets(String operId,Preset preset);

	String findPresetsById(String id);

	PageResults<Preset> getPresetsPage(String query, int cPage);


}
