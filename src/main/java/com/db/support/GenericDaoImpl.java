package com.db.support;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * @author yangyang.zhang
 * @version 2.0
 * @created Oct 29, 2012 10:01:57 AM
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes" })
public class GenericDaoImpl implements GenericDao {
	
	@Autowired(required=true)
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	/**
	 * @description 返回当前SESSION
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	/**
	 * @description 删除一个实体
	 */
	public void delete(Object object) throws DbException {
		try {
			getCurrentSession().delete(object);
			getCurrentSession().flush();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.delete(Object object)出错",
					e);
		}
	}

	/**
	 * @description 根据ID删除一个实体
	 */

	public <T> void delete(Class<T> cls, Serializable id) throws DbException {
		try {
			Object obj =getCurrentSession().get(cls, id);
			if (obj != null) {
				getCurrentSession().delete(obj);
				getCurrentSession().flush();
			}
		} catch (Exception e) {
			throw new DbException(
					"[DAO层]GenericDao.delete(Class<T> cls, Serializable id)出错", e);
		}
	}

	/**
	 * @description 根据HQL更新
	 */
	public void updateByHql(String hql, Object[] params)
			throws DbException {
		try {
			Query query = getCurrentSession().createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.updateByHql(String hql, Object[] params)出错", e);
		}
	}
	/**
	 * @description 根据nameQuery查询
	 */
	public List findBySql(String sql, Object[] params)
			throws DbException {
		try {
			Query query = getCurrentSession().createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findBySql()出错", e);
		}
	}
	
	/**
	 * @description 根据nameQuery查询
	 */
	public List findBySql(String sql,Class entityType, Object[] params)
			throws DbException {
		try {
			Query query = getCurrentSession().createSQLQuery(sql).addEntity(entityType);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findBySql()出错", e);
		}
	}
	
	/**
	 * @description 根据nameQuery查询
	 */
	public List findBySqlListMap(String sql,Object[] params) throws DbException {
		try {
			Query query = getCurrentSession().createSQLQuery(sql);
			//设定结果结果集中的每个对象为Map类型   
			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findBySql()出错", e);
		}
	}

	/**
	 * @description 根据hql查询
	 */
	public  List findByHql(String hql, Object[] params)
			throws DbException {
		try {
			Query query = getCurrentSession().createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findByHql()出错", e);
		}
	}
	
	/**
	 * @description 根据hql查询(分页)
	 */
	public  List findByPage(String hql, Object[] params, int pageIndex, int pageSize)
			throws DbException {
		try {
			Query query = getCurrentSession().createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findByPage()出错", e);
		}
	}
	/**
	 * @desc    查询分页记录数
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param sql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	public  List findByPageForSql(String sql, Object[] params, int pageIndex, int pageSize)
			throws DbException {
		try {
			Query query = getCurrentSession().createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findByPage()出错", e);
		}
	}
	/**
	 * @description 保存实体
	 */
	public <T> T save(T entity) throws DbException {
		return save(entity,true);
	}
	public <T> T save(T entity,boolean fetch) throws DbException {
		try {
			Serializable id = getCurrentSession().save(entity);
			if(fetch)return (T)findById(entity.getClass(), id);
			return null;
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.save(T object)出错", e);
		}
	}
	
	
	@Override
	public <T> void saveOrUpdate(T entity) throws DbException {
		try {
			 getCurrentSession().saveOrUpdate(entity);
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.save(T object)出错", e);
		}
	}


	/**
	 * @description 批量保存实体
	 */
	public <T> void saveBatch(List<T> objectList)
			throws DbException {
		try {

			int i = 0;
			for (Object entity : objectList) {
				i++;
				getCurrentSession().save(entity);
				if (i % 100 == 0) {
					getCurrentSession().flush();
					getCurrentSession().clear();
				}
			}
			getCurrentSession().flush();
			getCurrentSession().clear();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.saveBatch()出错", e);
		}
	}

	/**
	 * @description 更新实体
	 */
	public <T> T update(T entity) throws DbException {
		try {
			getCurrentSession().update(entity);
			getCurrentSession().flush();
			return entity;
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.update(T entity)出错", e);
		}
	}

	/**
	 * @desc    批量更新实体
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param objectList
	 * @throws DbException
	 */
	public <T> void updateBatch(List<T> objectList)
			throws DbException {
		try {
			int i = 0;
			for (Object entity : objectList) {
				i++;
				getCurrentSession().update(entity);
				if (i % 100 == 0) {
					getCurrentSession().flush();
					getCurrentSession().clear();
				}
			}
			getCurrentSession().flush();
			getCurrentSession().clear();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.updateBatch()出错", e);
		}
	}
	/**
	 * @description 根据nameQuery查询
	 */
	public void updateBySql(String sql, Object[] params)
			throws DbException {
		try {

			Query query = getCurrentSession().createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.updateBySql()出错", e);
		}
	}
	/**
	 * @description 根据class和id查询
	 */
	public <T> T findById(Class<T> cls, Serializable id) throws DbException {
		try {
			return (T)getCurrentSession().get(cls, id);
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findById(Class<T> cls, Serializable id)出错", e);
		}
	}
	/**
	 * @desc    查询分页记录数
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	protected Object findPageCountByHql(String hql, Object[] params) throws DbException{
		List<Object> list = findByHql(hql, params);
		if(list == null || list.size()==0)return 0;
		return list.get(0);
	}
	/**
	 * @desc    查询分页记录数
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param hql
	 * @param params
	 * @return
	 * @throws DbException
	 */
	protected Object findPageCountBySql(String sql, Object[] params) throws DbException{
		List<Object> list = findBySql(sql, params);
		if(list == null || list.size()==0)return 0;
		return list.get(0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public  List findByPageForSqlToMap(String sql, Object[] params, int pageIndex, int pageSize)
			throws DbException {
		try {
			Query query = getCurrentSession().createSQLQuery(sql);
			//设定结果结果集中的每个对象为Map类型   
			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			if (params != null) {
				for (int i = 0; i < params.length; i++)
					query.setParameter(i, params[i]);
			}
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e) {
			throw new DbException("[DAO层]GenericDao.findByPageForSqlToMap()出错", e);
		}
	}
}
