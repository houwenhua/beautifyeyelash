package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Consume;
import com.meijie.utils.DataBasesException;

public interface IConsumeDao extends IBaseDao<Consume>{

	void updateConsume(Consume consume) throws DataBasesException;

	void deleteBatchByVipid(String ids, String obj);

	void deleteBatchByServiceid(String ids, String obj);
	
	void deleteBatchByWaiterid(String ids, String obj);

	List<Consume> findAllConsumeByWaiterId(int index);

}
