package service.impl;

import java.util.List;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import dto.OrderDto;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	OrderDao ordDao = new OrderDaoImpl();
	
	@Override
	public void addOrder(List<OrderDto> orderList) {
		ordDao.addOrder(orderList);
	}

	@Override
	public List<OrderDto> selectOne(String id) {
		return ordDao.selectOne(id);
	}

}
