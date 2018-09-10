package com.meijie.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meijie.entity.Consume;
import com.meijie.entity.Goods;
import com.meijie.entity.GoodsRecordDetail;
import com.meijie.entity.Service;
import com.meijie.entity.Visitor;
import com.meijie.interfaces.IConsumeDao;
import com.meijie.interfaces.IGoodsDao;
import com.meijie.interfaces.IGoodsRecordDetailDao;
import com.meijie.interfaces.IServiceDao;
import com.meijie.interfaces.IStatisticsService;
import com.meijie.interfaces.IVisitorDao;
import com.meijie.vo.ServiceStatisticsVo;
import com.meijie.vo.ServiceStatisticsVo2;

public class StatisticsServiceImpl implements IStatisticsService {

	@Autowired
	private IConsumeDao consumeDao;

	@Autowired
	private IVisitorDao visitorDao;

	@Autowired
	private IServiceDao serviceDao;

	@Autowired
	private IGoodsDao goodsDao;

	@Autowired
	private IGoodsRecordDetailDao goodsRecordDetailDao;

	@Override
	public List<ServiceStatisticsVo> getAllServiceStatisticsList() {
		String hqlService = "from Service";
		String hqlConsume = "from Consume";
		String hqlVisitor = "from Visitor";
		List<Consume> consumeList = consumeDao.find(hqlConsume);
		List<Visitor> visitorList = visitorDao.find(hqlVisitor);
		List<Service> serviceList = serviceDao.find(hqlService);

		// 过滤serviceList中的重复对象
		for (int i = 0; i < serviceList.size() - 1; i++) {
			for (int j = serviceList.size() - 1; j > i; j--) {
				if (serviceList.get(j).getServicename().equals(serviceList.get(i).getServicename())) {
					serviceList.remove(j);
				}
			}
		}

		Integer number = consumeList.size() + visitorList.size();
		// 创建一个list集合用于存放ServiceStatisticsVo
		List<ServiceStatisticsVo> list = new ArrayList<>();

		for (Service ser : serviceList) {
			Integer sum = 0;
			// 获得会员的
			for (Consume con : consumeList) {
				// 获得服务名
				Service service = con.getService();
				if(service == null){
					continue;
				}
				String name = con.getService().getServicename();

				// 获得该服务的记录数
				if (ser.getServicename().equals(name)) {
					sum++;
				}
			}
			// 获得游客的
			for (Visitor v : visitorList) {
				// 获得服务名
				Service service = v.getService();
				if(service == null){
					continue;
				}
				String name = v.getService().getServicename();

				// 获得该服务的记录数
				if (ser.getServicename().equals(name)) {
					sum++;
				}
			}
			ServiceStatisticsVo ssv = new ServiceStatisticsVo(ser.getServicename(), this.countPercentage(sum, number),
					"#6f83a5");
			list.add(ssv);
		}
		return list;
	}

	/**
	 * 
	 * @param a
	 *            是分子
	 * @param b
	 *            是分母
	 * @return
	 */
	private BigDecimal countPercentage(Integer a, Integer b) {
		double numa = a * 1.0;
		double numb = b * 1.0;
		double result = (numa / numb) * 100;
		DecimalFormat df = new DecimalFormat("##.00");
		String numresult = df.format(result);
		BigDecimal big = new BigDecimal(numresult);
		return big;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ServiceStatisticsVo2> getNumberServiceStatisticsList() {
		String hqlService = "from Service";
		String hqlConsume = "from Consume";
		String hqlVisitor = "from Visitor";
		List<Consume> consumeList = consumeDao.find(hqlConsume);
		List<Visitor> visitorList = visitorDao.find(hqlVisitor);
		List<Service> serviceList = serviceDao.find(hqlService);

		// 过滤serviceList中的重复对象
		for (int i = 0; i < serviceList.size() - 1; i++) {
			for (int j = serviceList.size() - 1; j > i; j--) {
				if (serviceList.get(j).getServicename().equals(serviceList.get(i).getServicename())) {
					serviceList.remove(j);
				}
			}
		}

		// 创建一个list集合用于存放ServiceStatisticsVo
		List<ServiceStatisticsVo2> list = new ArrayList<>();

		/**
		 * 获得当期日期
		 * 
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String preDate = df.format(date);
		String[] index = preDate.split("-");
		Integer year = Integer.parseInt(index[0]);
		Integer month = Integer.parseInt(index[1]) - 1;

		for (Service ser : serviceList) {
			Integer sum = 0;
			// 获得会员的
			for (Consume con : consumeList) {
				// 获得服务名
				Service service = con.getService();
				if(service == null){
					continue;
				}
				String name = service.getServicename();
				
				String[] condate = con.getDate().split("-");
				// 获得该服务的记录数
				if (ser.getServicename().equals(name) && Integer.parseInt(condate[0]) == year
						&& Integer.parseInt(condate[1]) == month) {
					sum++;
				}
			}
			// 获得游客的
			for (Visitor v : visitorList) {
				// 获得服务名
				Service service = v.getService();
				if(service == null){
					continue;
				}
				String name = v.getService().getServicename();
				String[] vdate = v.getDate().split("-");
				// 获得该服务的记录数
				if (ser.getServicename().equals(name) && Integer.parseInt(vdate[0]) == year
						&& Integer.parseInt(vdate[1]) == month) {
					sum++;
				}
			}
			ServiceStatisticsVo2 ssv2 = new ServiceStatisticsVo2(ser.getServicename(), sum, "#6f83a5");
			list.add(ssv2);
		}
		return list;
	}

	@Override
	public List<ServiceStatisticsVo> getAllGoodsStatisticsList() {
		String hql = "from GoodsRecordDetail";
		String hqlg = "from Goods";
		List<GoodsRecordDetail> grlist = goodsRecordDetailDao.find(hql);
		List<Goods> glist = goodsDao.find(hqlg);

		// 过滤glist中的重复对象
		for (int i = 0; i < glist.size() - 1; i++) {
			for (int j = glist.size() - 1; j > i; j--) {
				if (glist.get(j).getName().equals(glist.get(i).getName())) {
					glist.remove(j);
				}
			}
		}

		// 创建一个list集合用于存放ServiceStatisticsVo
		List<ServiceStatisticsVo> list = new ArrayList<>();
		Integer number = 0;
		for (GoodsRecordDetail gr : grlist) {
			number += gr.getNumber();
		}

		for (Goods g : glist) {
			Integer sum = 0;
			// 获得会员的
			for (GoodsRecordDetail gr : grlist) {
				// 获得商品名
				String name = gr.getGoods().getName();

				// 获得该服务的记录数
				if (g.getName().equals(name)) {
					sum += gr.getNumber();
				}
			}
			ServiceStatisticsVo ssv = new ServiceStatisticsVo(g.getName(), this.countPercentage(sum, number),
					"#6f83a5");
			list.add(ssv);
		}
		return list;
	}

	@Override
	public List<ServiceStatisticsVo2> getNumberGoodsStatisticsList() {
		String hql = "from GoodsRecordDetail";
		String hqlg = "from Goods";
		List<GoodsRecordDetail> grlist = goodsRecordDetailDao.find(hql);
		List<Goods> glist = goodsDao.find(hqlg);

		// 过滤glist中的重复对象
		for (int i = 0; i < glist.size() - 1; i++) {
			for (int j = glist.size() - 1; j > i; j--) {
				if (glist.get(j).getName().equals(glist.get(i).getName())) {
					glist.remove(j);
				}
			}
		}

		// 创建一个list集合用于存放ServiceStatisticsVo
		List<ServiceStatisticsVo2> list = new ArrayList<>();

		/**
		 * 获得当期日期
		 * 
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String preDate = df.format(date);
		String[] index = preDate.split("-");
		Integer year = Integer.parseInt(index[0]);
		Integer month = Integer.parseInt(index[1]) - 1;

		for (Goods g : glist) {
			Integer sum = 0;
			// 获得会员的
			for (GoodsRecordDetail gr : grlist) {
				// 获得商品名
				String name = gr.getGoods().getName();
				String[] vdate = gr.getGrdate().split("-");
				// 获得该服务的记录数
				if (g.getName().equals(name) && Integer.parseInt(vdate[0]) == year
						&& Integer.parseInt(vdate[1]) == month) {
					sum += gr.getNumber();
				}
			}
			ServiceStatisticsVo2 ssv2 = new ServiceStatisticsVo2(g.getName(), sum, "#6f83a5");
			list.add(ssv2);
		}
		return list;
	}

}
