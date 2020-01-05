package singleton;

import java.util.ArrayList;
import java.util.List;

import controller.MemberController;
import controller.OrderController;
import controller.SelectController;
import dto.OrderDto;

public class Singleton {
	
	public static Singleton singleton = null;
	
	public MemberController memCtrl = null;
	public SelectController selectCtrl = null;
	public OrderController orderCtrl = null;
	
	private Singleton() {
		memCtrl = new MemberController();
		selectCtrl = new SelectController();
		orderCtrl = new OrderController();
	}
	
	public static Singleton getInstance() {
		if ( singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	private String loginID = null;
	
	public List<OrderDto> orderList = new ArrayList<OrderDto>();
	public List<OrderDto> oldOrderList = new ArrayList<OrderDto>();
	
	public int selectedIndex = -1;
	public String text = "";
	public int rowEndNum = 0;
	public int rowStartNum = 0;
	public int rowSetNum = 1;

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}