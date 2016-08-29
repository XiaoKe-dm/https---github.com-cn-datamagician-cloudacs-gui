package org.acs.utils.dao;

import java.util.List;
import java.util.Map;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;

public interface OperDao extends BaseDao{

	List<Oper> findAllOperAndGroup(Oper oper);

	void insertOperModule(Oper oper_module);

	void deleteOperModule(Oper oper);

	List<Oper> findOperModule(Oper oper);

	List<Oper> findAllOperAndGroupPage(Map<String, Object> params);

	long findAllOperCount(Map<String, Object> params);


}
