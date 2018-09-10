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
import com.meijie.entity.Goods;
import com.meijie.entity.Pagination;
import com.meijie.entity.Purchase;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Visitor;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.IGoodsService;
import com.meijie.interfaces.IPurchaseService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.GoodsVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class GoodsAction extends ActionSupport {

	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		GoodsVo gv = null;
		if(name != null) {
			gv = new GoodsVo();
			gv.setName(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = goodsService.getPaginationGoods(Integer.parseInt(id),gv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
	
	
	public void paginationQueryAll() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
		GoodsVo gv = null;
		if(name != null) {
			gv = new GoodsVo();
			gv.setName(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = goodsService.getPaginationGoodsAll(gv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
	
	
	public void queryPurchase() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		Purchase p = purchaseService.getPurchase(Integer.parseInt(id));
		String json = JSON.toJSONString(p);
		response.getWriter().write(json);
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String purid = request.getParameter("purid");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String ginprice = request.getParameter("ginprice");
		String goutprivce = request.getParameter("goutprivce");
		String productiondate = request.getParameter("productiondate");
		String productiontotal = request.getParameter("productiontotal");
		String supplier = request.getParameter("supplier");
		String storagetime = request.getParameter("storagetime");
		
		Purchase p = purchaseService.getPurchase(Integer.parseInt(purid));
		Goods goods = new Goods(name, new BigDecimal(ginprice), new BigDecimal(goutprivce), productiondate, new Long(productiontotal), supplier, storagetime,p,brand);
		
		try {
			goodsService.addGoods(goods);
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
			goodsService.deleteGoods(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
	public void queryGoods() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		GoodsVo gv = goodsService.getGoods(Integer.parseInt(id));
		String json = JSON.toJSONString(gv);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String purid = request.getParameter("purid");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String ginprice = request.getParameter("ginprice");
		String goutprivce = request.getParameter("goutprivce");
		String productiondate = request.getParameter("productiondate");
		String productiontotal = request.getParameter("productiontotal");
		String supplier = request.getParameter("supplier");
		String storagetime = request.getParameter("storagetime");
		
		Purchase purchase = purchaseService.getPurchase(Integer.parseInt(purid));
		
		Goods goods = new Goods(Integer.parseInt(id), name, new BigDecimal(ginprice), new BigDecimal(goutprivce), productiondate, new Long(productiontotal), supplier, storagetime, purchase,brand);
		
		try {
			goodsService.updateGoods(goods);
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
