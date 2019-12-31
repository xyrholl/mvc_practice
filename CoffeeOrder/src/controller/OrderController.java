package controller;

import java.util.List;

import dto.OrderDto;
import service.impl.OrderServiceImpl;
import serviec.OrderService;
import view.OldOrderView;
import view.OrderView;

public class OrderController {
	
	OrderService ordServ = new OrderServiceImpl();
	
	public void orderView() {
		new OrderView();
	}
	
	public void oldOrderView() {
		new OldOrderView();
	}

	public void addOrder(List<OrderDto> orderList) {
		ordServ.addOrder(orderList);
		
	}
	
	public List<OrderDto> selectOne(String id){
		return ordServ.selectOne(id);
	}
	
	
	
	

}
