package com.meijie.service;

import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.Store;
import com.meijie.interfaces.IStoreDao;
import com.meijie.interfaces.IStoreService;

public class StoreServiceImpl implements IStoreService {

	private IStoreDao storeDao;
	public IStoreDao getStoreDao() {
		return storeDao;
	}
	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}
	@Override
	public Pagination getPaginationService(Store store, int page, int rows) throws Exception {
		String hql = "from Store";
		String[] param = null;
		//设置参数
		if(store!=null){
			hql = "from Store where name like ? and address like ?";
			param = new String[]{"%"+store.getName()+"%","%"+store.getAddress()+"%"}; 
		}else{
			hql = "from Store";
			param = new String[]{};
		}
		Pagination pagination = storeDao.find(hql, param, page, rows);
		return pagination;
	}
	@Override
	public void addStore(Store store) {
		storeDao.save(store);
	}
	@Override
	public void deleteStores(String ids) {
		storeDao.deleteBatch(ids, "Store");
	}
	@Override
	public Store queryObjectById(int id) {
		Store store = storeDao.get(Store.class, id);
		return store;
	}
	@Override
	public void updateStore(Store store) {
		Store temp = storeDao.get(Store.class, store.getId());
		temp.setName(store.getName());
		temp.setAddress(store.getAddress());
		temp.setPhone(store.getPhone());
		storeDao.update(temp);
	}
}
