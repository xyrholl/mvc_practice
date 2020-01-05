package dao;

import java.util.List;

import dto.CoffeeDto;

public interface SelectDao {
	
	public int isPageStartRowNum(int rowSetNum, int rowStartNum);
	
	public int isPageEndedRowNum(int rowStartNum);
	
	public CoffeeDto selectOne(String name);

	public List<CoffeeDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum);
	

}
