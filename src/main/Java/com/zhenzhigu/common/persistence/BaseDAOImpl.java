package com.zhenzhigu.common.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 
 * 基础实现类 + 分页功能
 * 
 * @author 
 * @version 1.0 create date 2013-10-5
 * @version 2.0 create date 2016-03-16 
 * @version 3.0 create date 2017-04-05
 * @version 4.0 create 2017年4月20日22:08:56
 */
@Repository //创建一个bean放入Spring容器
@SuppressWarnings("unchecked")
public class BaseDAOImpl implements BaseDAO {
	
	@Autowired //Spring根据类型自动注入，无需get、set，可以private
	private SessionFactory factory;

	/**
	 * 分页查询的核心实现
	 * @param hql hql语句
	 * @param page 当前页
	 * @param rows 长度
	 * @param params hql中的问号参数
	 * @return PagingSupport
	 */
	public <T> Paging<T> queryForPage(final String hql, final int page,final int rows,final Object... params) {
		Session session = factory.getCurrentSession();
		return HibernateHelper.find(session,hql, page, rows, params);
	}

	
	public void save(Object... objs) throws Exception {
		for(Object obj:objs) {
			factory.getCurrentSession().save(obj);
		}
	}
	
	public void delete(Object... objs) throws Exception {
		for(Object obj:objs) {
			factory.getCurrentSession().delete(obj);
		}
	}
	
	public void update(Object... objs) throws Exception {
		for(Object obj:objs) {
			factory.getCurrentSession().update(obj);
		}
	}

	
	public <T> Paging<T> find(String hql, int page, int rows)throws Exception {
		return queryForPage(hql, page, rows);
	}
	
	
	public Paging<?> find(String hql, int page, int rows,Object... params) throws Exception {
		return queryForPage(hql, page, rows,params);
	}
	
	
	public <T> Paging<T>  find(Class<T> c,int page, int rows)throws Exception {
		return queryForPage("from " + c.getName(), page, rows);
	}
	
	
	public <T> Paging<T> findByProperty(Class<T> c,String property,Object value, int page, int rows)throws Exception {
		Session session = factory.getCurrentSession();
		return HibernateHelper.findByProperty(session,c, property, value, page, rows);
	}

	
	public <T> T findById(Class<T> c,Serializable id) throws Exception {
		return factory.getCurrentSession().get(c, id);
	}

	
	public <T> T findOne(String hql, Object... params) throws Exception {
		Session session = factory.getCurrentSession();
		return HibernateHelper.findOne(session,hql, params);
	}

	
	public <T> T findOneByProperty(Class<T> c, String property, Object value)throws Exception {
		Session session = factory.getCurrentSession();
		return HibernateHelper.findOneByProperty(session,c, property, value);
	}
	
	public <T> List<T> findAll(Class<T> c) throws Exception {
		return factory.getCurrentSession().createQuery("from "+c.getName()).list();
	}
	
}
