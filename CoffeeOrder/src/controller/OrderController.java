package controller;

import java.util.List;

import dto.CoffeeDto;
import dto.OrderDto;
import service.impl.OrderServiceImpl;
import serviec.OrderService;
import singleton.Singleton;
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

	public void orderAdd(Object objDto, CoffeeDto dto, String name, int selectedIndex, int selectedIndex2, int selectedIndex3, String text) {
		
		String sizePrice_short ="";
		String sizePrice_tall ="";
		String sizePrice_grande ="";
		
		Singleton singleton = Singleton.getInstance();
		OrderDto orderDto = new OrderDto();
		
		name = dto.getName();
		sizePrice_short = dto.getSize_short();
		sizePrice_tall = dto.getSize_tall();
		sizePrice_grande = dto.getSize_grande();
		
		if (objDto != null) {
			if (objDto instanceof CoffeeDto) {
				name = ((CoffeeDto)objDto).getName();
				sizePrice_short = ((CoffeeDto)objDto).getSize_short();
				sizePrice_tall = ((CoffeeDto)objDto).getSize_tall();
				sizePrice_grande = ((CoffeeDto)objDto).getSize_grande();
			}
		}
		
		orderDto.setLoginID(singleton.getLoginID());
		orderDto.setCoffeeName(name);
		if (selectedIndex == 0) {
			orderDto.setPrice(sizePrice_short);
			orderDto.setSize("Short");
		} else if (selectedIndex == 1) {
			orderDto.setPrice(sizePrice_tall);
			orderDto.setSize("Tall");
		} else if (selectedIndex == 2) {
			orderDto.setPrice(sizePrice_grande);
			orderDto.setSize("Grande");
		}
		System.out.println(orderDto.getPrice());
		
		int price = Integer.parseInt(orderDto.getPrice());

		if (selectedIndex2 == 0) {
			orderDto.setSyrupAdd("추가안함");
		} else if (selectedIndex2 == 1) {
			orderDto.setSyrupAdd("바닐라 시럽");
			orderDto.setPrice(Integer.toString((price + 500)));
		} else if (selectedIndex2 == 2) {
			orderDto.setSyrupAdd("카라멜 시럽");
			orderDto.setPrice(Integer.toString((price + 500)));
		} else if (selectedIndex2 == 3) {
			orderDto.setSyrupAdd("헤이즐넛 시럽");
			orderDto.setPrice(Integer.toString((price + 500)));
		}

		if (selectedIndex3 == 0) {
			orderDto.setOtherAdd("추가안함");
		} else if (selectedIndex3 == 1) {
			orderDto.setOtherAdd("샷추가");
			orderDto.setPrice(Integer.toString((price + 500)));
		} else if (selectedIndex3 == 2) {
			orderDto.setOtherAdd("휘핑크림 추가");
			orderDto.setPrice(Integer.toString((price + 500)));
		}
		orderDto.setEA(text.trim());
		int total_price = price*Integer.parseInt(text);
		orderDto.setPrice(Integer.toString(total_price));
		
		for (int i = 0; i < singleton.orderList.size(); i++) {
			if (singleton.orderList.get(i).getCoffeeName().equals(name)
					&& !singleton.orderList.get(i).getPrice().equals(Integer.toString(total_price))) {
				singleton.orderList.remove(i);
			} else if(singleton.orderList.get(i).getCoffeeName().equals(name) || singleton.orderList.get(i).getPrice().equals(Integer.toString(total_price))) {
				singleton.orderList.remove(i);
			}
		}
		singleton.orderList.add(orderDto);
		
	}
	
	
	
	

}
