package org.acs.utils.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.acs.utils.base.PageResults;
import org.acs.utils.base.RowMapper;
import org.acs.utils.model.AbstractEntity;
 
 
/**
 * @ClassName: BaseService
 * @Description: Service封装接口
 * @date 2014年11月21日21:05:17
 */
public interface BaseService{
     
	//添加单个实体
	public <T extends AbstractEntity> int insert(Class<T> entityClass,T entity);
	//添加实体集合
	public <T extends AbstractEntity> void insertAll(Class<T> entityClass,Collection<T> collection);
	//批量添加实体集合
	public <T extends AbstractEntity> void insertBatch(Class<T> entityClass,List<T> collection) throws Exception;
	//更新单个实体
	public <T extends AbstractEntity> void update(Class<T> entityClass,T entity);
	//更新实体集合
	public <T extends AbstractEntity> void updateAll(Class<T> entityClass,Collection<T> collection);
	//删除单个实体
	public <T extends AbstractEntity> int delete(Class<T> entityClass,T entity);
	//根据sid删除实体
	public <T extends AbstractEntity> int deleteByPrimaryKey(Class<T> entityClass,long sid);
	//删除所有
	public <T extends AbstractEntity> void deleteAll(Class<T> entityClass);
	//根据sid集合删除所有
	public <T extends AbstractEntity> void deleteAllByPrimaryKey(Class<T> entityClass,Collection<Long> sids);
	//查询所有
	public <T extends AbstractEntity> List<T> findAll(Class<T> entityClass);
	//根据条件查询所有
	public <T extends AbstractEntity> List<T> findByParams(Class<T> entityClass,Map<String, Object> params);
	//根据sid查询实体
	public <T extends AbstractEntity> T findByPrimaryKey(Class<T> entityClass,Long id);
	//根据sid集合查询所有
	public <T extends AbstractEntity> List<T> findByPrimaryKeys(Class<T> entityClass,Long[] sids);
	//根据条件删除所有集合
	public <T extends AbstractEntity> void deleteByParams(Class<T> entityClass,Map<String, Object> params);
	//根据条件得到数量
	public <T extends AbstractEntity> long findCount(Class<T> entityClass,Map<String, Object> params);
	//分页查询
	public <T extends AbstractEntity> PageResults findByParamsPage(Class<T> entityClass,Map<String, Object> params,int pageNo, int pageSize);
 
}
