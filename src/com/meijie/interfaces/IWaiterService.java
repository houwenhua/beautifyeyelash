package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.entity.Waiter;

public interface IWaiterService {

	Pagination getPaginationWaiter(Waiter waiter, int page, int rows) throws Exception;

	Store getStore(int storeid);

	List<Store> getStoreAll();

	void addWaiter(Waiter waiter);

	void deleteWaiters(String ids);

	Waiter queryObjectById(int id);

	void updateWaiter(Waiter waiter);

	List<Waiter> findAll();

}
