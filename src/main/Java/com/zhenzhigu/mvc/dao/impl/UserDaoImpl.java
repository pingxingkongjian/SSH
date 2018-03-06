package com.zhenzhigu.mvc.dao.impl;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhenzhigu.mvc.dao.UserDao;
import com.zhenzhigu.mvc.entity.User;


public class UserDaoImpl implements UserDao {
	
	@Autowired SessionFactory factory;

	@Override
	public void save(User... users) throws Exception {
		for(User user:users) {
			factory.getCurrentSession().save(user);
		}
	}

	@Override
	public void update(User... users) throws Exception {
		for(User user:users) {
			factory.getCurrentSession().update(user);
		}
	}

	@Override
	public void delete(User... users) throws Exception {
		for(User user:users) {
			factory.getCurrentSession().delete(user);
		}
	}

	@Override
	public User findById(String id) throws Exception {
		return factory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User findOne(String hql,Object... objs) throws Exception {
		Query<User> query = factory.getCurrentSession().createQuery(hql);
		for(int i=0;objs!=null&&i<objs.length;i++) {
			query.setParameter(i, objs[i]);
		}
		return query.uniqueResult();
	}

}
