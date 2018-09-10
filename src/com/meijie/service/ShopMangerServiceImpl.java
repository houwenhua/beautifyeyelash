package com.meijie.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.ShopManager;
import com.meijie.entity.Store;
import com.meijie.interfaces.IShopManagerDao;
import com.meijie.interfaces.IShopManagerService;

public class ShopMangerServiceImpl implements IShopManagerService {

	private IShopManagerDao shopMangerDao;
	public IShopManagerDao getShopMangerDao() {
		return shopMangerDao;
	}
	public void setShopMangerDao(IShopManagerDao shopMangerDao) {
		this.shopMangerDao = shopMangerDao;
	}
	@Override
	public Pagination getPaginationShopManager(ShopManager sm, int page, int rows) throws Exception {
		String hql = "from ShopManager";
		String[] param = null;
		//设置参数
		if(sm!=null){
			hql = "from ShopManager where name like ?";
			param = new String[]{"%"+sm.getName()+"%"}; 
		}else{
			hql = "from ShopManager";
			param = new String[]{};
		}
		Pagination pagination = shopMangerDao.find(hql, param, page, rows);
		return pagination;
	}
	@Override
	public Store getStore(int storeid) {
		String hql = "from Store where id="+storeid;
		Store store = shopMangerDao.findStore(hql);
		return store;
	}
	@Override
	public List<Store> getStoreAll() {
		String hql = "from Store";
		List<Store> list = shopMangerDao.findStoreAll(hql);
		return list;
	}
	@Override
	public void addShopManager(ShopManager sm) {
		shopMangerDao.save(sm);
	}
	@Override
	public void deleteShopManagers(String ids) {
		shopMangerDao.deleteBatch(ids, "ShopManager");
	}
	@Override
	public ShopManager queryObjectById(int id) {
		ShopManager sm = shopMangerDao.get(ShopManager.class, id);
		return sm;
	}
	@Override
	public void updateShopManager(ShopManager sm) {
		ShopManager temp = shopMangerDao.get(ShopManager.class, sm.getId());
		temp.setName(sm.getName());
		temp.setPhone(sm.getPhone());
		temp.setSex(sm.getSex());
		temp.setAge(sm.getAge());
		temp.setStoreid(sm.getStoreid());
		shopMangerDao.update(temp);
	}
	
}
