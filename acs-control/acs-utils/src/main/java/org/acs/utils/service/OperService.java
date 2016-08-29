package org.acs.utils.service;

import java.util.List;
import java.util.Map;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;

public interface OperService extends BaseService{

	List<Oper> findAllOperAndGroup(Oper oper);

	void insertOperModule(Oper oper_module);

	void deleteOperModule(Oper oper);

	List<Oper> findModlesByUser(User user);

	PageResults<Oper> findAllOperAndGroupPage(String currentPage,String queryValue);

	String deleteByNBI(Oper oper);
	
	String insertByNBI(Oper oper);
}
