package com.meijie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.meijie.entity.Pagination;
import com.meijie.entity.ServiceImage;
import com.meijie.interfaces.IServiceImageDao;

public class ServiceImageDaoImpl extends BaseDaoImpl<ServiceImage> implements IServiceImageDao {

	@Override
	public ServiceImage getServiceImage(int id) {
		ServiceImage si = null;
		String hql = "from ServiceImage where serviceid = "+id;
		List<ServiceImage> list = (List<ServiceImage>) super.getHt().find(hql);
		if(list.size()>0){
			si = list.get(0);
		}
		return si;
	}

}
