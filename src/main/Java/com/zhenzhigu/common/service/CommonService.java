package com.zhenzhigu.common.service;


import java.io.Serializable;
import java.util.List;

import com.zhenzhigu.common.persistence.Paging;

/**
 * 通用Service，可以对所有实体类进行操作
 * @author 
 *
 */
public interface CommonService {
	
	void save(Object... objs) throws Exception;
	void save(List<?> objs) throws Exception;
	void update(Object... obj) throws Exception;
	void delete(Object... obj) throws Exception;
	void delete(Class<?> c, Serializable... ids) throws Exception;
	<T> T findById(Class<T> c, Serializable id) throws Exception;
	<T> Paging<T> find(Class<T> c, int page, int rows) throws Exception;
	<T> Paging<T> find(String hql, int page, int rows, Object... params) throws Exception;
	<T> Paging<T> findByProperty(Class<T> c, String property, Object value, int page, int rows)throws Exception;
	<T> List<T> findByProperty(Class<T> c, String property, Object value)throws Exception;
	<T> T findOne(String hql, Object... params) throws Exception;
	<T> T findOneByProperty(Class<T> c, String property, Object value)throws Exception;
	<T> List<T> findAll(Class<T> c) throws Exception;
	
}
