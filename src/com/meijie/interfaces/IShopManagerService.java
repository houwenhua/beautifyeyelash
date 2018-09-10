package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.ShopManager;
import com.meijie.entity.Store;

public interface IShopManagerService {

	Pagination getPaginationShopManager(ShopManager sm, int page, int rows) throws Exception;

	Store getStore(int storeid);

	List<Store> getStoreAll();

	void addShopManager(ShopManager sm);

	void deleteShopManagers(String ids);

	ShopManager queryObjectById(int id);

	void updateShopManager(ShopManager sm);

}
