package com.meijie.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IServiceService;
import com.meijie.interfaces.IVisitorService;
import com.meijie.interfaces.IWaiterService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ConsumeVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class VisitorAction extends ActionSupport {

	@Autowired
	private IVisitorService visitorService;
	
	@Autowired
	private IServiceService serviceService;
	
	@Autowired
	private IWaiterService waiterService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String vipname = request.getParameter("vipname");
		String servicename = request.getParameter("servicename");
		String waitername = request.getParameter("waitername");
		
		ConsumeVo cv = null;
		if(vipname != null || servicename != null || waitername != null){
			cv = new ConsumeVo();
			cv.setVipname(vipname);
			cv.setServicename(servicename);
			cv.setWaitername(waitername);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = visitorService.getPaginationVisitor(cv,page,rows);
		
		String jsonWaiter = JSON.toJSONString(pagination);
		response.getWriter().write(jsonWaiter);
	}
	
	public void queryService() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		List<Service> serviceList = serviceService.findAll();
		List listJson = null;
		JSONObject jsonObj = null;
		
		if(serviceList.size()>0){
			listJson = new ArrayList();
			for(Service s : serviceList){
				jsonObj = new JSONObject();
				jsonObj.put("serviceid", s.getId());
				jsonObj.put("servicename", s.getServicename());
				listJson.add(jsonObj);
			}
		}
		String json = JSON.toJSONString(listJson);
		response.getWriter().write(json);
	}
	
	public void queryWaiter() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		List<Waiter> waiterList = waiterService.findAll();
		List listJson = null;
		JSONObject json = null;
		
		if(waiterList.size()>0){
			listJson = new ArrayList();
			for(Waiter obj : waiterList){
				json = new JSONObject();
				json.put("waiterid", obj.getId());
				json.put("waitername", obj.getName());
				listJson.add(json);
			}
		}
		String waiterJson = JSON.toJSONString(listJson);
		response.getWriter().write(waiterJson);
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String vipid = request.getParameter("vipid");
		String serviceid = request.getParameter("serviceid");
		String waiterid = request.getParameter("waiterid");
		String money = request.getParameter("money");
		Date dateTemp = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(dateTemp);
		
		Waiter waiter = waiterService.queryObjectById(Integer.parseInt(waiterid));
		Service service = serviceService.queryObjectById(Integer.parseInt(serviceid));
		Visitor visitor = new Visitor(new BigDecimal(money), vipid, waiter, service, date);
		
		try {
			visitorService.addVisitor(visitor);
			result.setSuccess(true);
			result.setMsg("添加成功");
		} catch (DataBasesException e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
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
			visitorService.deleteVisitors(ids);
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
	 * 根据id查询对象
	 * @throws IOException 
	 */
	public void queryObject() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		ConsumeVo consumeVo = visitorService.queryObjectById(Integer.parseInt(id));
		
		String json = JSON.toJSONString(consumeVo);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String vipid = request.getParameter("vipid");
		String serviceid = request.getParameter("serviceid");
		String waiterid = request.getParameter("waiterid");
		String money = request.getParameter("money");
		
		Date dateTemp = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(dateTemp);
		
		Waiter waiter = waiterService.queryObjectById(Integer.parseInt(waiterid));
		Service service = serviceService.queryObjectById(Integer.parseInt(serviceid));
		Visitor visitor = new Visitor(Integer.parseInt(id),new BigDecimal(money), vipid, waiter, service, date);
		
		try {
			visitorService.updateVisitor(visitor);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch (DataBasesException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
}
