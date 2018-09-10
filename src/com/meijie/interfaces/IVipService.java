package com.meijie.interfaces;

import java.util.List;

import com.meijie.entity.Pagination;
import com.meijie.entity.Vip;

public interface IVipService {

	Pagination getPaginationAgency(Vip vip, int page, int rows) throws Exception;

	void addVip(Vip user);

	void deleteVips(String ids);

	Vip queryVipById(int i);

	void updateVip(Vip vip);
	
	List<Vip> findAll();

}
