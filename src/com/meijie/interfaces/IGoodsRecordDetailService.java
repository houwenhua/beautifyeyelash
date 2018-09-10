package com.meijie.interfaces;

import java.util.List;

import com.meijie.vo.GoodsRecordDetailVo;

public interface IGoodsRecordDetailService {

	//void saveGoodsRecordDetail(List<GoodsRecordDetailVo> list);

	void saveGoodsRecordDetail(List<GoodsRecordDetailVo> list, int waiterid);

}
