package com.meijie.interfaces;

import java.util.List;

import com.meijie.vo.ServiceStatisticsVo;
import com.meijie.vo.ServiceStatisticsVo2;

public interface IStatisticsService {

	List<ServiceStatisticsVo> getAllServiceStatisticsList();

	List<ServiceStatisticsVo2> getNumberServiceStatisticsList();

	List<ServiceStatisticsVo> getAllGoodsStatisticsList();

	List<ServiceStatisticsVo2> getNumberGoodsStatisticsList();

}
