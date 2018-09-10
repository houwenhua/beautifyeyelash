package com.meijie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.ISalaryDetailService;
import com.meijie.vo.SalaryDetailVo;
import com.meijie.vo.SalaryVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SalaryDetailAction extends ActionSupport {

	@Autowired
	private ISalaryDetailService salaryDetailService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
		SalaryDetailVo sdv = null;
		if(name != null) {
			sdv = new SalaryDetailVo();
			sdv.setWaitername(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = salaryDetailService.getPaginationGoods(sdv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
}
