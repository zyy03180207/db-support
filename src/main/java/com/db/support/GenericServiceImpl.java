package com.db.support;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangyang.zhang
 * @version 2.0
 * @created Oct 29, 2012 10:01:57 AM
 */

public class GenericServiceImpl implements GenericService {
	
	private static final Logger genericLogger = LoggerFactory.getLogger(GenericServiceImpl.class);
	
	@Autowired(required = true)
	protected GenericDao genericDao;
	
	@Autowired(required = true)
	protected QueryDao queryDao;

	public <T> T findObjectById(Class<T> cls, Serializable id)
			throws DbException {
		return genericDao.findById(cls, id);
	}

	@Transactional(rollbackFor=Exception.class)
	public <T> void deleteObjectById(Class<T> cls, Serializable id)
			throws DbException {
		genericDao.delete(cls, id);
	}

	@Transactional(rollbackFor=Exception.class)
	public <T> T saveObject(T object) throws DbException {
		T a =genericDao.save(object);
		return a;

	}

	@Transactional(rollbackFor=Exception.class)
	public <T> T saveObject(T object, boolean fetch) throws DbException {
		return genericDao.save(object, fetch);
	}

	@Transactional(rollbackFor=Exception.class)
	public <T> T updateObject(T object) throws DbException {
		return genericDao.update(object);
	}

	public String findSequence(String sequenceName) throws DbException {
		List<Object> list = genericDao.findBySql("select nextval for "
				+ sequenceName + " from sysibm.sysdummy1", null);
		if (list == null)
			throw new DbException("获取序列出错");
		return String.valueOf(list.get(0));
	}

	/**
	 * @desc 查询分页记录数
	 * @author shuhui.wen
	 * @version 2.0
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	@SuppressWarnings("unchecked")
	protected Object findPageCountByHql(String hql, Object[] params)
			throws DbException {
		List<Object> list = genericDao.findByHql(hql, params);
		if (list == null || list.size() == 0)
			return 0;
		return list.get(0);
	}
	
	/**
	 * 查询分页记录数，查询库
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	@SuppressWarnings("unchecked")
	protected Object queryPageCountByHql(String hql, Object[] params)
			throws DbException {
		List<Object> list = queryDao.findByHql(hql, params);
		if (list == null || list.size() == 0)
			return 0;
		return list.get(0);
	}

	/**
	 * @desc 查询分页记录数
	 * @author shuhui.wen
	 * @version 2.0
	 * @param sql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	@SuppressWarnings("unchecked")
	protected Object findPageCountBySql(String sql, Object[] params)
			throws DbException {
		List<Object> list = genericDao.findBySql(sql, params);
		if (list == null || list.size() == 0)
			return 0;
		return list.get(0);
	}
	
	
	/**
	 * 查询分页记录数,查询库
	 * @param sql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	@SuppressWarnings("unchecked")
	protected Object queryPageCountBySql(String sql, Object[] params)
			throws DbException {
		List<Object> list = queryDao.findBySql(sql, params);
		if (list == null || list.size() == 0)
			return 0;
		return list.get(0);
	}
	/**
	 * 在结果级中查询唯一对象,当结果集大于1时将会抛出异常：DbException(size is greater than 1)
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	protected Object findOnlyByHql(String hql,Object ... params) throws DbException{
		List<Object> list = genericDao.findByHql(hql, params);
		if(list==null || list.size()==0 ) return null;
		if(list.size()>1) {
			String hsqStr = hql.replaceAll("?", "%s");
			String hsqMsg = String.format("result size is greater than 1 : HQL="+hsqStr, params);
			genericLogger.error(hsqMsg);
			throw new DbException(hsqMsg);
		}
	    return list.get(0);
	}
	
	/**
	 * 在结果集中取得自然顺序第一个
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	protected Object findFirstByHql(String hql,Object ... params) throws DbException{
		List<Object> list = genericDao.findByHql(hql, params);
		if(list==null || list.size()==0 ) return null;
	    return list.get(0);
	}

	@Transactional(rollbackFor=Exception.class)
	public <T> void saveBatch(List<T> objectList) throws DbException {
		genericDao.saveBatch(objectList);
	}

	@Transactional(rollbackFor=Exception.class)
	public <T> void updateBatch(List<T> objectList) throws DbException {
		genericDao.updateBatch(objectList);
	}

	public void setGenericDao(GenericDao dao) {
		this.genericDao = dao;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

}
