package com.meijie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.interfaces.IMaterialDao;
import com.meijie.interfaces.IMaterialService;
import com.meijie.vo.MaterialVo;

@Service
public class MaterialServiceImpl implements IMaterialService {

	@Autowired
	private IMaterialDao materialDao;

	@Override
	public Pagination getPagination(MaterialVo mv, int page, int rows) throws Exception {
		String hql = "from Material";
		String[] param = null;
		//设置参数
		if(mv!=null){
			hql = "from Material where name like ?";
			param = new String[]{"%"+mv.getName()+"%"}; 
		}else{
			hql = "from Material";
			param = new String[]{};
		}
		Pagination pagination = materialDao.find(hql, param, page, rows);
		List<Material> list = pagination.getRows();
		List<MaterialVo> mList = new ArrayList<>();
		MaterialVo mvo = null;
		for(Material m : list) {
			mvo = new MaterialVo(m.getId(), m.getName(), m.getBrand(), m.getProductiondate(), m.getProducer(), m.getRemark());
		    mList.add(mvo);
		}
		pagination.setRows(mList);
		return pagination;
	}

	@Override
	public List<Material> findAllMaterial() {
		List<Material> list = materialDao.find("from Material");
		return list;
	}

	@Override
	public Material getMaterial(int index) {
		Material material = materialDao.get(Material.class, index);
		return material;
	}

	@Override
	public void addMaterial(Material material) {
		materialDao.save(material);
	}

	@Override
	public void updateMaterial(Material material) {
		materialDao.update(material);
	}

	@Override
	public void deleteMaterials(String ids) {
		materialDao.deleteBatch(ids, "Material");
	}
}
