package service;

import java.util.List;

import dto.CoffeeDto;

public interface SelectService {
	
	public int rowNum10(int rowSetNum, int rowStartNum);
	
	public int rowNum20(int rowStartNum);
	
	public CoffeeDto selectOne(String name);
	
	public List<CoffeeDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum);
	
	
}
