package com.meijie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;
import com.meijie.entity.SalaryDetail;
import com.meijie.interfaces.ISalaryDetailDao;

public class SalaryDetailDaoImpl extends BaseDaoImpl<SalaryDetail> implements ISalaryDetailDao {

	@Override
	public void deleteByObjectId(String ids, String obj,String objid) {
		String hql = "delete from "+obj+" where "+objid+" in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public void deleteBySalaryId(String ids, String obj) {
		String hql = "delete from "+obj+" where salaryid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}



}
