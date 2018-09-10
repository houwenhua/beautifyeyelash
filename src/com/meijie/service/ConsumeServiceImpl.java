package com.meijie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.interfaces.IConsumeService;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.IServiceDao;
import com.meijie.interfaces.IVipDao;
import com.meijie.interfaces.IWaiterDao;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ConsumeVo;

public class ConsumeServiceImpl implements IConsumeService {

	private IConsumeDao consumeDao;
	
	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;

	public void setConsumeDao(IConsumeDao consumeDao) {
		this.consumeDao = consumeDao;
	}
	
	@Override
	public Pagination getPaginationConsume(ConsumeVo cv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		
		if(cv != null && (StringUtils.isNotBlank(cv.getServicename()) || StringUtils.isNotBlank(cv.getWaitername()) || StringUtils.isNotBlank(cv.getVipname()))) {
			hql = "from Consume c,Service s,Waiter w,Vip v where " +
		          "c.service.id = s.id and c.waiter.id = w.id and c.vip.id = v.id " +
				  "and s.servicename like ? and w.name like ? and v.name like ? order by date desc";
			param = new String[]{"%"+cv.getServicename()+"%","%"+cv.getWaitername()+"%","%"+cv.getVipname()+"%"};
		} else {
			hql = "from Consume order by date desc";
			param = new String[]{};
		}
		
		Pagination pagination = consumeDao.find(hql, param, page, rows);
		List<Consume> list = pagination.getRows();
		List cvList = new ArrayList();
		ConsumeVo cvo = null;
		for(Consume c : list) {
			Vip vip = c.getVip();
			Service service = c.getService();
			Waiter waiter = c.getWaiter();
			String vipName = "暂无";
			String serviceName = "暂无";
			String waiterName = "暂无";
			if(waiter != null){
				waiterName = waiter.getName();
			}
			if(vip != null) {
				vipName = vip.getName();
			}
			if(service != null) {
				serviceName = service.getServicename();
			}
			cvo = new ConsumeVo(c.getId(),c.getMoney(),vipName,waiterName,serviceName,c.getDate(),c.getIntegral());
			cvList.add(cvo);
		}
		pagination.setRows(cvList);
		return pagination;
	}

	@Override
	public boolean addConsume(Consume consume) {
		//保存工资详细记录
		BigDecimal rate = new BigDecimal(0.1);
		BigDecimal reward = rate.multiply(consume.getMoney());
		String hql = "from Salary where waiterid = "+consume.getWaiter().getId();
		Salary salary = null;
		List<Salary> list = salaryDao.find(hql);
		if(list.size() > 0){
			salary = list.get(0);
		} else {
			throw new DataBasesException("该员工工资信息不存在，请先添加员工工资信息");
		}
		SalaryDetail sa = new SalaryDetail(consume.getDate(), consume.getMoney(), rate, reward, "0", consume,salary);
		salaryDeatailDao.save(sa);
		
		return consumeDao.save(consume);
	}

	@Override
	public Integer deleteConsumes(String ids) {
		//删除对应的工资详情
		salaryDeatailDao.deleteByObjectId(ids,"SalaryDetail","consumeid");
		
		int number = consumeDao.deleteBatch(ids, "Consume");
		if(number >= 1) {
			return number;
		}
		return 0;
	}

	@Override
	public ConsumeVo queryObjectById(int id) {
		Consume consume = consumeDao.get(Consume.class, id);
		Vip vip = consume.getVip();
		Service service = consume.getService();
		Waiter waiter = consume.getWaiter();
		String vipName = "暂无";
		String serviceName = "暂无";
		String waiterName = "暂无";
		Integer waiterid = null;
		Integer vipid = null;
		Integer serviceid = null;
		if(waiter != null){
			waiterName = waiter.getName();
			waiterid = waiter.getId();
		}
		if(vip != null) {
			vipName = vip.getName();
			vipid = vip.getId();
		}
		if(service != null) {
			serviceName = service.getServicename();
			serviceid = service.getId();
		}
		return new ConsumeVo(consume.getId(),consume.getMoney(),vipName,waiterName,serviceName
				,consume.getDate(),consume.getIntegral(),serviceid,vipid,waiterid);
	}

	@Override
	public void updateConsume(Consume consume) {
		consumeDao.update(consume);
		
		//修改工资详情
		String hql = "from SalaryDetail where consumeid = " + consume.getId();
		SalaryDetail sd = salaryDeatailDao.find(hql).get(0);
		sd.setAllmoney(consume.getMoney());
		sd.setReward(sd.getRate().multiply(consume.getMoney()));
		String hqls = "from Salary where waiterid = "+consume.getWaiter().getId();
		Salary salary = salaryDao.find(hqls).get(0);
		sd.setSalary(salary);
		salaryDeatailDao.update(sd);
		
	}

	
}
