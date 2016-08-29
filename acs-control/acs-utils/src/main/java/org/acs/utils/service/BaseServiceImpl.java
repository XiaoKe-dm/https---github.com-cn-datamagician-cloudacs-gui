package org.acs.utils.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.acs.utils.base.PageResults;
import org.acs.utils.dao.BaseDao;
import org.acs.utils.model.AbstractEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
/**
 * @ClassName: BaseServiceImpl
 * @Description: BaseService实现
 * @date 2014年11月21日21:05:17
 *
 */
@Transactional
@Service("baseService")
public class BaseServiceImpl implements BaseService {
     
	@Resource
    private BaseDao baseDao;

	//添加单个实体
	@Override
	public <T extends AbstractEntity> int insert(Class<T> entityClass, T entity) {
		// TODO Auto-generated method stub
		return baseDao.insert(entityClass, entity);
	}

	//添加实体集合
	@Override
	public <T extends AbstractEntity> void insertAll(Class<T> entityClass,
			Collection<T> collection) {
		// TODO Auto-generated method stub
		baseDao.insertAll(entityClass, collection);
	}

	//批量添加实体集合
	@Override
	public <T extends AbstractEntity> void insertBatch(Class<T> entityClass,
			List<T> collection) throws Exception {
		// TODO Auto-generated method stub
		baseDao.insertBatch(entityClass, collection);
	}

	//更新单个实体
	@Override
	public <T extends AbstractEntity> void update(Class<T> entityClass, T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entityClass, entity);
	}

	//更新实体集合
	@Override
	public <T extends AbstractEntity> void updateAll(Class<T> entityClass,
			Collection<T> collection) {
		// TODO Auto-generated method stub
		baseDao.updateAll(entityClass, collection);
	}

	//删除单个实体
	@Override
	public <T extends AbstractEntity> int delete(Class<T> entityClass, T entity) {
		// TODO Auto-generated method stub
		return baseDao.delete(entityClass, entity);
	}

	//根据sid删除实体
	@Override
	public <T extends AbstractEntity> int deleteByPrimaryKey(
			Class<T> entityClass, long sid) {
		// TODO Auto-generated method stub
		return baseDao.deleteByPrimaryKey(entityClass, sid);
	}

	//删除所有
	@Override
	public <T extends AbstractEntity> void deleteAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		baseDao.deleteAll(entityClass);
	}

	//根据sid集合删除所有
	@Override
	public <T extends AbstractEntity> void deleteAllByPrimaryKey(
			Class<T> entityClass, Collection<Long> sids) {
		// TODO Auto-generated method stub
		baseDao.deleteAllByPrimaryKey(entityClass, sids);
	}

	//查询所有
	@Override
	public <T extends AbstractEntity> List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return baseDao.findAll(entityClass);
	}

	//根据条件查询所有
	@Override
	public <T extends AbstractEntity> List<T> findByParams(
			Class<T> entityClass, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDao.findByParams(entityClass, params);
	}

	//根据sid查询实体
	@Override
	public <T extends AbstractEntity> T findByPrimaryKey(Class<T> entityClass,
			Long id) {
		// TODO Auto-generated method stub
		return baseDao.findByPrimaryKey(entityClass, id);
	}

	//根据sid集合查询所有
	@Override
	public <T extends AbstractEntity> List<T> findByPrimaryKeys(
			Class<T> entityClass, Long[] sids) {
		// TODO Auto-generated method stub
		return baseDao.findByPrimaryKeys(entityClass, sids);
	}

	//根据条件删除所有集合
	@Override
	public <T extends AbstractEntity> void deleteByParams(Class<T> entityClass,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		baseDao.deleteByParams(entityClass, params);
	}

	//根据条件得到数量
	@Override
	public <T extends AbstractEntity> long findCount(Class<T> entityClass,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseDao.findCount(entityClass, params);
	}
	//分页查询
	@Override
	public <T extends AbstractEntity> PageResults findByParamsPage(
			Class<T> entityClass, Map<String, Object> params,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findByParamsPage(entityClass,params,pageNo,pageSize);
	}
 
 
}