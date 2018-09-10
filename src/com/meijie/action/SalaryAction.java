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
import com.meijie.entity.Pagination;
import com.meijie.entity.Salary;
import com.meijie.entity.Service;
import com.meijie.entity.Vip;
import com.meijie.entity.Waiter;
import com.meijie.interfaces.ISalaryService;
import com.meijie.interfaces.IWaiterService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.meijie.vo.GoodsVo;
import com.meijie.vo.SalaryVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SalaryAction extends ActionSupport {

	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private IWaiterService waiterService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
		SalaryVo sv = null;
		if(name != null) {
			sv = new SalaryVo();
			sv.setWatiername(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = salaryService.getPaginationGoods(sv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
	
	public void querySomeMonthSalary() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		
		SalaryVo sv = null;
		if(name != null) {
			sv = new SalaryVo();
			sv.setWatiername(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = salaryService.getPaginationSalary(date,sv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
	
	
	public void add() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String waiterid = request.getParameter("waiterid");
		String minsalary = request.getParameter("minsalary");
		String reward = request.getParameter("reward");
		String summoney = request.getParameter("summoney");
		
		//Salary salarys = salaryService.findSalaryByWaiterId(Integer.parseInt(waiterid));
		
			Waiter waiter = waiterService.queryObjectById(Integer.parseInt(waiterid));
			Salary salary = new Salary(new BigDecimal(minsalary),new BigDecimal(reward),new BigDecimal(summoney),waiter);
			
			try {
				salaryService.addSalary(salary);
				result.setSuccess(true);
				result.setMsg("添加成功");
			} catch (DataBasesException e) {
				e.printStackTrace();
				result.setMsg("添加失败");
			}
	
		
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
	public void queryObject() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		SalaryVo sv = salaryService.getSalary(id);
		
		String json = JSON.toJSONString(sv);
		response.getWriter().write(json);
	}
	
	public void update() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		String id = request.getParameter("id");
		String waiterid = request.getParameter("waiterid");
		String minsalary = request.getParameter("minsalary");
		String reward = request.getParameter("reward");
		String summoney = request.getParameter("summoney");
		
		Waiter waiter = waiterService.queryObjectById(Integer.parseInt(waiterid));
		Salary salary = new Salary(Integer.parseInt(id),new BigDecimal(minsalary),new BigDecimal(reward),new BigDecimal(summoney),waiter);
		
		try {
			salaryService.updateSalary(salary);
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
			salaryService.deleteSalarys(ids);
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
