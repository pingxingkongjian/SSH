package com.zhenzhigu.mvc.dao;

import com.zhenzhigu.mvc.entity.User;
/**
 * 自从有了BaseDAO，这个接口就没用了
 * @author Master.Xia
 *
 */
public interface UserDao {
	void save(User... users) throws Exception;
	void update(User... users) throws Exception;
	void delete(User... users)throws Exception;
	User findById(String id) throws Exception;
	User findOne(String hql, Object... objs)throws Exception;
}
