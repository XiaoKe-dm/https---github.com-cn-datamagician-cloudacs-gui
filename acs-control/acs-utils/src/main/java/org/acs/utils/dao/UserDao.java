package org.acs.utils.dao;

import java.util.List;

import org.acs.utils.model.Oper;
import org.acs.utils.model.User;

public interface UserDao extends BaseDao{

	List<Oper> findAllOper();

}
