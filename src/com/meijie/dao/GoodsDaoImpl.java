package com.meijie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Goods;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.IGoodsDao;

public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao {

	@Override
	public void deleteBatchByPurchaseid(String ids, String obj) {
		String hql = "delete from "+obj+" where purchaseid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	
}
