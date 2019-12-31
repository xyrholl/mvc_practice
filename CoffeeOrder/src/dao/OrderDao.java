package dao;

import java.util.List;

import dto.OrderDto;

public interface OrderDao {

	public void addOrder(List<OrderDto> orderList);
	
	public List<OrderDto> selectOne(String id);

}
