package org.acs.utils.service;

import java.util.List;

import org.acs.utils.base.PageResults;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;

public interface UserService extends BaseService{

	public User isUserExist(User user);

	public List<Oper> findAllOper();

	public PageResults<User> findAllUserPage(String currentPage, String queryValue);

}
