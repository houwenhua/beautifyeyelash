package com.meijie.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;
import com.meijie.entity.ShopManager;
import com.meijie.entity.Store;
import com.meijie.interfaces.IShopManagerDao;

public class ShopManagerDaoImpl extends BaseDaoImpl<ShopManager> implements IShopManagerDao {

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
