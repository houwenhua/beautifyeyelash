package com.meijie.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meijie.entity.Operator;
import com.meijie.entity.Pagination;
import com.meijie.entity.Store;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IWaiterService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.opensymphony.xwork2.ActionSupport;

public class WaiterAction extends ActionSupport {

	private IWaiterService waiterService;
	public IWaiterService getWaiterService() {
		return waiterService;
	}
	public void setWaiterService(IWaiterService waiterService) {
		this.waiterService = waiterService;
	}
	public String paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		Waiter waiter = null;
		if(name!=null){
			waiter = new Waiter();
			waiter.setName(name);
		}
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = waiterService.getPaginationWaiter(waiter,page,rows);
		List list = pagination.getRows();
		JSONObject obj = null;
		List listJson = new ArrayList();
		if(list.size()>0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Waiter w = (Waiter) it.next();
				obj = new JSONObject();
				int storeid = w.getStoreid();
				Store store = waiterService.getStore(storeid);
				obj.put("id", w.getId());
				obj.put("name", w.getName());
				obj.put("phone", w.getPhone());
				obj.put("sex", w.getSex());
				obj.put("age", w.getAge());
				obj.put("job", w.getJob());
				obj.put("salary", w.getSalary());
				if(store!=null){
					obj.put("storeid", w.getStoreid());
					obj.put("storename", store.getName());
				}
				listJson.add(obj);
			}
		}
		String jsonWaiter = JSON.toJSONString(listJson);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonWaiter);
		return NONE;
	}
	public String queryStore() throws IOException{
		List<Store> list = waiterService.getStoreAll();
		List listJson = new ArrayList();
		JSONObject json = null;
		if(list.size()>0){
			for(Store s : list){
				json = new JSONObject();
				json.put("storeid", s.getId());
				json.put("storename", s.getName());
				listJson.add(json);
			}
		}
		String jsonStore = JSON.toJSONString(listJson);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonStore);
		return  NONE;
	}
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String salary = request.getParameter("salary");
		String storeid = request.getParameter("storeid");
		String job = request.getParameter("job");
		Waiter waiter = new Waiter(name, phone, sex, Integer.parseInt(age), 0.0, Integer.parseInt(storeid),job);
		
		try {
			waiterService.addWaiter(waiter);
			result.setMsg("添加成功");
			result.setSuccess(true);
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("添加失败");
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
			waiterService.deleteWaiters(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	public String queryObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		Waiter waiter = waiterService.queryObjectById(Integer.parseInt(id));
		String objectJson = JSON.toJSONString(waiter);
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
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String salary = request.getParameter("salary");
		String storeid = request.getParameter("storeid");
		String job = request.getParameter("job");
		Waiter waiter = new Waiter(Integer.parseInt(id),name, phone, sex, Integer.parseInt(age), 0.0, Integer.parseInt(storeid),job);
		
		try {
			waiterService.updateWaiter(waiter);
			result.setMsg("修改成功");
			result.setSuccess(true);
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
}
