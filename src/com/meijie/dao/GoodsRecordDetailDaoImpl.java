package com.meijie.dao;

import org.springframework.stereotype.Component;

import com.meijie.entity.Consume;
import com.meijie.entity.GoodsRecordDetail;
import com.meijie.interfaces.IGoodsRecordDao;
import com.meijie.interfaces.IGoodsRecordDetailDao;

@Component
public class GoodsRecordDetailDaoImpl extends BaseDaoImpl<GoodsRecordDetail> implements IGoodsRecordDetailDao{

	@Override
	public void deleteBatchBygoodsrecordid(String ids, String obj) {
		String hql = "delete from "+obj+" where goodsRecordid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}
}
