package org.acs.utils.dao;

import java.util.List;
import java.util.Map;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.springframework.stereotype.Repository;

@Repository("operDao")
public class OperDaoImpl extends BaseDaoImpl implements OperDao{

	@Override
	public List<Oper> findAllOperAndGroup(Oper oper) {
		// TODO Auto-generated method stub
		return this.getMySqlSession().selectList("org.acs.dao.mapper.OperMapper.findOperDeviceGroup",oper);
	}

	@Override
	public void insertOperModule(Oper oper_module) {
		// TODO Auto-generated method stub
		this.getMySqlSession().insert("org.acs.dao.mapper.OperMapper.insertOperModule",oper_module);
	}

	@Override
	public void deleteOperModule(Oper oper) {
		// TODO Auto-generated method stub
		this.getMySqlSession().insert("org.acs.dao.mapper.OperMapper.deleteOperModule",oper);
	}

	@Override
	public List<Oper> findOperModule(Oper oper) {
		// TODO Auto-generated method stub
		return this.getMySqlSession().selectList("org.acs.dao.mapper.OperMapper.findOperModule",oper);
	}
	
	@Override
	public List<Oper> findAllOperAndGroupPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.getMySqlSession().selectList("org.acs.dao.mapper.OperMapper.findOperDeviceGroup",params);
	}

	@Override
	public long findAllOperCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.getMySqlSession().selectOne("org.acs.dao.mapper.OperMapper.findCount",params);
	}


}
