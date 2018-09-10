package com.meijie.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;
import com.meijie.interfaces.IServiceDao;


public class ServiceDaoImpl extends BaseDaoImpl<Service> implements IServiceDao {

	@Override
	public void save(ServiceImage img) {
		super.getHt().save(img);
	}

	@Override
	public ServiceImage get(String hql) {
		List<ServiceImage> list = (List<ServiceImage>) super.getHt().find(hql);
		if(list!=null && list.size()>0){
			ServiceImage img = list.get(0);
			return img;
		}else{
			return null;
		}
	}

	@Override
	public void delete(String hql) {
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public void deleteBatch(String hql) {
		super.getHt().bulkUpdate(hql);
	}

	@Override
	public ServiceImage get(Class<ServiceImage> img, int id) {
		ServiceImage image = super.getHt().get(img, id);
		return image;
	}
}
