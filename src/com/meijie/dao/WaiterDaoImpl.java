package com.meijie.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IWaiterDao;

public class WaiterDaoImpl extends BaseDaoImpl<Waiter> implements IWaiterDao {

	@Override
	public Store findStore(String hql) {
		Store store = (Store) super.getHt().find(hql).get(0);
		if(store!=null){
			return store;
		}else{
			return null;
		}
	}

	@Override
	public List<Store> findStoreAll(String hql) {
		List<Store> list = (List<Store>) super.getHt().find(hql);
		return list;
	}

}
