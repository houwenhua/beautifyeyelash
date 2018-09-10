package com.meijie.interfaces;

import com.meijie.entity.GoodsRecordDetail;

public interface IGoodsRecordDetailDao extends IBaseDao<GoodsRecordDetail>{

	void deleteBatchBygoodsrecordid(String ids, String obj);

}
