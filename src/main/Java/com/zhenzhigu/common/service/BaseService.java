package com.zhenzhigu.common.service;


import java.io.Serializable;
import java.util.List;

import com.zhenzhigu.common.persistence.Paging;

/**
 * 用于给其他Service接口去继承，不要单独使用
 */
public interface BaseService<T> {
	
	void save(T... objs) throws Exception;
	void save(List<T> objs) throws Exception;
	void update(T... objs) throws Exception;
	void delete(T... objs) throws Exception;
	void delete(Serializable id) throws Exception;
	void delete(Serializable... ids) throws Exception;
	T findById(Serializable id) throws Exception;
	Paging<T> find(int page, int rows) throws Exception;
	Paging<T> find(String hql, int page, int rows, Object... params) throws Exception;
	Paging<T> findByProperty(String property, Object value, int page, int rows)throws Exception;
	List<T> findByProperty(String property, Object value)throws Exception;
	T findOne(String hql, Object... params) throws Exception;
	T findOneByProperty(String property, Object value)throws Exception;
	List<T> findAll() throws Exception;
	
}
