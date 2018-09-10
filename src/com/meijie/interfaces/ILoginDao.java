package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.SysUser;
import com.meijie.entity.Tree;

public interface ILoginDao extends IBaseDao<SysUser> {

	List<Tree> findTresList(String hql);

}
