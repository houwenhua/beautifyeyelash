package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Goods;
import com.meijie.entity.Pagination;
import com.meijie.vo.GoodsVo;

public interface IGoodsService {

	Pagination getPaginationGoods(int id, GoodsVo gv, int page, int rows) throws Exception;

	void addGoods(Goods goods);

	void deleteGoods(String ids);

	GoodsVo getGoods(int id);

	void updateGoods(Goods goods);

	List<Goods> getGoodsList();

	Pagination getPaginationGoodsAll(GoodsVo gv, int page, int rows) throws Exception;

}
