package com.meijie.interfaces;

import java.util.List;
import java.util.Set;

import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;
import com.meijie.vo.ServiceVo;

public interface IServiceService {

	Pagination getPaginationService(Service service, int page, int rows) throws Exception;

	void addService(Service service);

	ServiceImage getServiceImage(int sid);

	void deleteimage(int sid);

	Service queryObjectById(int id);

	void updateService(Service service);

	void deleteServices(String ids, String abPath);

	void addUpload(ServiceImage img, String abPath);
	
	List<Service> findAll();

	Pagination getPaginationDisplayService(ServiceVo sv, int page, int rows) throws Exception;

	void addService(Service service, Set<Material> set);

}
