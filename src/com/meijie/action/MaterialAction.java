package com.meijie.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.meijie.entity.Consume;
import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IMaterialService;
import com.meijie.interfaces.IServiceService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.GoodsVo;
import com.meijie.vo.MaterialVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class MaterialAction extends ActionSupport {

	@Autowired
	private IMaterialService materialService;
	
	@Autowired
	private IServiceService serviceService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
	    MaterialVo mv = null;
		if(name != null) {
			mv = new MaterialVo();
			mv.setName(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = materialService.getPagination(mv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String serviceid = request.getParameter("serviceid");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String productiondate = request.getParameter("productiondate");
		String producer = request.getParameter("producer");
		String remark = request.getParameter("remark");
	
		Service service = serviceService.queryObjectById(Integer.parseInt(serviceid));
		Material material = new Material(name, brand, productiondate, producer, remark, service);
		
		try {
			materialService.addMaterial(material);
			result.setSuccess(true);
			result.setMsg("添加成功");
		} catch (DataBasesException e) {
			e.printStackTrace();
			result.setMsg("添加失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);

	}
	
	public void queryMaterial() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		Material m = materialService.getMaterial(Integer.parseInt(id));
		MaterialVo mv = new MaterialVo(m.getId(), m.getName(), m.getBrand(), m.getProductiondate(), m.getProducer(), m.getRemark(),m.getService().getId());
		
		String json = JSON.toJSONString(mv);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String serviceid = request.getParameter("serviceid");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String productiondate = request.getParameter("productiondate");
		String producer = request.getParameter("producer");
		String remark = request.getParameter("remark");
	
		Service service = serviceService.queryObjectById(Integer.parseInt(serviceid));
		Material m = materialService.getMaterial(Integer.parseInt(id));
		//Material material = new Material(Integer.parseInt(id),name, brand, productiondate, producer, remark, service);
		m.setName(name);
		m.setBrand(brand);
		m.setProducer(producer);
		m.setProductiondate(productiondate);
		m.setRemark(remark);
		m.setService(service);
		try {
			materialService.updateMaterial(m);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch (DataBasesException e) {
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
		try{
			materialService.deleteMaterials(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
}
