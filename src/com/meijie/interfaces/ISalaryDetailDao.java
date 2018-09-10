package com.meijie.interfaces;

import com.meijie.entity.Consume;
import com.meijie.entity.SalaryDetail;

public interface ISalaryDetailDao  extends IBaseDao<SalaryDetail> {

	void deleteByObjectId(String ids, String obj, String objid);

	void deleteBySalaryId(String ids, String string);

}
