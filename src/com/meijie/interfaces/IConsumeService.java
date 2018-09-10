package com.meijie.interfaces;

import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ConsumeVo;

public interface IConsumeService {

	boolean addConsume(Consume consume);

	Pagination getPaginationConsume(ConsumeVo cv, int page, int rows) throws Exception;

	Integer deleteConsumes(String ids);

	ConsumeVo queryObjectById(int id);

	void updateConsume(Consume consume) throws DataBasesException;

}
