package com.zhenzhigu.common.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhenzhigu.common.persistence.BaseDAO;
import com.zhenzhigu.common.persistence.Paging;
import com.zhenzhigu.common.service.BaseService;

/**
 * 为其他Service提供的抽象父类，继承时需要指定泛型
 * @author 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired protected SessionFactory factory;
	@Autowired protected BaseDAO dao;
	
	/**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * 
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    private static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
    	//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
           return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
        	return Object.class;
        }
        if (!(params[index] instanceof Class)) {
        	return Object.class;
        }
        return (Class<?>) params[index];
    }
    @SuppressWarnings("unchecked")
	public Class<T> getEntityClass(){
    	return (Class<T>) getSuperClassGenricType(this.getClass(), 0);
    }

	
	public void save(T... objs) throws Exception {
		dao.save(objs);
	}
	
	public void save(List<T> objs) throws Exception {
		for(T obj : objs){
			dao.save(obj);
		}
	}
	
	public void update(T... objs) throws Exception {
		dao.update(objs);
	}

	
	public void delete(T... objs) throws Exception {
		dao.delete(objs);
	}

	
	public void delete(Serializable id) throws Exception {
		dao.delete(dao.findById(getEntityClass(), id));
	}

	
	public void delete(Serializable... ids) throws Exception {
		for(int i=0;ids!=null&&i<ids.length;i++){
			dao.delete(dao.findById(getEntityClass(), ids[i]));
		}
	}

	
	public T findById(Serializable id) throws Exception {
		return dao.findById(getEntityClass(), id);
	}

	
	public Paging<T> find(int page, int rows) throws Exception {
		return dao.find(getEntityClass(), page, rows);
	}

	
	public Paging<T> find(String hql, int page, int rows, Object... params) throws Exception {
		return (Paging<T>) dao.find(hql, page, rows, params);
	}

	
	public List<T> findByProperty(String property, Object value) throws Exception {
		return dao.findByProperty(getEntityClass(), property, value, 1, Integer.MAX_VALUE).getList();
	}
	
	public Paging<T> findByProperty(String property, Object value,int page,int rows) throws Exception {
		return dao.findByProperty(getEntityClass(), property, value, page, rows);
	}
	
	public T findOne(String hql, Object... params) throws Exception {
		return (T) dao.findOne(hql, params);
	}

	
	public T findOneByProperty(String property, Object value) throws Exception {
		return dao.findOneByProperty(getEntityClass(), property, value);
	}

	
	public List<T> findAll() throws Exception {
		return dao.findAll(getEntityClass());
	}

}
