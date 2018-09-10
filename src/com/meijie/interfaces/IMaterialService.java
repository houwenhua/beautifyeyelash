package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Material;
import com.meijie.entity.Pagination;
import com.meijie.vo.MaterialVo;

public interface IMaterialService {

	Pagination getPagination(MaterialVo mv, int page, int rows) throws Exception;

	List<Material> findAllMaterial();

	Material getMaterial(int index);

	void addMaterial(Material material);

	void updateMaterial(Material material);

	void deleteMaterials(String ids);

}
