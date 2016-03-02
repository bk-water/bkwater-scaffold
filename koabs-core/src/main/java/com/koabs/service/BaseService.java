package com.koabs.service;

import java.io.Serializable;
import java.util.List;

import com.koabs.dao.IBaseDao;
import com.koabs.entity.BaseEntity;

/**
 * 
 * @author koabs
 * Service 层基础公共方法.
 */
public abstract class BaseService<T extends BaseEntity, ID extends Serializable> {
	
	protected IBaseDao<T, ID> baseDao;
	
	protected abstract void setBaseDao();
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */

	public T findOne(ID id) {
		return (T) baseDao.findOne(id);
	}
	
	/**
	 * 返回单表的所有记录
	 * @return
	 */
	public List<T> findAll() {
		return (List<T>) baseDao.findAll();
	}
	
	/**
	 * 根据实体类中得属性进行查询
	 * @param record
	 * @return
	 */
	public List<T> findByRecord(T record) {
		return (List<T>) baseDao.findByRecord(record);	
	}
	
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	public void deleteById(ID id) {
		baseDao.deleteById(id);
	}
	
	/**
	 * 根据ID 数组删除多条记录
	 * @param ids
	 */
	public void deleteIds(ID[] ids) {
		baseDao.deleteIds(ids);
	}
}
