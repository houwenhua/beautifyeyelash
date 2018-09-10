package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.entity.Purchase;
import com.meijie.vo.PurchaseVo;

public interface IPurchaseService {

	Pagination getPaginationPurchase(PurchaseVo pv, int page, int rows) throws Exception;

	void addPurchase(PurchaseVo pv);

	void deletePurchases(String ids);

	PurchaseVo queryObjectById(int parseInt);

	void updatePurchase(PurchaseVo pv);

	Purchase getPurchase(int id);

}
