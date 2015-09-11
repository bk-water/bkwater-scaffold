package com.koabs.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.koabs.entity.BaseEntity;
import com.koabs.plugin.mybatis.DaoSqlSprovider;

public interface IBaseDao<T extends BaseEntity, ID extends Serializable> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public T findOne(ID id);
	
	/**
	 * 返回单表的所有记录
	 * @return
	 */
	@SelectProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public List<T> findAll();
	
	/**
	 * 根据实体类中得属性进行查询
	 * @param record
	 * @return
	 */
	@SelectProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public List<T> findByRecord(T record);
	
	/**
	 *  根据主键进行更新 不会更新null值.
	 * @param record
	 */
	@UpdateProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public T update(T record);
	
	/**
	 * 根据主键进行更新 会更新null值.
	 * @param record
	 */
	@UpdateProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public T updateN(T record);
	
	/**
	 * 插入
	 * @param record
	 */
	@InsertProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public T insert(T record);
	
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	@DeleteProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public void deleteById(ID id);
	
	/**
	 * 根据ID 数组删除多条记录
	 * @param ids
	 */
	@DeleteProvider(type = DaoSqlSprovider.class, method = "dynamicSQL")
	public void deleteIds(ID[] ids);
}
