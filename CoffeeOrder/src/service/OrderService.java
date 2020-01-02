package service;

import java.util.List;

import dto.OrderDto;

public interface OrderService {

	public void addOrder(List<OrderDto> orderList);
	
	public List<OrderDto> selectOne(String id);

}
