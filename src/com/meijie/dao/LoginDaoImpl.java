package com.meijie.dao;

import java.util.List;

import com.meijie.entity.SysUser;
import com.meijie.entity.Tree;
import com.meijie.interfaces.ILoginDao;

public class LoginDaoImpl extends BaseDaoImpl<SysUser> implements ILoginDao {

	@Override
	public List<Tree> findTresList(String hql) {
		List<Tree> list = (List<Tree>) super.getHt().find(hql);
		return list;
	}

}
