package dto;

public class OrderDto {
	
	private String loginID;
	private String coffeeName;
	private String otherAdd;
	private String syrupAdd;
	private String size;
	private String EA;
	private String price;
	private String orderDate;
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCoffeeName() {
		return coffeeName;
	}
	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getOtherAdd() {
		return otherAdd;
	}
	public void setOtherAdd(String otherAdd) {
		this.otherAdd = otherAdd;
	}
	public String getSyrupAdd() {
		return syrupAdd;
	}
	public void setSyrupAdd(String syrupAdd) {
		this.syrupAdd = syrupAdd;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getEA() {
		return EA;
	}
	public void setEA(String eA) {
		EA = eA;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "OrderDto [loginID=" + loginID + ", otherAdd=" + otherAdd + ", syrupAdd=" + syrupAdd + ", size=" + size
				+ ", EA=" + EA + ", price=" + price + "]";
	}
	
	
	

}
