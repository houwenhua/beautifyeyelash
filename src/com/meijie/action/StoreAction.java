package com.meijie.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.ServiceImage;
import com.meijie.entity.Store;
import com.meijie.entity.Vip;
import com.meijie.interfaces.IStoreService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.opensymphony.xwork2.ActionSupport;

public class StoreAction extends ActionSupport {

	private IStoreService storeService;
	public IStoreService getStoreService() {
		return storeService;
	}
	public void setStoreService(IStoreService storeService) {
		this.storeService = storeService;
	}
	
	public String paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		Store store = null;
		if(name!=null || address!=null){
			store = new Store();
			store.setName(name);
			store.setAddress(address);
		}
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = storeService.getPaginationService(store,page,rows);
		String jsonStore = JSON.toJSONString(pagination);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsonStore);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Store store = new Store(name,address,phone);
		
		try {
			storeService.addStore(store);
			result.setSuccess(true);
			result.setMsg("添加成功");
		} catch (DataBasesException e) {
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
			storeService.deleteStores(ids);
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
		Store store = storeService.queryObjectById(Integer.parseInt(id));
		String objectJson = JSON.toJSONString(store);
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
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Store store = new Store(Integer.parseInt(id),name,address,phone);
		
		try {
			storeService.updateStore(store);
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
