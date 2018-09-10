package com.meijie.service;

import java.util.List;

import com.meijie.entity.SysUser;
import com.meijie.entity.Tree;
import com.meijie.interfaces.ILoginDao;
import com.meijie.interfaces.ILoginService;

public class LoginServiceImpl implements ILoginService {

	private ILoginDao loginDao;
	public ILoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
	@Override
	public List<SysUser> login(SysUser user) {
		String hql = "from SysUser where loginname=? and password=?";
		List<SysUser> list = loginDao.find(hql,new Object[]{user.getLoginname(),user.getPassword()});
		return list;
	}
	
	public List<Tree> getTreeList(int treeid) {
		String hql = "from Tree where tid="+treeid;
		List<Tree> list = loginDao.findTresList(hql);
		return list;
	}
	@Override
	public void updatePassword(SysUser user) {
		loginDao.update(user);
	}
	
}
