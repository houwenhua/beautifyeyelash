package com.meijie.interfaces;

import com.meijie.entity.ServiceImage;

public interface IServiceImageDao extends IBaseDao<ServiceImage> {

	ServiceImage getServiceImage(int id);

}
