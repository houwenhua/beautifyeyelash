package com.meijie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.IGoodsClassService;
import com.meijie.vo.GoodsClassVo;
import com.meijie.vo.GoodsVo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class GoodsClassAction extends ActionSupport {

	@Autowired
	private IGoodsClassService goodsClassService;
	
	public void paginationQuery() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");

		String name = request.getParameter("name");
		
		GoodsClassVo gcv = null;
		if(name != null) {
			gcv = new GoodsClassVo();
			gcv.setName(name);
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Pagination pagination = goodsClassService.getPagination(gcv,page,rows);
		String json = JSON.toJSONString(pagination);
		response.getWriter().write(json);
	}
}
