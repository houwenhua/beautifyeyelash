package com.meijie.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.interfaces.IOperatorDao;

public class OperatorDaoImpl extends BaseDaoImpl<Operator> implements IOperatorDao {

	@Override
	public Store findStore(String hql) {
		Store store = (Store) super.getHt().find(hql).get(0);
		return store;
	}

	@Override
	public List<Store> findStoreAll(String hql) {
		List<Store> list = (List<Store>) super.getHt().find(hql);
		return list;
	}


}
