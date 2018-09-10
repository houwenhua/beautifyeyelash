package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.vo.SalaryDetailVo;

public interface ISalaryDetailService {

	Pagination getPaginationGoods(SalaryDetailVo sdv, int page, int rows) throws Exception;

}
