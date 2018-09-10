package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Goods;
import com.meijie.entity.GoodsClass;
import com.meijie.entity.Pagination;
import com.meijie.entity.Purchase;
import com.meijie.interfaces.IGoodsClassDao;
import com.meijie.interfaces.IGoodsClassService;
import com.meijie.vo.GoodsClassVo;
import com.meijie.vo.GoodsVo;

@Service
public class GoodsClassServiceImpl implements IGoodsClassService {

	@Autowired
	private IGoodsClassDao goodsClassDao;

	@Override
	public Pagination getPagination(GoodsClassVo gcv, int page, int rows) throws Exception {
		String hql = null;
		String[] param = null;
		if(gcv != null && StringUtils.isNotBlank(gcv.getName())) {
			hql = "from GoodsClass where name like ?";
			param = new String[]{"%"+gcv.getName()+"%"};
		} else {
			hql = "from GoodsClass";
			param = new String[]{};
		}
		Pagination pagination = goodsClassDao.find(hql, param, page, rows);
		
		List<GoodsClass> gcList = pagination.getRows();
		List<GoodsClassVo> gcvList = new ArrayList();
		GoodsClassVo gvo = null;
		for(GoodsClass g : gcList) {
			gvo = new GoodsClassVo(g.getId(), g.getName(), g.getBrand(), g.getNumber());
			gcvList.add(gvo);
		}
		pagination.setRows(gcvList);
		return pagination;
	}
}
