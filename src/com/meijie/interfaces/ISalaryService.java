package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.vo.SalaryVo;

public interface ISalaryService {

	Pagination getPaginationGoods(SalaryVo sv, int page, int rows) throws Exception;

	void addSalary(Salary salary);

	Salary findSalaryByWaiterId(int parseInt);

	SalaryVo getSalary(String id);

	void updateSalary(Salary salary);

	void deleteSalarys(String ids);

	Pagination getPaginationSalary(String date, SalaryVo sv, int page, int rows) throws Exception;

}
