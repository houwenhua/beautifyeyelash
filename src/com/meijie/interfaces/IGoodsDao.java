package com.meijie.interfaces;

import com.meijie.entity.Goods;

public interface IGoodsDao extends IBaseDao<Goods> {

	void deleteBatchByPurchaseid(String ids, String obj);

}
