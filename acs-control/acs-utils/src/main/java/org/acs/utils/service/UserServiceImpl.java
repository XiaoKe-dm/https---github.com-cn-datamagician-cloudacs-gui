package org.acs.utils.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acs.utils.base.PageResults;
import org.acs.utils.dao.UserDao;
import org.acs.utils.model.Oper;
import org.acs.utils.model.User;
import org.acs.utils.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;

	@Override
	public User isUserExist(User user) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		params.put("password", MD5Util.string2MD5(user.getPassword()));
		List<User> userList = userdao.findByParams(User.class, params);
		if(userList!=null&&userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<Oper> findAllOper() {
		// TODO Auto-generated method stub
		return userdao.findAllOper();
	}

	@Override
	public PageResults<User> findAllUserPage(String currentPage, String queryValue) {
		// TODO Auto-generated method stub
		PageResults<User> userPage = new PageResults<User>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("limit", PageResults.pageSize);
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		params.put("start", (cPage-1)*PageResults.pageSize);
		if(queryValue!=null&&!"".equals(queryValue))params.put("username", "%"+queryValue+"%");
		List<User> userList = userdao.findByParams(User.class, params);
		userPage.setResults(userList);
		userPage.setCurrentPage(cPage);
		long totalCount = userdao.findCount(User.class, params);
		userPage.setPageCountBySize(totalCount);
		return userPage;
	}
}
