package com.meijie.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.entity.Vip;
import com.meijie.interfaces.IConsumeService;
import com.meijie.interfaces.IVipService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.opensymphony.xwork2.ActionSupport;

public class VipAction extends ActionSupport {
	
	@Autowired
	private IConsumeService consumeService;

	private IVipService vipService;
	public IVipService getVipService() {
		return vipService;
	}
	public void setVipService(IVipService vipService) {
		this.vipService = vipService;
	}
	
	public String paginationQuery() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		Vip vip = null;
		if(name!=null){
			vip = new Vip();
			vip.setName(name);
		}
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Pagination pagination = vipService.getPaginationAgency(vip,page,rows);
		String jsonAgency = JSON.toJSONString(pagination);
		
		response.getWriter().write(jsonAgency);
		return NONE;
	}
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		Vip user = new Vip(name, phone, Integer.parseInt(age), sex, remark);
		

		try {
			vipService.addVip(user);
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
		String ids = request.getParameter("ids");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		/*String[] indexs = ids.split(",");
		for(int i = 0; i < indexs.length; i++) {
			int index = Integer.parseInt(indexs[i]);
			
			List<Consume> list = consumeService.findConsumesByVipId(index);
		}*/
		
		try{
			vipService.deleteVips(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	public String QueryVip() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		Vip vip = vipService.queryVipById(Integer.parseInt(id));
		String vipJson = JSON.toJSONString(vip);
		response.getWriter().write(vipJson);
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
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		Vip vip = new Vip(Integer.parseInt(id), name, phone, Integer.parseInt(age), sex, remark);
		
		try {
			vipService.updateVip(vip);
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
