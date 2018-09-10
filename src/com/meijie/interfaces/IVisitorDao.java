package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Visitor;

public interface IVisitorDao extends IBaseDao<Visitor> {

	void deleteBatchByServiceid(String ids, String obj);

	void deleteBatchByWaiterid(String ids, String obj);

	List<Visitor> findAllVisitorByWaiterId(int index);

}
