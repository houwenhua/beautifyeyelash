package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.SysUser;
import com.meijie.entity.Tree;

public interface ILoginService {

	List<SysUser> login(SysUser user);

	List<Tree> getTreeList(int treeid);

	void updatePassword(SysUser user);

}
