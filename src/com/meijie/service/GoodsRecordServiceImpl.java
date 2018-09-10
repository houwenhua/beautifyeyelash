package com.meijie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.GoodsRecord;
import com.meijie.entity.GoodsRecordDetail;
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.SalaryDetail;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IGoodsRecordDao;
import com.meijie.interfaces.IGoodsRecordDetailDao;
import com.meijie.interfaces.IGoodsRecordService;
import com.meijie.interfaces.ISalaryDao;
import com.meijie.interfaces.ISalaryDetailDao;
import com.meijie.interfaces.IWaiterDao;
import com.meijie.vo.GoodsRecordDetailVo;
import com.meijie.vo.GoodsRecordVo;

@Service
public class GoodsRecordServiceImpl implements IGoodsRecordService {

	@Autowired
	private IGoodsRecordDao goodsRecordDao;
	
	@Autowired
	private IGoodsRecordDetailDao goodsRecordDetailDao;
	
	@Autowired
	private IWaiterDao waiterDao;
	
	@Autowired
	private ISalaryDao salaryDao;
	
	@Autowired
	private ISalaryDetailDao salaryDeatailDao;

	@Override
	public Pagination getPaginationGoodsRecord(GoodsRecordVo grv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		
		if(grv != null && (StringUtils.isNotBlank(grv.getStartdate()) || StringUtils.isNotBlank(grv.getEnddate()))) {
			hql = "from GoodsRecord where grdate between  date('"+grv.getStartdate()+"') and date('"+grv.getEnddate()+"') order by grdate desc";
			param = new String[]{};
			
		} else {
			hql = "from GoodsRecord order by grdate desc";
			param = new String[]{};
		}
		Pagination pagination = goodsRecordDao.find(hql, param, page, rows);
		List<GoodsRecord> list = pagination.getRows();
		ArrayList<GoodsRecordVo> grvList = new ArrayList<>();
		GoodsRecordVo grvo = null;
		for(GoodsRecord gr : list) {
			String name = "";
			Set<GoodsRecordDetail> grdSet = gr.getGoodsRecordDetail();
			for(GoodsRecordDetail grd : grdSet) {
				name += grd.getName()+",";
			}
			Waiter waiter = gr.getWaiter();
			String waiterName = "暂无";
			if(waiter != null){
				waiterName = waiter.getName();
			}
			grvo = new GoodsRecordVo(gr.getId(), gr.getGrmoney(), gr.getGrdate(), name.substring(0, name.length()-1),gr.getReallmoney(),waiterName);
			grvList.add(grvo);
		}
		pagination.setRows(grvList);
		return pagination;
	}

	@Override
	public void deleteGoodsRecords(String ids) {
		//删除对应的工资详情
		salaryDeatailDao.deleteByObjectId(ids,"SalaryDetail","grid");
		goodsRecordDetailDao.deleteBatchBygoodsrecordid(ids, "GoodsRecordDetail");
		goodsRecordDao.deleteBatch(ids, "GoodsRecord");
	}

	@Override
	public List<GoodsRecordDetailVo> findAllGoodsRecordDetail(int id) {
		String hql = "from GoodsRecordDetail where goodsRecordid = "+id;
		List<GoodsRecordDetail> list = goodsRecordDetailDao.find(hql);
		List<GoodsRecordDetailVo> listVo = new ArrayList<>();
		GoodsRecordDetailVo gVo = null;
		for(GoodsRecordDetail g : list) {
		    gVo = new GoodsRecordDetailVo(g.getId(), g.getGoods().getId(), g.getName(), g.getNumber(), g.getPrice(), g.getGrmoney(), g.getGrdate());
		    listVo.add(gVo);
		}
		return listVo;
	}

	@Override
	public GoodsRecordVo findAllGoodsRecord(int id) {
		GoodsRecord gr = goodsRecordDao.get(GoodsRecord.class, id);
		Waiter waiter = gr.getWaiter();
		Integer waiterid = null;
		if(waiter != null){
			waiterid = waiter.getId();
		}
		
		GoodsRecordVo grv = new GoodsRecordVo();
		grv.setReallmoney(gr.getReallmoney());
		grv.setWaiterid(waiterid);
		return grv;
	}

	@Override
	public void updateGoodsRecord(int id, BigDecimal reallmoney, int waiterid) {
		GoodsRecord gr = goodsRecordDao.get(GoodsRecord.class, id);
		Waiter waiter = waiterDao.get(Waiter.class, waiterid);
		gr.setReallmoney(reallmoney);
		gr.setWaiter(waiter);
		goodsRecordDao.update(gr);
		
		//修改工资详情
		String hql = "from SalaryDetail where grid = " + gr.getId();
		SalaryDetail sd = salaryDeatailDao.find(hql).get(0);
		sd.setAllmoney(gr.getReallmoney());
		sd.setReward(sd.getRate().multiply(gr.getReallmoney()));
		String hqls = "from Salary where waiterid = "+gr.getWaiter().getId();
		Salary salary = salaryDao.find(hqls).get(0);
		sd.setSalary(salary);
		salaryDeatailDao.update(sd);
	}
}
