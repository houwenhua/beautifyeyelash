package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.ShopManager;
import com.meijie.entity.Store;

public interface IShopManagerDao extends IBaseDao<ShopManager> {

	Store findStore(String hql);

	List<Store> findStoreAll(String hql);

}
