package com.meijie.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.meijie.interfaces.IStatisticsService;
import com.meijie.vo.ServiceStatisticsVo;
import com.meijie.vo.ServiceStatisticsVo2;
import com.opensymphony.xwork2.ActionSupport;


public class StatisticsAction extends ActionSupport {

	@Autowired
	private IStatisticsService statisticsService;
	
	/**
	 * 查询出会员和游客的消费记录中的各服务类型的（服务名和数量）
	 * 封装成ServiceStatisticsVo对象
	 * @throws IOException 
	 */
	public void queryStat() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		List<ServiceStatisticsVo> list = statisticsService.getAllServiceStatisticsList();
		
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
	public void queryStatNumber() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		List<ServiceStatisticsVo2> list = statisticsService.getNumberServiceStatisticsList();
		
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
	
	public void queryGoodsAll() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		List<ServiceStatisticsVo> list = statisticsService.getAllGoodsStatisticsList();
		
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
	public void queryGoodsNumber() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		List<ServiceStatisticsVo2> list = statisticsService.getNumberGoodsStatisticsList();
		
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
	
}
