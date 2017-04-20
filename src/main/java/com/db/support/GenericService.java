package com.db.support;

import java.io.Serializable;
import java.util.List;
/**
 * @author yangyang.zhang
 * @version 2.0
 * @created Oct 29, 2012 10:01:57 AM
 */
public interface GenericService {
	
	/**
	 * @desc    根据ID删除对象
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param cls
	 * @param id
	 * @throws DbException
	 */
	public <T> void deleteObjectById(Class<T> cls,Serializable id) throws DbException;
	
	/**
	 * @desc    保存对象
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param object
	 * @return
	 * @throws DbException
	 */
	public <T> T saveObject(T object) throws DbException;
	public <T> T saveObject(T object,boolean fetch) throws DbException;
	/**
	 * @desc    更新对象
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param object
	 * @return
	 * @throws DbException
	 */
	public <T> T updateObject(T object) throws DbException;
	
	/**
	 * @desc    根据ID查询对象
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param cls
	 * @param id
	 * @return
	 * @throws DbException
	 */
	public <T> T findObjectById(Class<T> cls, Serializable id) throws DbException;
	
	/**
	 * @desc    根据序列名称获取
	 * @version 2.0
	 * @param sequenceName
	 * @return
	 * @throws DbException
	 */
	public String findSequence(String sequenceName)throws DbException;
	
	/**
	 * @desc    批量保存实体
	 * @version 2.0
	 * @param <T>
	 * @param objectList
	 * @throws DbException
	 */
	public <T> void saveBatch(List<T> objectList)	throws DbException;
	
	/**
	 * @desc    批量更新实体
	 * @author  shuhui.wen
	 * @version 2.0
	 * @param objectList
	 * @throws DbException
	 */
	public <T> void updateBatch(List<T> objectList) throws DbException;
}
