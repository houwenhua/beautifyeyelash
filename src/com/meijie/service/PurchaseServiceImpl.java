package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Goods;
import com.meijie.entity.Pagination;
import com.meijie.entity.Purchase;
import com.meijie.interfaces.IGoodsDao;
import com.meijie.interfaces.IGoodsService;
import com.meijie.interfaces.IPurchaseDao;
import com.meijie.interfaces.IPurchaseService;
import com.meijie.vo.PurchaseVo;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	private IPurchaseDao purchaseDao;
	
	@Autowired
	private IGoodsDao goodsDao;
	
	@Autowired
	private IGoodsService goodsService;
	
	@Override
	public Pagination getPaginationPurchase(PurchaseVo pv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(pv != null && (StringUtils.isNotBlank(pv.getProvider()) || StringUtils.isNotBlank(pv.getPurchasename()))) {
			hql = "from Purchase where provider like ? and purchasename like ? order by date desc";
			param = new String[]{"%"+pv.getProvider()+"%","%"+pv.getPurchasename()+"%"};
		
		} else {
			hql = "from Purchase order by date desc";
			param = new String[]{};
		}
		Pagination pagination = purchaseDao.find(hql, param, page, rows);
		
		List<Purchase> list = pagination.getRows();
		List pvList = new ArrayList();
		PurchaseVo pvo = null;
		for(Purchase p : list) {
			pvo = new PurchaseVo(p.getId(),p.getProvider(),p.getPurchasename(),p.getMoney(),p.getDate(),p.getRemark());
			pvList.add(pvo);
		}
		pagination.setRows(pvList);
		return pagination;
	}

	@Override
	public void addPurchase(PurchaseVo pv) {
		Purchase purchase = new Purchase(pv.getProvider(), pv.getPurchasename(), pv.getMoney(), pv.getDate(), pv.getRemark());
		purchaseDao.save(purchase);
	}

	@Override
	public void deletePurchases(String ids) {
		String[] indexs = ids.split(",");
		String goodsid = "";
		for(int i = 0; i < indexs.length; i++){
			int index = Integer.parseInt(indexs[i]);
			String hql = "from Goods where purchaseid = "+index;
			List<Goods> list = goodsDao.find(hql);
			for(Goods g : list) {
				goodsid += g.getId() + ",";
			}
			
		}
		if(!"".equals(goodsid)) {
			goodsService.deleteGoods(goodsid.substring(0, goodsid.length()-1));
		}
		//goodsDao.deleteBatchByPurchaseid(ids, "Goods");
		purchaseDao.deleteBatch(ids,"Purchase");
	}

	@Override
	public PurchaseVo queryObjectById(int id) {
		Purchase p = purchaseDao.get(Purchase.class, id);
		PurchaseVo pv = new PurchaseVo(p.getId(), p.getProvider(), p.getPurchasename(), p.getMoney(), p.getDate(), p.getRemark());
		return pv;
	}

	@Override
	public void updatePurchase(PurchaseVo pv) {
		Purchase purchase = new Purchase(pv.getId(),pv.getProvider(), pv.getPurchasename(), pv.getMoney(), pv.getDate(), pv.getRemark());
		purchaseDao.update(purchase);
	}

	@Override
	public Purchase getPurchase(int id) {
		return purchaseDao.get(Purchase.class, id);
	}

}
