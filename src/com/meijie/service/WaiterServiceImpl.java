
package com.meijie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meijie.entity.Consume;
import com.meijie.entity.GoodsRecord;
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.entity.Store;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.interfaces.IConsumeService;
import com.meijie.interfaces.IGoodsRecordDao;
import com.meijie.interfaces.IGoodsRecordService;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.IVisitorDao;
import com.meijie.interfaces.IVisitorService;
import com.meijie.interfaces.IWaiterDao;
import com.meijie.interfaces.IWaiterService;

public class WaiterServiceImpl implements IWaiterService {

	@Autowired
	private IConsumeDao consumeDao;

	@Autowired
	private IVisitorDao visitorDao;

	private IWaiterDao waiterDao;
	
	@Autowired
	private IGoodsRecordDao goodsRecordDao;
	
	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;

	public IWaiterDao getWaiterDao() {
		return waiterDao;
	}

	public void setWaiterDao(IWaiterDao waiterDao) {
		this.waiterDao = waiterDao;
	}

	@Override
	public Pagination getPaginationWaiter(Waiter waiter, int page, int rows) throws Exception {
		String hql = "from Waiter";
		String[] param = null;
		// 设置参数
		if (waiter != null) {
			hql = "from Waiter where name like ?";
			param = new String[] { "%" + waiter.getName() + "%" };
		} else {
			hql = "from Waiter";
			param = new String[] {};
		}
		Pagination pagination = waiterDao.find(hql, param, page, rows);
		return pagination;
	}

	@Override
	public Store getStore(int storeid) {
		String hql = "from Store where id=" + storeid;
		Store store = waiterDao.findStore(hql);
		return store;
	}

	@Override
	public List<Store> getStoreAll() {
		String hql = "from Store";
		List<Store> list = waiterDao.findStoreAll(hql);
		return list;
	}

	@Override
	public void addWaiter(Waiter waiter) {
		waiterDao.save(waiter);
	}

	//删除该员工的工资记录和工资记录详情，并将会员消费，游客消费，商品消费的服务员工置为null
	@Override
	public void deleteWaiters(String ids) {
		String[] indexs = ids.split(",");
		/*for(int i = 0; i < indexs.length; i++) {
			int index = Integer.parseInt(indexs[i]);
			
			// 所有会员消费记录
			List<Consume> cList = consumeDao.findAllConsumeByWaiterId(index);
			for(Consume c : cList) {
				c.setWaiter(null);
				consumeDao.update(c);
			}
			
			// 所有游客的消费记录
			List<Visitor> vList = visitorDao.findAllVisitorByWaiterId(index);
			for(Visitor v : vList) {
				v.setWaiter(null);
				visitorDao.update(v);
			}
			
			//所有商品记录
			List<GoodsRecord> grList = goodsRecordDao.findAllGoodsRecordByWaiterId(index);
			for(GoodsRecord gr : grList) {
				gr.setWaiter(null);
				goodsRecordDao.update(gr);
			}
		}*/
		//删除工资表和工资详情
		String sdids = "";
		for(int i = 0; i < indexs.length; i++) {
			int index = Integer.parseInt(indexs[i]);
			Salary salary = salaryDao.findSalaryByWaiterId(index);
			
			if(salary != null){
				String hqlsd = "from SalaryDetail where salaryid = " + salary.getId();
				List<SalaryDetail> sdList = salaryDeatailDao.find(hqlsd);
				for(SalaryDetail sd : sdList) {
					sdids += sd.getId() + ",";
				}
			}
		}
		
		if(!sdids.equals("")){
			salaryDeatailDao.deleteBatch(sdids.substring(0,sdids.length()-1),"SalaryDetail");
		}
		
		salaryDao.deleteBatchByWaiterId(ids,"Salary");
		
		waiterDao.deleteBatch(ids, "Waiter");
	}

	@Override
	public Waiter queryObjectById(int id) {
		return waiterDao.get(Waiter.class, id);
	}

	@Override
	public void updateWaiter(Waiter waiter) {
		Waiter temp = waiterDao.get(Waiter.class, waiter.getId());
		temp.setPhone(waiter.getPhone());
		temp.setName(waiter.getName());
		temp.setSalary(waiter.getSalary());
		temp.setSex(waiter.getSex());
		temp.setAge(waiter.getAge());
		temp.setStoreid(waiter.getStoreid());
		temp.setJob(waiter.getJob());
		waiterDao.update(temp);
	}

	@Override
	public List<Waiter> findAll() {
		String hql = "from Waiter";
		return waiterDao.find(hql);
	}
}
