package com.meijie.interfaces;

import com.meijie.entity.Pagination;
import com.meijie.entity.SysUser;
import com.meijie.vo.UserVo;

public interface IUserService {

	Pagination getPaginationUser(UserVo user, int page, int rows) throws Exception;

	void addUser(SysUser su);

	void deleteUsers(String ids);

	UserVo getUser(int id);

	void updateUser(SysUser su);

}
