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
import com.meijie.entity.Store;
import com.meijie.entity.SysUser;
import com.meijie.entity.Vip;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IUserService;
import com.meijie.interfaces.IWaiterService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.UserVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class UserAction extends ActionSupport{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IWaiterService waiterService;
	
	public void paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
		UserVo user = null;
		if(name != null){
			user = new UserVo();
			user.setName(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = userService.getPaginationUser(user,page,rows);
		String jsonAgency = JSON.toJSONString(pagination);
		
		response.getWriter().write(jsonAgency);
	}
	
	public void queryStore() throws IOException{
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
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String name = request.getParameter("name");
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String jurisdiction = request.getParameter("jurisdiction");
		String storeid = request.getParameter("storeid");
		SysUser su = new SysUser(name, loginname, password, email, jurisdiction, 0, Integer.parseInt(storeid));
		

		try {
			userService.addUser(su);
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
			userService.deleteUsers(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
	public void queryObject() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		
		UserVo user = userService.getUser(Integer.parseInt(id));
		
		String json = JSON.toJSONString(user);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String jurisdiction = request.getParameter("jurisdiction");
		String storeid = request.getParameter("storeid");
		SysUser su = new SysUser(Integer.parseInt(id),name, loginname, password, email, jurisdiction, 0, Integer.parseInt(storeid));
		
		try {
			userService.updateUser(su);
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
