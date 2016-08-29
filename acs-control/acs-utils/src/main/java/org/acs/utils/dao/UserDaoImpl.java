package org.acs.utils.dao;

import java.util.List;

import org.acs.utils.model.Oper;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	@Override
	public List<Oper> findAllOper() {
		// TODO Auto-generated method stub
		return this.getMySqlSession().selectList("org.acs.dao.mapper.UserMapper.findAllOper");
	}

}
