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
import com.meijie.entity.ShopManager;
import com.meijie.entity.Store;
import com.meijie.interfaces.IShopManagerService;
import com.opensymphony.xwork2.ActionSupport;

public class ShopManagerAction extends ActionSupport {

	private IShopManagerService shopManagerService;
	public IShopManagerService getShopManagerService() {
		return shopManagerService;
	}
	public void setShopManagerService(IShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}
	public String paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		//String address = request.getParameter("address");
		ShopManager sm = null;
		if(name!=null){
			sm = new ShopManager();
			sm.setName(name);
		}
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = shopManagerService.getPaginationShopManager(sm,page,rows);
		List list = pagination.getRows();
		JSONObject obj = null;
		List listJson = new ArrayList();
		if(list.size()>0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				ShopManager o = (ShopManager) it.next();
				obj = new JSONObject();
				int storeid = o.getStoreid();
				Store store = shopManagerService.getStore(storeid);
				obj.put("id", o.getId());
				obj.put("name", o.getName());
				obj.put("phone", o.getPhone());
				obj.put("sex", o.getSex());
				obj.put("age", o.getAge());
				if(store!=null){
					obj.put("storeid", o.getStoreid());
					obj.put("storename", store.getName());
				}
				listJson.add(obj);
			}
		}
		String jsonOperator = JSON.toJSONString(listJson);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonOperator);
		return NONE;
	}
	public String queryStore() throws IOException{
		List<Store> list = shopManagerService.getStoreAll();
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
	public String add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String storeid = request.getParameter("storeid");
		ShopManager sm = new ShopManager(name, phone, sex, Integer.parseInt(age), Integer.parseInt(storeid));
		shopManagerService.addShopManager(sm);
		response.getWriter().write("1");
		return NONE;
	}
	public String remove() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String ids = request.getParameter("ids");
		shopManagerService.deleteShopManagers(ids);
		String[] index = ids.split(",");
		response.getWriter().write(new Integer(index.length).toString());
		return NONE;
	}
	public String queryObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		ShopManager sm = shopManagerService.queryObjectById(Integer.parseInt(id));
		String objectJson = JSON.toJSONString(sm);
		response.getWriter().write(objectJson);
		return NONE;
	}
	public String update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String storeid = request.getParameter("storeid");
		ShopManager sm = new ShopManager(Integer.parseInt(id), name, phone, sex, Integer.parseInt(age), Integer.parseInt(storeid));
		shopManagerService.updateShopManager(sm);
		response.getWriter().write("1");
		return NONE;
	}
}
