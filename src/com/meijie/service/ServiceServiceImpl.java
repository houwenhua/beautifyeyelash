package com.meijie.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.interfaces.IServiceDao;
import com.meijie.interfaces.IServiceImageDao;
import com.meijie.interfaces.IServiceService;
import com.meijie.interfaces.IVisitorDao;
import com.meijie.vo.ServiceVo;


public class ServiceServiceImpl implements IServiceService {

	@Autowired
	private IConsumeDao consumeDao;
	
	@Autowired
	private IVisitorDao visitorDao;
	
	@Autowired
	private IServiceImageDao serviceImageDao;
	
	private IServiceDao serviceDao;
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	@Override
	public Pagination getPaginationService(Service service, int page, int rows) throws Exception {
		String hql = "from Service";
		String[] param = null;
		//设置参数
		if(service!=null){
			hql = "from Service where servicename like ?";
			param = new String[]{"%"+service.getServicename()+"%"}; 
		}else{
			hql = "from Service";
			param = new String[]{};
		}
		Pagination pagination = serviceDao.find(hql, param, page, rows);
		return pagination;
	}
	@Override
	public void addService(Service service) {
		serviceDao.save(service);
	}
	@Override
	public void addUpload(ServiceImage img, String abPath) {
		ServiceImage serviceImg = null;
		if((serviceImg=this.getServiceImage(img.getServiceid()))!=null){
			this.deleteimage(img.getServiceid());
			String servicename = serviceImg.getServicename();
			File file = new File(abPath+"/"+servicename);
			if(file.exists()){
				file.delete();
			}
		}
		//int i = 10/0;
		serviceDao.save(img);
	}
	@Override
	public ServiceImage getServiceImage(int sid) {
		String hql = "from ServiceImage where serviceid="+sid;
		ServiceImage img = serviceDao.get(hql);
		return img;
	}
	@Override
	public void deleteimage(int sid) {
		String hql = "delete from ServiceImage where serviceid="+sid;
		serviceDao.delete(hql);
	}
	@Override
	public Service queryObjectById(int id) {
		Service service = serviceDao.get(Service.class, id);
		return service;
	}
	@Override
	public void updateService(Service service) {
		Service temp = serviceDao.get(Service.class, service.getId());
		temp.setServicename(service.getServicename());
		temp.setPrice(service.getPrice());
		temp.setVipprice(service.getVipprice());
		temp.setRemark(service.getRemark());
		serviceDao.update(temp);
	}
	
	@Override
	public void deleteServices(String ids,String abPath) {
		String[] id  = ids.split(",");
		for(int i=0;i<id.length;i++){
			ServiceImage serviceImg = this.getServiceImage(Integer.parseInt(id[i]));
			if(serviceImg!=null){
				String servicename = serviceImg.getServicename();
				File file = new File(abPath+"/"+servicename);
				if(file.exists()){
					file.delete();
				}
			}
		}
		//删除所有会员消费记录
		//consumeDao.deleteBatchByServiceid(ids, "Consume");
		//删除所有游客的消费记录
		//visitorDao.deleteBatchByServiceid(ids, "Visitor");
		//删除服务
		serviceDao.deleteBatch(ids, "Service");
		this.deleteServiceImage(ids);
	}
	private ServiceImage getServiceImageById(int id) {
		ServiceImage img = serviceDao.get(ServiceImage.class,id);
		return img;
	}
	public void deleteServiceImage(String ids){
		String hql = "delete from ServiceImage where serviceid in("+ids+")";
		serviceDao.deleteBatch(hql);
	}
	@Override
	public List<Service> findAll() {
		String hql = "from Service";
		return serviceDao.find(hql);
	}
	@Override
	public Pagination getPaginationDisplayService(ServiceVo sv, int page, int rows) throws Exception {
		String hql = "from Service";
		String[] param = null;
		//设置参数
		if(sv != null){
			hql = "from Service where servicename like ?";
			param = new String[]{"%"+sv.getServicename()+"%"}; 
		}else{
			hql = "from Service";
			param = new String[]{};
		}
		Pagination pagination = serviceDao.find(hql, param, page, rows);
		List<Service> list = pagination.getRows();
		
		List<ServiceVo> sList = new ArrayList<>();
		ServiceVo svo = null;
		for(Service s : list) {
			//根据服务id查询出图片
			ServiceImage si = serviceImageDao.getServiceImage(s.getId());
			String reallPath = "default.jpg";
			if(si != null) {
				reallPath = si.getServicename();
			}
			svo = new ServiceVo();
			svo.setServicename(s.getServicename());
			svo.setId(s.getId());
			svo.setImagepath(s.getImagepath());
			svo.setPrice(s.getPrice());
			svo.setVipprice(s.getVipprice());
			svo.setRemark(s.getRemark());
			svo.setRealpicturepath(reallPath);
			sList.add(svo);
		}
		pagination.setRows(sList);
		return pagination;
	}
	@Override
	public void addService(Service service, Set<Material> set) {
		service.setMaterials(set);
		serviceDao.save(service);
	}
	
}
