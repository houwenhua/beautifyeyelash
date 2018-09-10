package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Operator;
import com.meijie.entity.Store;

public interface IOperatorDao extends IBaseDao<Operator> {

	Store findStore(String hql);

	List<Store> findStoreAll(String hql);

}
