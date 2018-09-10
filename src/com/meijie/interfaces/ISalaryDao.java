package com.meijie.interfaces;

import com.meijie.entity.Salary;

public interface ISalaryDao extends IBaseDao<Salary> {

	Salary findSalaryByWaiterId(int waiterid);

	void deleteBatchByWaiterId(String ids, String string);

}
