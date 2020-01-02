package controller;

import java.util.List;

import dto.CoffeeDto;
import service.SelectService;
import service.impl.SelectServiceImpl;
import view.MenuView;
import view.SelectView;

public class SelectController {

	SelectService selectServ = new SelectServiceImpl();
	
	public void menu() {
		new MenuView();
	}

	public void selectMenu(Object obj, CoffeeDto dto) {
		new SelectView(obj, dto);
	}
	
	public CoffeeDto selectOne(String name) {
		return selectServ.selectOne(name);
	}
	
	public List<CoffeeDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum) {
		return selectServ.getBbsList(selectedIndex, text, rowEndNum, rowStartNum, rowSetNum);
	}
	
	public int rowNum20(int rowStartNum) {
		return selectServ.rowNum20(rowStartNum);
	}
	
	public int rowNum10(int rowSetNum, int rowStartNum) {
		return selectServ.rowNum10(rowSetNum, rowStartNum);
	}
	
	
}
