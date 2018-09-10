package com.meijie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.GoodsRecord;
import com.meijie.entity.Pagination;
import com.meijie.entity.Visitor;
import com.meijie.interfaces.IGoodsRecordDao;

public class GoodsRecordDaoImpl extends BaseDaoImpl<GoodsRecord> implements IGoodsRecordDao {

	@Override
	public void deleteBatchByWaiterid(String ids, String obj) {
		String hql = "delete from "+obj+" where waiterid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}
	
	@Override
	public List<GoodsRecord> findAllGoodsRecordByWaiterId(int index) {
		String hql = "from GoodsRecord where waiterid = " + index;
		List<GoodsRecord> list = (List<GoodsRecord>) super.getHt().find(hql);
		return list;
	}

}
