package com.meijie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.IVisitorDao;
import com.meijie.interfaces.IVisitorService;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ConsumeVo;

@Component
public class VisitorServiceImpl implements IVisitorService {

	@Autowired
	private IVisitorDao visitorDao;
	
	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;

	@Override
	public Pagination getPaginationVisitor(ConsumeVo cv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		
		if(cv != null && (StringUtils.isNotBlank(cv.getServicename()) || StringUtils.isNotBlank(cv.getWaitername()) || StringUtils.isNotBlank(cv.getVipname()))) {
			hql = "from Visitor v,Service s,Waiter w where " +
		          "v.service.id = s.id and v.waiter.id = w.id " +
				  "and s.servicename like ? and w.name like ? and v.name like ? order by date desc";
			param = new String[]{"%"+cv.getServicename()+"%","%"+cv.getWaitername()+"%","%"+cv.getVipname()+"%"};
		
		} else {
			hql = "from Visitor order by date desc";
			param = new String[]{};
		}
		
		Pagination pagination = visitorDao.find(hql, param, page, rows);
		
		List<Visitor> list = pagination.getRows();
		List cvList = new ArrayList();
		ConsumeVo cvo = null;
		for(Visitor v : list) {
			Service service = v.getService();
			Waiter waiter = v.getWaiter();
			String serviceName = "暂无";
			String waiterName = "暂无";
			if(waiter != null){
				waiterName = waiter.getName();
			}
			if(service != null) {
				serviceName = service.getServicename();
			}
			cvo = new ConsumeVo(v.getId(),v.getMoney(),v.getName(),waiterName,serviceName,v.getDate());
			cvList.add(cvo);
		}
		pagination.setRows(cvList);
		return pagination;
	}

	@Override
	public void addVisitor(Visitor visitor) {
		//保存工资详细记录
		BigDecimal rate = new BigDecimal(0.1);
		BigDecimal reward = rate.multiply(visitor.getMoney());
		String hql = "from Salary where waiterid = "+visitor.getWaiter().getId();
		Salary salary = null;
		List<Salary> list = salaryDao.find(hql);
		if(list.size() > 0){
			salary = list.get(0);
		} else {
			throw new DataBasesException("该员工工资信息不存在,请先添加员工工资信息");
		}
		SalaryDetail sa = new SalaryDetail(visitor.getDate(), visitor.getMoney(), rate, reward, "0", visitor,salary);
		salaryDeatailDao.save(sa);
		
		visitorDao.save(visitor);
	}

	@Override
	public void deleteVisitors(String ids) {
		//删除对应的工资详情
		salaryDeatailDao.deleteByObjectId(ids,"SalaryDetail","visitorid");
		visitorDao.deleteBatch(ids, "Visitor");
	}
	
	@Override
	public ConsumeVo queryObjectById(int id) {
		Visitor visitor = visitorDao.get(Visitor.class, id);
		Service service = visitor.getService();
		Waiter waiter = visitor.getWaiter();
		String serviceName = "暂无";
		String waiterName = "暂无";
		Integer waiterid = null;
		Integer serviceid = null;
		if(waiter != null){
			waiterName = waiter.getName();
			waiterid = waiter.getId();
		}
		if(service != null) {
			serviceName = service.getServicename();
			serviceid = service.getId();
		}
		
		return new ConsumeVo(visitor.getId(),visitor.getMoney(),visitor.getName(),waiterName,serviceName
				,visitor.getDate(),serviceid,waiterid);
	}

	@Override
	public void updateVisitor(Visitor visitor) {
		visitorDao.update(visitor);
		//修改工资详情
		String hql = "from SalaryDetail where visitorid = " + visitor.getId();
		SalaryDetail sd = salaryDeatailDao.find(hql).get(0);
		sd.setAllmoney(visitor.getMoney());
		sd.setReward(sd.getRate().multiply(visitor.getMoney()));
		String hqls = "from Salary where waiterid = "+visitor.getWaiter().getId();
		Salary salary = salaryDao.find(hqls).get(0);
		sd.setSalary(salary);
		salaryDeatailDao.update(sd);
	}
}
