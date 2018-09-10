package com.meijie.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Goods;
import com.meijie.entity.GoodsClass;
import com.meijie.entity.GoodsRecord;
import com.meijie.entity.GoodsRecordDetail;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IGoodsClassDao;
import com.meijie.interfaces.IGoodsDao;
import com.meijie.interfaces.IGoodsRecordDao;
import com.meijie.interfaces.IGoodsRecordDetailDao;
import com.meijie.interfaces.IGoodsRecordDetailService;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.IWaiterDao;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.GoodsRecordDetailVo;

@Service
public class GoodsRecordDetailServiceImpl implements IGoodsRecordDetailService {

	@Autowired
	private IGoodsRecordDetailDao goodsRecordDetailDao;
	
	@Autowired
	private IGoodsRecordDao goodsRecordDao;
	
	@Autowired
	private IGoodsDao goodsDao;
	
	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;
	
	@Autowired
	private IWaiterDao waiterDao;
	
	@Autowired
	private IGoodsClassDao goodsClassDao;

	@Override
	public void saveGoodsRecordDetail(List<GoodsRecordDetailVo> list, int waiterid) {

		Waiter waiter = waiterDao.get(Waiter.class, waiterid);
		
		BigDecimal sum = new BigDecimal("0");
		String date = null;
		for(GoodsRecordDetailVo v : list){
			sum = sum.add(v.getGrmoney());
			date = v.getGrdate();
			String[] indexs = v.getName().split("--");
			String hql = "from GoodsClass where name = '" + indexs[0] + "' and brand = '" + indexs[1] +"'";
			List<GoodsClass> gclist = goodsClassDao.find(hql);
			if(gclist.size() > 0) {
				GoodsClass gc = gclist.get(0);
				gc.setNumber(gc.getNumber() - v.getNumber());
				goodsClassDao.update(gc);
			}
		}
		
		GoodsRecord gr = new GoodsRecord(sum,date);
		
		GoodsRecordDetail grd = null;
		Set<GoodsRecordDetail> set = new HashSet<>();
		for(GoodsRecordDetailVo v : list){
			Goods goods = goodsDao.get(Goods.class, v.getGoodsid());
			grd = new GoodsRecordDetail();
			grd.setName(v.getName());
			grd.setPrice(v.getPrice());
			grd.setNumber(v.getNumber());
			grd.setGrmoney(v.getGrmoney());
			grd.setGrdate(v.getGrdate());
			grd.setGoods(goods);
			grd.setGoodsRecord(gr);
			set.add(grd);
		}
		gr.setGoodsRecordDetail(set);
		gr.setReallmoney(sum);
		gr.setWaiter(waiter);
		goodsRecordDao.save(gr);
		
		
		//保存工资详细记录
		BigDecimal rate = new BigDecimal(0.1);
		BigDecimal reward = rate.multiply(gr.getReallmoney());
		String hql = "from Salary where waiterid = "+gr.getWaiter().getId();
		Salary salary = null;
		List<Salary> slist = salaryDao.find(hql);
		if(slist.size() > 0){
			salary = slist.get(0);
		} else {
			throw new DataBasesException("该员工工资信息不存在，请先添加员工工资信息");
		}
		SalaryDetail sa = new SalaryDetail(gr.getGrdate(), gr.getGrmoney(), rate, reward, "0", gr,salary);
		salaryDeatailDao.save(sa);
	}

	
	
}
