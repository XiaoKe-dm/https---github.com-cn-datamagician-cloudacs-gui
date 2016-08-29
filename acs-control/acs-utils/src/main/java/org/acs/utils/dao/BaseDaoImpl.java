package org.acs.utils.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.acs.utils.base.PageResults;
import org.acs.utils.model.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: BaseDaoImpl
 * @Description: baseDao实现
 * @date 2014年11月21日21:05:17
 *
 */
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {
	private static Logger logger = LogManager.getLogger(BaseDaoImpl.class.getName());

	@Autowired
	@Qualifier("mySqlSession")
	private SqlSession mySqlSession;
	
	
	public SqlSession getMySqlSession(){
		return this.mySqlSession;
	}
	
	@Autowired
	@Qualifier("mySqlBatchSessionFactory")
	private SqlSessionFactory mySqlSessionFactory;
	
	public SqlSessionFactory getMySqlSessionFactory() {
		return this.mySqlSessionFactory;
	}

	//添加单个实体
	public <T extends AbstractEntity> int insert(Class<T> entityClass,T entity) {
		return getMySqlSession().insert("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.insert",entity);
	}
	//添加实体集合
	public <T extends AbstractEntity> void insertAll(Class<T> entityClass,Collection<T> collection) {
		for(T entity:collection){
			getMySqlSession().insert("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.insert",entity);
		}
	}
	//insert batch
	public <T extends AbstractEntity> void insertBatch(Class<T> entityClass, List<T> collection) throws Exception{
		SqlSession session = getMySqlSessionFactory().openSession(ExecutorType.BATCH, false);
		
		final int BATCH_SIZE = 2000;//0;
		logger.info("in insertBatch Size -- "+BATCH_SIZE);
		try {
			List<T> temp = new ArrayList<T>();
			int size = collection.size();
			for (int i = 0; i < size; i++) {
				temp.add(collection.get(i));
				if(i%BATCH_SIZE == 0 || i == collection.size()-1){
					session.insert("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.insertBatch",temp);
					session.commit();
					session.clearCache();
					
					temp = null;
					temp = new ArrayList<T>();
					logger.info("in insertBatch commit -- "+ i);
				}
			}
		} catch (Exception e) {
			logger.error("insertBath exception: "+e.toString());
			session.rollback();
			throw new Exception("insertBatch error!");
		}finally{
			session.close();
		}
	}
	
	//更新单个实体
	public <T extends AbstractEntity> void update(Class<T> entityClass,T entity) {
		getMySqlSession().update("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.update",entity);
	}
	//更新实体集合
	public <T extends AbstractEntity> void updateAll(Class<T> entityClass,Collection<T> collection) {
		for(T entity:collection){
			getMySqlSession().update("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.update",entity);
		}
	}
	//删除单个实体
	public <T extends AbstractEntity> int delete(Class<T> entityClass,T entity) {
		return getMySqlSession().delete("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.delete",entity);
	}
	//根据sid删除实体
	public <T extends AbstractEntity> int deleteByPrimaryKey(Class<T> entityClass,Long sid) {
		return getMySqlSession().delete("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.deleteByKey", sid);
	}
	//删除所有
	public <T extends AbstractEntity> void deleteAll(Class<T> entityClass) {
		getMySqlSession().delete("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.delete");
	}
	//根据条件删除所有
	public <T extends AbstractEntity> void deleteByParams(Class<T> entityClass,Map<String, Object> params) {
		getMySqlSession().delete("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.delete",params);
	}
	//根据sid集合删除所有
	public <T extends AbstractEntity> void deleteAllByPrimaryKey(Class<T> entityClass,Collection<Long> sids) {
		for(long id:sids){
			getMySqlSession().delete("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.deleteByKey", id);
		}
	}
	//查询所有
	public <T extends AbstractEntity> List<T> findAll(Class<T> entityClass) {
		return getMySqlSession().selectList("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.find");
	}
	//根据条件查询所有
	public <T extends AbstractEntity> List<T> findByParams(Class<T> entityClass,Map<String, Object> params) {
		return getMySqlSession().selectList("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.find", params);
	}
	//根据sid查询实体
	public <T extends AbstractEntity> T findByPrimaryKey(Class<T> entityClass,Long id) {
		return getMySqlSession().selectOne("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.findByPrimaryKey", id);
	}
	//根据sid集合查询所有
	@SuppressWarnings("unchecked")
	public <T extends AbstractEntity> List<T> findByPrimaryKeys(Class<T> entityClass,Long[] sids) {
		List<T> list = new ArrayList<T>();
		for(long id:sids){
			list.add(((T) getMySqlSession().selectOne("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.findByPrimaryKey", id)));
		}
		return list;
	}
	//根据条件得到数量
	public <T extends AbstractEntity> long findCount(Class<T> entityClass,Map<String, Object> params){
		long temp = getMySqlSession().selectOne("org.acs.dao.mapper."+getClassName(entityClass.getName())+"Mapper.findCount",params);
		return temp;
	}
	//处理类名
	public String getClassName(String str) {
		String[] strSet = str.split("\\.");
		return strSet[strSet.length-1];
	}
	//分页查询
	@Override
	public <T extends AbstractEntity> PageResults findByParamsPage(
			Class<T> entityClass, Map<String, Object> params,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
     
    
}