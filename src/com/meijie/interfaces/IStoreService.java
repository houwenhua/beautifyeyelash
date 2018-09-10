package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.Store;

public interface IStoreService {

	Pagination getPaginationService(Store store, int page, int rows) throws Exception;

	void addStore(Store store);

	void deleteStores(String ids);

	Store queryObjectById(int id);

	void updateStore(Store store);

}
