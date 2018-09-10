package com.meijie.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;

public interface IBaseDao<T> {

	    //保存对象
		public boolean save(T t);
		//删除对象
		public void delete(T t);
	    //批量删除
		public Integer deleteBatch(String ids,String obj);
		//更新对象
		public void update(T t);
		//查询对象
		public List<T> find(String hql);
		//查询对象
		public List<T> find(String hql,Object[] param);
		//查询集合
		public List<T> find(String hql,List<Object> param);
		
		//查询集合（带分页）
		public Pagination find(String hql,Object[] param,Integer page,Integer rows)throws Exception;
		//查询集合（带分页）
		public Pagination find(String hql,List<Object> param,Integer page,Integer rows)throws Exception;
		//根据标识获取对象
		public T get(Class<T> t ,Integer id);
		//根据条件获取对象
		public T get(String hql,Object[] param);
		//根据条件获取对象
		public T get(String hql,List<Object> param);
		
		//获得SessionFactory
		public SessionFactory getSessionFactory();
		public Session getSession();
}
