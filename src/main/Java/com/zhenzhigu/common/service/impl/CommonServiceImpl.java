package com.zhenzhigu.common.service.impl;

import java.io.Serializable;
import java.util.List;


import com.zhenzhigu.common.persistence.BaseDAO;
import com.zhenzhigu.common.persistence.Paging;
import com.zhenzhigu.common.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired protected BaseDAO dao;

	public void save(Object... objs) throws Exception {
		for(Object obj : objs){
			dao.save(obj);
		}
	}
	
	public void save(List<?> objs) throws Exception {
		for(Object obj : objs){
			dao.save(obj);
		}
	}
	
	public void update(Object... objs) throws Exception {
		for(Object obj : objs){
			dao.update(obj);
		}
	}

	
	public void delete(Object... objs) throws Exception {
		for(Object obj:objs) {
			dao.delete(obj);
		}
	}

	public void delete(Class<?> c, Serializable... ids) throws Exception {
		for(int i=0;ids!=null&&i<ids.length;i++){
			dao.delete(dao.findById(c, ids[i]));
		}
	}

	public <T> T findById(Class<T> c, Serializable id) throws Exception {
		return dao.findById(c, id);
	}

	public <T> Paging<T> find(Class<T> c, int page, int rows) throws Exception {
		return dao.find(c, page, rows);
	}

	public <T> Paging<T> find(String hql, int page, int rows, Object... params) throws Exception {
		return (Paging<T>) dao.find(hql, page, rows, params);
	}
	
	public <T> Paging<T> findByProperty(Class<T> c, String property, Object value,int page,int rows) throws Exception {
		return dao.findByProperty(c, property, value, page, rows);
	}
	
	public <T> List<T> findByProperty(Class<T> c, String property, Object value) throws Exception {
		return dao.findByProperty(c, property, value, 1, Integer.MAX_VALUE).getList();
	}

	public <T> T findOne(String hql, Object... params) throws Exception {
		return (T) dao.findOne(hql, params);
	}

	public <T> T findOneByProperty(Class<T> c, String property, Object value) throws Exception {
		return dao.findOneByProperty(c, property, value);
	}

	public <T> List<T> findAll(Class<T> c) throws Exception {
		return dao.findAll(c);
	}
	
}
