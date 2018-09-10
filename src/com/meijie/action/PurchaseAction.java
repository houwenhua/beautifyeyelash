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
import com.meijie.entity.Pagination;
import com.meijie.entity.Service;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IPurchaseService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.ConsumeVo;
import com.meijie.vo.PurchaseVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class PurchaseAction extends ActionSupport {

	@Autowired
	private IPurchaseService purchaseService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String provider = request.getParameter("provider");
		String purchasename = request.getParameter("purchasename");
		
		PurchaseVo pv = null;
		if(provider != null || purchasename != null){
			pv = new PurchaseVo();
			pv.setProvider(provider);
			pv.setPurchasename(purchasename);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = purchaseService.getPaginationPurchase(pv,page,rows);
		
		String jsonWaiter = JSON.toJSONString(pagination);
		response.getWriter().write(jsonWaiter);
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String date = request.getParameter("date");
		String provider = request.getParameter("provider");
		String purchasename = request.getParameter("purchasename");
		String money = request.getParameter("money");
		String remark = request.getParameter("remark");
		
		PurchaseVo pv = new PurchaseVo(provider, purchasename, new BigDecimal(money), date, remark);
		
		try {
			purchaseService.addPurchase(pv);
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
			purchaseService.deletePurchases(ids);
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
		PurchaseVo purchaseVo = purchaseService.queryObjectById(Integer.parseInt(id));
		
		String json = JSON.toJSONString(purchaseVo);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String date = request.getParameter("date");
		String provider = request.getParameter("provider");
		String purchasename = request.getParameter("purchasename");
		String money = request.getParameter("money");
		String remark = request.getParameter("remark");
		PurchaseVo pv = new PurchaseVo(Integer.parseInt(id),provider, purchasename, new BigDecimal(money), date, remark);
		
		try {
			purchaseService.updatePurchase(pv);
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
