package com.meijie.service;

import java.util.List;

import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.interfaces.IOperatorDao;
import com.meijie.interfaces.IOperatorService;

public class OperatorServiceImpl implements IOperatorService {

	private IOperatorDao operatorDao;
	public IOperatorDao getOperatorDao() {
		return operatorDao;
	}
	public void setOperatorDao(IOperatorDao operatorDao) {
		this.operatorDao = operatorDao;
	}
	@Override
	public Pagination getPaginationOperator(Operator operator, int page, int rows) throws Exception {
		String hql = "from Operator";
		String[] param = null;
		//设置参数
		if(operator!=null){
			hql = "from Operator where name like ?";
			param = new String[]{"%"+operator.getName()+"%"}; 
		}else{
			hql = "from Operator";
			param = new String[]{};
		}
		Pagination pagination = operatorDao.find(hql, param, page, rows);
		return pagination;
	}
	@Override
	public Store getStore(int storeid) {
		String hql = "from Store where id="+storeid;
		Store store = operatorDao.findStore(hql);
		return store;
	}
	@Override
	public List<Store> getStoreAll() {
		String hql = "from Store";
		List<Store> list = operatorDao.findStoreAll(hql);
		return list;
	}
	@Override
	public void addOperator(Operator operator) {
		operatorDao.save(operator);		
	}
	@Override
	public void deleteOperators(String ids) {
		operatorDao.deleteBatch(ids, "Operator");
	}
	@Override
	public Operator queryObjectById(int id) {
		Operator operator = operatorDao.get(Operator.class, id);
		return operator;
	}
	@Override
	public void updateOperator(Operator operator) {
		Operator temp = operatorDao.get(Operator.class, operator.getId());
		temp.setName(operator.getName());
		temp.setPhone(operator.getPhone());
		temp.setSex(operator.getSex());
		temp.setAge(operator.getAge());
		temp.setStoreid(operator.getStoreid());
		operatorDao.update(temp);
	}
}
