package com.koabs.plugin.mybatis;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.provider.base.BaseDeleteProvider;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;
import tk.mybatis.mapper.provider.base.BaseUpdateProvider;

public class DaoSqlSprovider extends MapperTemplate{

	public DaoSqlSprovider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
		this.deleteProvider = new BaseDeleteProvider(mapperClass, mapperHelper);
		this.updateProvider = new BaseUpdateProvider(mapperClass, mapperHelper);
		this.insertProvider = new BaseInsertProvider(mapperClass, mapperHelper);
		this.selectProvider = new BaseSelectProvider(mapperClass, mapperHelper);
	}

	private BaseDeleteProvider deleteProvider = null;
	private BaseUpdateProvider updateProvider = null;
	private BaseInsertProvider insertProvider = null;
	private BaseSelectProvider selectProvider = null;
			
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public void findOne(MappedStatement ms) {		
		selectProvider.selectByPrimaryKey(ms);
	}
	
	/**
	 * 返回单表的所有记录
	 * @return
	 */
	public String findAll(MappedStatement ms) {	
		return selectProvider.selectAll(ms);
	}
	
	/**
	 * 根据实体类中得属性进行查询
	 * @param record
	 * @return
	 */
	public SqlNode findByRecord(MappedStatement ms) {
		return selectProvider.select(ms);
	}
	
	/**
	 * 根据主键进行更新 不会更新null值.
	 * @param record
	 */
	public SqlNode update(MappedStatement ms) {
		return updateProvider.updateByPrimaryKeySelective(ms);
	}
	
	/**
	 * 根据主键进行更新 会更新null值.
	 * @param record
	 */
	public SqlNode updateN(MappedStatement ms) {
		return updateProvider.updateByPrimaryKey(ms);
	}
	
	/**
	 * 插入
	 * @param record
	 */
	public SqlNode insert(MappedStatement ms) {
		return insertProvider.insert(ms);
	}
	
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	public void deleteById(MappedStatement ms) {
		 deleteProvider.deleteByPrimaryKey(ms);
	}
	
	/**
	 * 根据ID 数组删除多条记录
	 * @param ids
	 */
	public String deleteIds(MappedStatement ms) {
		return null;
	}
}
