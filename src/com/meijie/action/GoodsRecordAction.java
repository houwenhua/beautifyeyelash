package com.meijie.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meijie.entity.Goods;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.IGoodsRecordDetailService;
import com.meijie.interfaces.IGoodsRecordService;
import com.meijie.interfaces.IGoodsService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.GoodsRecordDetailVo;
import com.meijie.vo.GoodsRecordVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class GoodsRecordAction extends ActionSupport implements ParameterAware {

	private Map parameters; //接收参数
	
    public void setParameters(Map parameters) {
	    this.parameters = parameters;
	  }

	@Autowired
	private IGoodsRecordService goodsRecordService;
	
	@Autowired
	private IGoodsRecordDetailService goodsRecordDetailService;
	
	@Autowired
	private IGoodsService goodsService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		GoodsRecordVo grv = null;
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		if(startdate != null || enddate != null) {
			grv = new GoodsRecordVo();
			grv.setStartdate(startdate);
			grv.setEnddate(enddate);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = goodsRecordService.getPaginationGoodsRecord(grv,page,rows);
		
		String jsonWaiter = JSON.toJSONString(pagination);
		response.getWriter().write(jsonWaiter);
	}
	
	public void queryGoods() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		List<Goods> list = goodsService.getGoodsList();
		List tempList = new ArrayList();
		JSONObject obj = null;
		for(Goods g : list){
			obj = new JSONObject();
			obj.put("goodsid", g.getId());
			obj.put("goodsname", g.getName()+"--"+g.getBrand());
			obj.put("goodsprice", g.getGoutprivce());
			tempList.add(obj);
		}
		String json = JSON.toJSONString(tempList);
		response.getWriter().write(json);
	}
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String waiterid = request.getParameter("waiterid");
		JSONObject json = new JSONObject();
		String[] params1 = (String[]) parameters.get("grdv");//接收myJson参数
		
		String jsonObj = params1[0];
		
		List<GoodsRecordDetailVo> list = JSON.parseArray(jsonObj, GoodsRecordDetailVo.class);
		
		try{
			goodsRecordDetailService.saveGoodsRecordDetail(list,Integer.parseInt(waiterid));
			result.setSuccess(true);
			result.setMsg("添加成功");
		} catch (DataBasesException e){
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}
		String jsonObj1 = JSON.toJSONString(result);
		response.getWriter().write(jsonObj1);
	}
	
	public void remove() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String ids = request.getParameter("ids");
		try{
			goodsRecordService.deleteGoodsRecords(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("删除失败");
		} 
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
	public void goodsRecordDetail() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		List<GoodsRecordDetailVo> list = goodsRecordService.findAllGoodsRecordDetail(Integer.parseInt(id));
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
	public void querygoodsRecord() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		GoodsRecordVo list = goodsRecordService.findAllGoodsRecord(Integer.parseInt(id));
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String reallmoney = request.getParameter("reallmoney");
		String waiterid = request.getParameter("waiterid");
		
		try {
			goodsRecordService.updateGoodsRecord(Integer.parseInt(id),new BigDecimal(reallmoney),Integer.parseInt(waiterid));
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
