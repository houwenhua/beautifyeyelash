package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.GoodsRecord;
import com.meijie.entity.Visitor;

public interface IGoodsRecordDao extends IBaseDao<GoodsRecord> {


	void deleteBatchByWaiterid(String ids, String obj);

	List<GoodsRecord> findAllGoodsRecordByWaiterId(int index);

}
