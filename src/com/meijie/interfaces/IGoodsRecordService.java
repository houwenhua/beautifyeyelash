package com.meijie.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.meijie.entity.Pagination;
import com.meijie.vo.GoodsRecordDetailVo;
import com.meijie.vo.GoodsRecordVo;

public interface IGoodsRecordService {

	Pagination getPaginationGoodsRecord(GoodsRecordVo grv, int page, int rows) throws Exception;

	void deleteGoodsRecords(String ids);

	List<GoodsRecordDetailVo> findAllGoodsRecordDetail(int parseInt);

	GoodsRecordVo findAllGoodsRecord(int id);

	void updateGoodsRecord(int id, BigDecimal reallmoney, int waiterid);

}
