package com.meijie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.meijie.entity.Consume;
import com.meijie.entity.Pagination;
import com.meijie.entity.Vip;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.interfaces.IVipDao;
import com.meijie.interfaces.IVipService;
import com.meijie.vo.VipVo;

@Transactional
public class VipServiceImpl implements IVipService {
	
	@Autowired
	private IConsumeDao consumeDao;

	private IVipDao vipDao;
	public IVipDao getVipDao() {
		return vipDao;
	}
	public void setVipDao(IVipDao vipDao) {
		this.vipDao = vipDao;
	}
	@Override
	public Pagination getPaginationAgency(Vip vip, int page, int rows) throws Exception {
		String hql = "from Vip";
		String[] param = null;
		//设置参数
		if(vip!=null){
			hql = "from Vip where name like ?";
			param = new String[]{"%"+vip.getName()+"%"}; 
		}else{
			hql = "from Vip";
			param = new String[]{};
		}
		Pagination pagination = vipDao.find(hql, param, page, rows);
		
		List<Vip> list = pagination.getRows();
		List<VipVo> listVo = new ArrayList();
		VipVo vipVo = null;
		for(Vip v : list) {
			Set<Consume> set = v.getConsumes();
			Integer sumIntegral = 0;
			for(Consume consume : set) {
				sumIntegral += Integer.parseInt(consume.getIntegral());
			}
			
			vipVo = new VipVo(v.getId(), v.getName(), v.getPhone(), v.getAge(), v.getSex(), v.getRemark(), sumIntegral.toString());
			listVo.add(vipVo);
		}
		pagination.setRows(listVo);
		return pagination;
	}
	@Override
	public void addVip(Vip user) {
		vipDao.save(user);
	}
	@Override
	public void deleteVips(String ids) {
        //consumeDao.deleteBatchByVipid(ids, "Consume");
		vipDao.deleteBatch(ids,"Vip");
	}
	@Override
	public Vip queryVipById(int id) {
		Vip vip = vipDao.get(Vip.class, id);
		return vip;
	}
	@Override
	public void updateVip(Vip vip) {
		Vip temp = vipDao.get(Vip.class, vip.getId());
		temp.setName(vip.getName());
		temp.setAge(vip.getAge());
		temp.setSex(vip.getSex());
		temp.setPhone(vip.getPhone());
		temp.setRemark(vip.getRemark());
		vipDao.update(temp);
	}
	
	public List<Vip> findAll(){
		String hql = "from Vip";
		return vipDao.find(hql);
	}
	
}
