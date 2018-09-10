package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.ISalaryDetailService;
import com.meijie.vo.SalaryDetailVo;
import com.meijie.vo.SalaryVo;

@Service
public class SalaryDetailServiceImpl implements ISalaryDetailService {

	@Autowired
	private ISalaryDetailDao salaryDeatailDao;

	@Override
	public Pagination getPaginationGoods(SalaryDetailVo sdv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(sdv != null && StringUtils.isNotBlank(sdv.getWaitername())) {
			hql = "from SalaryDetail sd,Salary s,Waiter w where sd.salary.id = s.id and s.waiter.id = w.id and w.name like ? order by sd.date desc";
			param = new String[]{"%"+sdv.getWaitername()+"%"};
		} else {
			hql = "from SalaryDetail order by date desc";
			param = new String[]{};
		}
		Pagination pagination = salaryDeatailDao.find(hql, param, page, rows);
		if(sdv != null && StringUtils.isNotBlank(sdv.getWaitername())) {
			List<Object[]> list = pagination.getRows();
			List svList = new ArrayList();
			SalaryDetailVo svo = null;
			for(Object[] obj : list) {
				SalaryDetail s = (SalaryDetail) obj[0];
				svo = new SalaryDetailVo(s.getId(), s.getSalary().getWaiter().getName(), s.getDate(), s.getAllmoney(), s.getRate(),s.getReward());
				svList.add(svo);
			}
			pagination.setRows(svList);
			return pagination;
		}
		
		
		List<SalaryDetail> list = pagination.getRows();
		
		List svList = new ArrayList();
		SalaryDetailVo svo = null;
		for(SalaryDetail s : list) {
			svo = new SalaryDetailVo(s.getId(), s.getSalary().getWaiter().getName(), s.getDate(), s.getAllmoney(), s.getRate(),s.getReward());
			svList.add(svo);
		}
		pagination.setRows(svList);
		return pagination;
	}
}
