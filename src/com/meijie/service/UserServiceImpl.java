package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.entity.SysUser;
import com.meijie.interfaces.IStoreDao;
import com.meijie.interfaces.IUserDao;
import com.meijie.interfaces.IUserService;
import com.meijie.vo.UserVo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IStoreDao storeDao;
	
	@Override
	public Pagination getPaginationUser(UserVo user, int page, int rows) throws Exception {
		String hql = "";
		String[] param = null;
		//设置参数
		if(user != null && StringUtils.isNoneBlank(user.getName())){
			hql = "from SysUser where name like ?";
			param = new String[]{"%"+user.getName()+"%"}; 
		}else{
			hql = "from SysUser";
			param = new String[]{};
		}
		Pagination pagination = userDao.find(hql, param, page, rows);
		List<SysUser> list = pagination.getRows();
		UserVo uv = null;
		List<UserVo> vList = new ArrayList<>();
		for(SysUser su : list){
			Store store = storeDao.get(Store.class, su.getStoreid());
			uv = new UserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(), su.getJurisdiction(), su.getState(), store.getName());
			vList.add(uv);
		}
		pagination.setRows(vList);
		return pagination;
	}

	@Override
	public void addUser(SysUser su) {
		userDao.save(su);
	}

	@Override
	public void deleteUsers(String ids) {
		userDao.deleteBatch(ids, "SysUser");
	}

	@Override
	public UserVo getUser(int id) {
		SysUser user = userDao.get(SysUser.class, id);
		Store store = storeDao.get(Store.class, user.getStoreid());
		UserVo uv = new UserVo(user.getId(), user.getName(), user.getLoginname(), user.getPassword(), user.getEmail(), user.getJurisdiction(), user.getState(), store.getName());
		return uv;
	}

	@Override
	public void updateUser(SysUser su) {
		userDao.update(su);
	}

}
