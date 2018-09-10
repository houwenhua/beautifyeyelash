package com.meijie.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.utils.DataBasesException;
import com.meijie.utils.PageHibernateCallback;

public class ConsumeDaoImpl extends BaseDaoImpl<Consume> implements IConsumeDao{

	@Override
	public Pagination find(String hql, Object[] param, Integer page, Integer rows) throws Exception {
		if(page==null || page<1){
			page = 1;
		}
		if(rows==null || rows<1){
			rows = 10;
		}
		int indexFrom = hql.indexOf("from");
		if(indexFrom==-1){
			throw new Exception("无效语句，没有包含from");
		}
		String countHql = "select count(*) "+hql.substring(indexFrom);
		long total = (long) super.getHt().find(countHql, param).iterator().next();
		if(total < 1L){
			return new Pagination(0L,0,Collections.EMPTY_LIST);
		}
		
		if(param != null && param.length > 0) {
			List<Object[]> list = (List) super.getHt().execute(new PageHibernateCallback(hql,param,page,rows));
			List<Consume> cList = new ArrayList<>();
			for (Object[] objects : list) {
				Consume con = (Consume) objects[0];
				cList.add(con);
			}
			return new Pagination(total,page,cList);
		} else {
			List list = (List) super.getHt().execute(new PageHibernateCallback(hql,param,page,rows));
			return new Pagination(total,page,list);
		}
	}
	
	public void updateConsume(Consume t) throws DataBasesException {
		try {
			super.getHt().update(t);
		} catch(Exception e) {
			throw new DataBasesException("更新失败");
		} 
		
	}

	@Override
	public void deleteBatchByVipid(String ids, String obj) {
		String hql = "delete from "+obj+" where vipid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public void deleteBatchByServiceid(String ids, String obj) {
		String hql = "delete from "+obj+" where serviceid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public void deleteBatchByWaiterid(String ids, String obj) {
		String hql = "delete from "+obj+" where waiterid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public List<Consume> findAllConsumeByWaiterId(int index) {
		String hql = "from Consume where waiterid = " + index;
		List<Consume> list = (List<Consume>) super.getHt().find(hql);
		return list;
	}
}
