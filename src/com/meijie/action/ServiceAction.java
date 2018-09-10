package com.meijie.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;
import com.meijie.entity.Vip;
import com.meijie.interfaces.IMaterialService;
import com.meijie.interfaces.IServiceService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ServiceVo;
import com.opensymphony.xwork2.ActionSupport;

public class ServiceAction extends ActionSupport {
	
	@Autowired
	private IMaterialService materialService;

	private IServiceService serviceService;
	public void setServiceService(IServiceService serviceService) {
		this.serviceService = serviceService;
	}
	private File image;
	private String imageFileName;
	private String imageContentType;
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File imagePath) {
		this.image = imagePath;
	}
	
	
	public String paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String servicename = request.getParameter("servicename");
		Service service = null;
		if(servicename!=null){
			service = new Service();
			service.setServicename(servicename);
		}
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = serviceService.getPaginationService(service,page,rows);
		List<Service> list = pagination.getRows();
		JSONObject obj = null;
		List listJson = new ArrayList();
		ServiceVo sv = null;
		if(list.size()>0){
			for(Service s : list){
				sv = new ServiceVo();
				obj = new JSONObject();
				
				sv.setId(s.getId());
				sv.setImagepath(s.getImagepath());
				sv.setPrice(s.getPrice());
				sv.setVipprice(s.getVipprice());
				sv.setServicename(s.getServicename());
				sv.setRemark(s.getRemark());
				sv.setRealpicturepath("http://localhost:8088/upload/default.jpg");
				Set<Material> mSet = s.getMaterials();
				String material = "";
				for(Material m : mSet){
					material += m.getName()+",";
				}
				if(!"".equals(material)){
					sv.setMaterial(material.substring(0,material.length()-1));
				} else {
					sv.setMaterial(material);
				}
				
				int sid = s.getId();
				ServiceImage img = serviceService.getServiceImage(sid);
				
				if(img != null){
					sv.setImagepath(img.getFilename());
					sv.setRealpicturepath("http://localhost:8088/upload/"+img.getServicename());
				}
				listJson.add(sv);
			}
		}
		pagination.setRows(listJson);
		String jsonService = JSON.toJSONString(pagination);
		
		try {
			response.getWriter().write(jsonService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 其中包含图片的上传
	 * @throws IOException 
	 * 
	 * 
	 * */
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false); 
		
		String servicename = request.getParameter("servicename");
		String price = request.getParameter("price");
		String vipprice = request.getParameter("vipprice");
		String remark = request.getParameter("remark");
		String imagepath = "default.jpg";
		/*String material = request.getParameter("materialids");
		String[] indexs = material.split(",");
		Set<Material> set = new HashSet<>();
		for(int i = 0; i < indexs.length; i++) {
			int index = Integer.parseInt(indexs[i]);
			Material m = materialService.getMaterial(index);
			set.add(m);
		}*/
		Service service = new Service(servicename, imagepath, Double.parseDouble(price), Double.parseDouble(vipprice), remark);
		
		try{
			serviceService.addService(service);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
		
	}

	public String upload() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");//获得服务id；
		
		long date = new Date().getTime();
		String path = "F:/apache-tomcat-8.0.28/webapps/upload";  
		String servicename = path+//ServletActionContext.getServletContext().getRealPath("/upload")
				"/"+date+imageFileName.substring(imageFileName.lastIndexOf(".")==-1?0:imageFileName.
						lastIndexOf("."),imageFileName.length());
		File destFile = new File(servicename);
		FileUtils.copyFile(image,destFile); 
		
		ServiceImage img = new ServiceImage();
		img.setServiceid(Integer.parseInt(id));
		img.setFilename(imageFileName);
		img.setServicename(date+imageFileName.substring(imageFileName.lastIndexOf(".")==-1?0:imageFileName.
						lastIndexOf("."),imageFileName.length()));
		String abPath = path;//ServletActionContext.getServletContext().getRealPath(path);
		serviceService.addUpload(img,abPath);
		response.getWriter().write("1");
		return NONE;
	}
	public String queryObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		Service service = serviceService.queryObjectById(Integer.parseInt(id));
		String objectJson = JSON.toJSONString(service);
		response.getWriter().write(objectJson);
		return NONE;
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false); 
		
		String id = request.getParameter("id");
		String servicename = request.getParameter("servicename");
		String price = request.getParameter("price");
		String vipprice = request.getParameter("vipprice");
		String remark = request.getParameter("remark");
		Service service = new Service(Integer.parseInt(id), servicename, Double.parseDouble(price), Double.parseDouble(vipprice), remark);
		
		try{
			serviceService.updateService(service);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	public void remove() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String ids = request.getParameter("ids");
		String path = "F:/apache-tomcat-8.0.28/webapps/upload";
		//String abPath = ServletActionContext.getServletContext().getRealPath("/upload");
		
		try {
			serviceService.deleteServices(ids,path);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	/**
	 * 查询所有图片进行展示
	 * @throws Exception 
	 * 
	 * */ 
	public void queryDispaly() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		String page1 = request.getParameter("page");
		int page = Integer.parseInt(page1);
		int rows = 9;
		Pagination pagination = serviceService.getPaginationDisplayService(null,page,rows);
		String jsonService = JSON.toJSONString(pagination);
		response.getWriter().write(jsonService);
	}
	
	public void queryMaterial() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		List<Material> list = materialService.findAllMaterial();
		List listJson = null;
		JSONObject json = null;
		if(list.size()>0){
			listJson = new ArrayList();
			for(Material m : list){
				json = new JSONObject();
				json.put("materialid", m.getId());
				json.put("materialname", m.getName());
				listJson.add(json);
			}
		}
		String jsonM = JSON.toJSONString(listJson);
		response.getWriter().write(jsonM);
	}
	
	
}
