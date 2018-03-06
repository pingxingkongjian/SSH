package com.zhenzhigu.common.persistence;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.query.Query;
import org.hibernate.Session;
/**
 * Hibernate查询辅助工具，使分页查询更方便
 * @author Master.Xia v1.0 Date:2017年1月14日17:39:17
 * @author Master.Xia v2.0 Date:2017年10月18日14:55:25
 */
public class HibernateHelper {
	
	public static void main(String[] args) {
		String  str="SUN公司被Oracle收购，是否意味着java被逼上了死路？";
		str = "from User";
        String s = "\\d+.\\d+|\\w+";
        Pattern  pattern=Pattern.compile(s);  
        Matcher  ma=pattern.matcher(str);  
   
        while(ma.find()){  
            System.out.println(ma.group());  
        }  
        
        /*
        String hql = "";
        System.out.println("--- "+hql);
        Pattern  pattern=Pattern.compile("\\d+.\\d+|\\w+");  
        Matcher  ma=pattern.matcher(hql);
        
        String table = null;
        for(int i=1;ma.find();i++){
        	table = ma.group();
        	if(i==2){
        		break;
        	}
        }

*/
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Paging<T> find(Session session,final String hql, final int page,final int rows,final Object... params) {
        String countStr = "select count(1) "+hql.substring(hql.toLowerCase().indexOf("from"));
		Query query = session.createQuery(hql);
		Query countQuery = session.createQuery(countStr);
		for(int i=0;(params!=null&&i<params.length);i++){
			query.setParameter(i, params[i]);
			countQuery.setParameter(i, params[i]);
		}
        int count = ((Long) countQuery.uniqueResult()).intValue();
		int firstResult = (page - 1) * rows;	//开始位置
		query.setFirstResult(firstResult);
		query.setMaxResults(rows);
		List<T> data = query.getResultList();
		return new Paging<T>(data, page, count, rows);
	}
	
	public static <T> Paging<T> find(Session session,Class<T> c,int page, int rows){
		return find(session,"from "+c.getName(), page, rows);
	}
	
	public static <T> Paging<T> findByProperty(Session session,Class<T> c,String property,Object value,int page, int rows){
		String hql = "from "+c.getName()+" where "+property+"=?";
		return find(session,hql, page, rows, value);
	}
	
	public static <T> T findOne(Session session,String hql, Object... params){
		Paging<T> page = find(session,hql, 1, 1, params);
		return page.getFirst();
	}
	
	public static <T> T findOneByProperty(Session session,Class<T> c, String property, Object value){
		Paging<T> page = findByProperty(session,c, property, value, 1, 1);
		return page.getFirst();
	}
	
}
