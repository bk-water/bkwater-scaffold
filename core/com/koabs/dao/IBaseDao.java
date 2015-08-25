package com.koabs.dao;

import java.util.List;

public interface IBaseDao<T> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T findOne(Object id);
	
	/**
	 * 返回单表的所有记录
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据实体类中得属性进行查询
	 * @param record
	 * @return
	 */
	public List<T> findByRecord(T record);
	
	/**
	 * 插入或者更新记录
	 * 主键不存在时插入
	 * @param record
	 */
	public void saveOrUpdate(T record);
	
	/**
	 * 插入或者更新记录
	 * 主键不存在时插入
	 * 不会更新null值
	 * @param record
	 */
	public void saveOrUpdateN(T record);
	
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	public void deleteById(Object id);
}
