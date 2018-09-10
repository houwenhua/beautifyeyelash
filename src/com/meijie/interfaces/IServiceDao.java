package com.meijie.interfaces;

import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;

public interface IServiceDao extends IBaseDao<Service> {

	void save(ServiceImage img);

	ServiceImage get(String hql);

	void delete(String hql);

	void deleteBatch(String hql);

	ServiceImage get(Class<ServiceImage> img, int id);

}
