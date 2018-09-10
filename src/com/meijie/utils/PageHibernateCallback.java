package com.meijie.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

	private String hql;
	private Object[] param;
	private int startIndex;
	private int pageSize;
	public PageHibernateCallback(String hql, Object[] param, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.param = param;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException {
		Query query = session.createQuery(hql);
		if(param != null && param.length > 0){
			for(int i=0;i<param.length;i++){
				query.setParameter(i, param[i]);
			}
		}
		query.setFirstResult((startIndex-1)*pageSize).setMaxResults(pageSize);
		List list = query.list();
		return list;
	}

}
