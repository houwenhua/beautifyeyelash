package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.Store;

public interface IOperatorService {

	Pagination getPaginationOperator(Operator operator, int page, int rows) throws Exception;

	Store getStore(int storeid);

	List<Store> getStoreAll();

	void addOperator(Operator operator);

	void deleteOperators(String ids);

	Operator queryObjectById(int id);

	void updateOperator(Operator operator);

}
