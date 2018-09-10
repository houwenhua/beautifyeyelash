package com.meijie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.interfaces.ISalaryDao;

public class SalaryDaoImpl extends BaseDaoImpl<Salary> implements ISalaryDao {

	@Override
	public Salary findSalaryByWaiterId(int waiterid) {
		Salary salary = null;
		String hql = "from Salary where waiterid = "+waiterid;
		List<Salary> list = (List<Salary>) super.getHt().find(hql);
		if(list.size()>0) {
			salary = list.get(0);
		}
		return salary;
	}

	@Override
	public void deleteBatchByWaiterId(String ids, String obj) {
		String hql = "delete from "+obj+" where waiterid in ("+ids+")";
		super.getHt().bulkUpdate(hql);
	}

	
}
