package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Store;
import com.meijie.entity.Waiter;

public interface IWaiterDao extends IBaseDao<Waiter> {

	Store findStore(String hql);

	List<Store> findStoreAll(String hql);

}
