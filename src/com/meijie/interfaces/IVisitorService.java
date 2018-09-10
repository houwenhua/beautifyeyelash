package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.entity.Visitor;
import com.meijie.vo.ConsumeVo;

public interface IVisitorService {

	Pagination getPaginationVisitor(ConsumeVo cv, int page, int rows) throws Exception;

	void addVisitor(Visitor visitor);

	void deleteVisitors(String ids);

	ConsumeVo queryObjectById(int id);

	void updateVisitor(Visitor visitor);

}
