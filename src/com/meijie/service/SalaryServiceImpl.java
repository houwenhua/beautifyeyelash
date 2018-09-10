package com.meijie.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.ISalaryService;
import com.meijie.vo.SalaryVo;

@Service
public class SalaryServiceImpl implements ISalaryService {

	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;


	@Override
	public Pagination getPaginationGoods(SalaryVo sv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(sv != null && StringUtils.isNotBlank(sv.getWatiername())) {
			hql = "from Salary s,Waiter w where s.waiter.id = w.id and w.name like ?";
			param = new String[]{"%"+sv.getWatiername()+"%"};
		
		} else {
			hql = "from Salary";
			param = new String[]{};
		}
		Pagination pagination = salaryDao.find(hql, param, page, rows);
		
		//得到所有工资详细,得到这个月的工资信息
		//得到上个月的最后一天
		Date predate = new Date();
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ca.set(Calendar.DAY_OF_MONTH, 1); 
		ca.add(Calendar.DATE, -1);
		predate = ca.getTime();
		//得到下个月的第一天
		Date nextdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.MONTH, 1);
	    nextdate = calendar.getTime();
		
		String hqls = "from SalaryDetail sd where sd.date> date('"+sdf.format(predate)+"') and sd.date < date('"+sdf.format(nextdate)+"')";
		List<SalaryDetail> sdList = salaryDeatailDao.find(hqls);
		
		if(sv != null && StringUtils.isNotBlank(sv.getWatiername())) {
			List<Object[]> list = pagination.getRows();
			List svList = new ArrayList();
			SalaryVo svo = null;
			for(Object[] obj : list) {
				Salary s = (Salary) obj[0];
				
				BigDecimal sum = new BigDecimal(0);
				for(SalaryDetail sd: sdList) {
					if(s.getId() == sd.getSalary().getId()){
						sum = sum.add(sd.getReward());
					}
				}
				
				svo = new SalaryVo(s.getId(), s.getMinsalary(), sum, s.getMinsalary().add(sum), s.getWaiter().getName());
				svList.add(svo);
			}
			pagination.setRows(svList);
			return pagination;
		}
		
		List<Salary> list = pagination.getRows();
		List svList = new ArrayList();
		SalaryVo svo = null;
		for(Salary s : list) {
			
			BigDecimal sum = new BigDecimal(0);
			for(SalaryDetail sd: sdList) {
				if(s.getId() == sd.getSalary().getId()){
					sum = sum.add(sd.getReward());
				}
			}
			
			svo = new SalaryVo(s.getId(), s.getMinsalary(), sum, s.getMinsalary().add(sum), s.getWaiter().getName());
			svList.add(svo);
		}
		pagination.setRows(svList);
		return pagination;
	}
	
	@Override
	public Pagination getPaginationSalary(String date,SalaryVo sv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(sv != null && StringUtils.isNotBlank(sv.getWatiername())) {
			hql = "from Salary s,Waiter w where s.waiter.id = w.id and w.name like ?";
			param = new String[]{"%"+sv.getWatiername()+"%"};
		
		} else {
			hql = "from Salary";
			param = new String[]{};
		}
		Pagination pagination = salaryDao.find(hql, param, page, rows);
		
		//得到所有工资详细,得到这个月的工资信息
		//得到上个月的最后一天
		String[] dateindex = date.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date predate = sdf.parse(date);
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, Integer.parseInt(dateindex[0])); 
		ca.set(Calendar.MONTH, Integer.parseInt(dateindex[1])-1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		predate = ca.getTime();
		//得到下个月的第一天
		Date nextdate = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(dateindex[0])); 
		calendar.set(Calendar.MONTH, Integer.parseInt(dateindex[1]));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    nextdate = calendar.getTime();
		
		String hqls = "from SalaryDetail sd where sd.date> date('"+sdf.format(predate)+"') and sd.date < date('"+sdf.format(nextdate)+"')";
		List<SalaryDetail> sdList = salaryDeatailDao.find(hqls);
		
		if(sv != null && StringUtils.isNotBlank(sv.getWatiername())) {
			List<Object[]> list = pagination.getRows();
			List svList = new ArrayList();
			SalaryVo svo = null;
			for(Object[] obj : list) {
				Salary s = (Salary) obj[0];
				
				BigDecimal sum = new BigDecimal(0);
				for(SalaryDetail sd: sdList) {
					if(s.getId() == sd.getSalary().getId()){
						sum = sum.add(sd.getReward());
					}
				}
				
				svo = new SalaryVo(s.getId(), s.getMinsalary(), sum, s.getMinsalary().add(sum), s.getWaiter().getName());
				svList.add(svo);
			}
			pagination.setRows(svList);
			return pagination;
		}
		
		List<Salary> list = pagination.getRows();
		List svList = new ArrayList();
		SalaryVo svo = null;
		for(Salary s : list) {
			
			BigDecimal sum = new BigDecimal(0);
			for(SalaryDetail sd: sdList) {
				if(s.getId() == sd.getSalary().getId()){
					sum = sum.add(sd.getReward());
				}
			}
			
			svo = new SalaryVo(s.getId(), s.getMinsalary(), sum, s.getMinsalary().add(sum), s.getWaiter().getName());
			svList.add(svo);
		}
		pagination.setRows(svList);
		return pagination;
	}

	@Override
	public void addSalary(Salary salary) {
		salaryDao.save(salary);
	}

	@Override
	public Salary findSalaryByWaiterId(int waiterid) {
		Salary salary = null;
		salary = salaryDao.findSalaryByWaiterId(waiterid);
		return salary;
	}

	@Override
	public SalaryVo getSalary(String id) {
		Salary s = salaryDao.get(Salary.class, Integer.parseInt(id));
		SalaryVo svo = new SalaryVo(s.getId(), s.getMinsalary(), s.getReward(), s.getSummoney(), s.getWaiter().getId());
		return svo;
	}

	@Override
	public void updateSalary(Salary salary) {
		salaryDao.update(salary);
	}

	@Override
	public void deleteSalarys(String ids) {
		salaryDeatailDao.deleteBySalaryId(ids, "SalaryDetail");
		salaryDao.deleteBatch(ids, "Salary");
	}
	

	
}
