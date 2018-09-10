package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.vo.GoodsClassVo;

public interface IGoodsClassService {

	Pagination getPagination(GoodsClassVo gcv, int page, int rows) throws Exception;

}
