package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Goods;
import com.meijie.entity.GoodsClass;
import com.meijie.entity.Pagination;
import com.meijie.entity.Purchase;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IGoodsClassDao;
import com.meijie.interfaces.IGoodsDao;
import com.meijie.interfaces.IGoodsService;
import com.meijie.vo.ConsumeVo;
import com.meijie.vo.GoodsVo;

@Service
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private IGoodsDao goodsDao;
	
	@Autowired
	private IGoodsClassDao goodsClassDao;

	@Override
	public Pagination getPaginationGoods(int id, GoodsVo gv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(gv != null && StringUtils.isNotBlank(gv.getName())) {
			hql = "from Goods g,Purchase p where " +
		          "g.purchase.id = p.id " +
				  "and p.id = "+id+" and g.name like ?";
			param = new String[]{"%"+gv.getName()+"%"};
		
		} else {
			hql = "from Goods g,Purchase p where " +
			          "g.purchase.id = p.id " +
			          "and p.id = "+id;
			param = new String[]{};
		}
		Pagination pagination = goodsDao.find(hql, param, page, rows);
		
		List<Goods> gList = new ArrayList<>();
		List<Purchase> pList = new ArrayList<>();
		List<Object[]> objList = pagination.getRows();
		for(Object[] obj : objList) {
			gList.add((Goods)obj[0]);
			pList.add((Purchase)obj[1]);
		}
		
		List gvList = new ArrayList();
		GoodsVo gvo = null;
		for(Goods g : gList) {
			gvo = new GoodsVo(g.getId(), g.getName(), g.getGinprice(), g.getGoutprivce(), g.getProductiondate(), g.getProductiontotal(), g.getSupplier(),g.getStoragetime(),g.getBrand());
			gvList.add(gvo);
		}
		pagination.setRows(gvList);
		return pagination;
	}

	@Override
	public void addGoods(Goods goods) {
		//根据商品名和品牌判断是否是同一间商品
		String hql = "from GoodsClass where name = '" + goods.getName() + "' and brand = '" + goods.getBrand()+"'";
		List<GoodsClass> list = goodsClassDao.find(hql);
		if(list.size() > 0){
			GoodsClass gc = list.get(0);
			gc.setNumber(gc.getNumber() + goods.getProductiontotal());
			goodsClassDao.update(gc);
		} else {
			GoodsClass gc = new GoodsClass();
			gc.setName(goods.getName());
			gc.setBrand(goods.getBrand());
			gc.setNumber(goods.getProductiontotal());
			goodsClassDao.save(gc);
		}
		goodsDao.save(goods);
	}

	@Override
	public void deleteGoods(String ids) {
		String hql = "from GoodsClass";
		List<GoodsClass> list = goodsClassDao.find(hql);
		String[] indexs = ids.split(",");
		for(int i = 0; i < indexs.length; i++) {
			int index = Integer.parseInt(indexs[i]);
			Goods goods = goodsDao.get(Goods.class, index);
			for(GoodsClass gc : list) {
				if(gc.getName().equals(goods.getName()) && gc.getBrand().equals(goods.getBrand())){
					gc.setNumber(gc.getNumber() - goods.getProductiontotal());
					goodsClassDao.update(gc);
				}
			}
		}
		goodsDao.deleteBatch(ids, "Goods");
	}

	@Override
	public GoodsVo getGoods(int id) {
		Goods g = goodsDao.get(Goods.class, id);
		GoodsVo gv = new GoodsVo(g.getId(), g.getName(), g.getGinprice(), g.getGoutprivce(), g.getProductiondate(), g.getProductiontotal(), g.getSupplier(), g.getStoragetime(),g.getPurchase().getId(),g.getBrand());
		return gv;
	}

	@Override
	public void updateGoods(Goods goods) {
		String hql = "from GoodsClass where name = '" + goods.getName() + "' and brand = '" + goods.getBrand()+"'";
		List<GoodsClass> list = goodsClassDao.find(hql);
		
		if(list.size() > 0){
			Goods g = goodsDao.get(Goods.class, goods.getId());
			GoodsClass gc = list.get(0);
			gc.setNumber(gc.getNumber() - g.getProductiontotal() + goods.getProductiontotal());
			goodsClassDao.update(gc);
			g.setGinprice(goods.getGinprice());
			g.setGoutprivce(goods.getGoutprivce());
			g.setProductiondate(goods.getProductiondate());
			g.setProductiontotal(goods.getProductiontotal());
			g.setStoragetime(goods.getStoragetime());
			g.setSupplier(goods.getSupplier());
			goodsDao.update(g);
		} /*else {
			GoodsClass gc = new GoodsClass();
			gc.setName(goods.getName());
			gc.setBrand(goods.getBrand());
			gc.setNumber(goods.getProductiontotal());
			
			goodsClassDao.save(gc);
			goodsDao.update(goods);
		}*/
		
	}

	@Override
	public List<Goods> getGoodsList() {
		String hql = "from Goods";
		List<Goods> gList = goodsDao.find(hql);
		for (int i = 0; i < gList.size() - 1; i++) {
			for (int j = gList.size() - 1; j > i; j--) {
				if(gList.get(j).getName().equals(gList.get(i).getName()) && gList.get(j).getBrand().equals(gList.get(i).getBrand())) {
					gList.remove(j);
				}
			}
		}
		return gList;
	}

	@Override
	public Pagination getPaginationGoodsAll(GoodsVo gv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(gv != null && StringUtils.isNotBlank(gv.getName())) {
			hql = "from Goods g where g.name like ?";
			param = new String[]{"%"+gv.getName()+"%"};
		
		} else {
			hql = "from Goods";
			param = new String[]{};
		}
		Pagination pagination = goodsDao.find(hql, param, page, rows);
		List<Goods> gList = pagination.getRows();
		
		List gvList = new ArrayList();
		GoodsVo gvo = null;
		
		/*for (int i = 0; i < gList.size() - 1; i++) {
			Long num = gList.get(i).getProductiontotal();
			for (int j = gList.size() - 1; j > i; j--) {
				if(gList.get(j).getName().equals(gList.get(i).getName())) {
					num += gList.get(j).getProductiontotal();
					gList.get(i).setProductiontotal(num);
					gList.remove(j);
				}
			}
		}*/
		
		for(Goods g : gList) {
			/**/
			
			gvo = new GoodsVo(g.getId(), g.getName(), g.getGinprice(), g.getGoutprivce(), g.getProductiondate(), g.getProductiontotal(), g.getSupplier(),g.getStoragetime(),g.getBrand());
			gvList.add(gvo);
		}
		pagination.setRows(gvList);
		return pagination;
	}
}
