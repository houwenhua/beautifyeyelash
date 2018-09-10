package com.meijie.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.meijie.entity.Pagination;
import com.meijie.interfaces.IBaseDao;
import com.meijie.utils.DataBasesException;
import com.meijie.utils.PageHibernateCallback;

public class BaseDaoImpl<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private HibernateTemplate ht;
	public void setHt(HibernateTemplate ht) {
		if(ht == null){
			ht = new HibernateTemplate();
		}
		this.ht = ht;
	}
	public HibernateTemplate getHt() {
		return ht;
	}

	@Override
	public boolean save(T t) {
		Serializable serializable = ht.save(t);
		int result = (Integer)serializable;
		if(result > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public void delete(T t) {
		ht.delete(t);
	}

	@Override
	public Integer deleteBatch(String ids, String obj) {
		String hql = "delete from "+obj+" where id in ("+ids+")";
		return ht.bulkUpdate(hql);
	}

	@Override
	public void update(T t) {
		ht.update(t);
	}

	@Override
	public List<T> find(String hql) {
		List<T> t = (List<T>) ht.find(hql);
		return t;
	}

	@Override
	public List<T> find(String hql, Object[] param) {
		List<T> t = (List<T>) ht.find(hql, param);
		return t;
	}

	@Override
	public List<T> find(String hql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination find(String hql, Object[] param, Integer page, Integer rows) throws Exception {
		if(page==null || page<1){
			page = 1;
		}
		if(rows==null || rows<1){
			rows = 10;
		}
		int indexFrom = hql.indexOf("from");
		if(indexFrom==-1){
			throw new Exception("无效语句，没有包含from");
		}
		String countHql = "select count(*) "+hql.substring(indexFrom);
		long total = (long) ht.find(countHql, param).iterator().next();
		if(total < 1L){
			return new Pagination(0L,0,Collections.EMPTY_LIST);
		}
		List list = (List) ht.execute(new PageHibernateCallback(hql,param,page,rows));
		return new Pagination(total,page,list);
	}

	@Override
	public Pagination find(String hql, List<Object> param, Integer page, Integer rows) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Class<T> t, Integer id) {
		T c = ht.get(t, id);
		return c;
	}

	@Override
	public T get(String hql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(String hql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public Session getSession(){
		return sessionFactory.openSession();
	}

}
