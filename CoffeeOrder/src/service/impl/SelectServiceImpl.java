package service.impl;

import java.util.List;

import dao.SelectDao;
import dao.impl.SelectDaoImpl;
import dto.CoffeeDto;
import service.SelectService;

public class SelectServiceImpl implements SelectService {
	
	SelectDao dao = new SelectDaoImpl();

	@Override
	public List<CoffeeDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum) {
		return dao.getBbsList(selectedIndex, text, rowEndNum, rowStartNum, rowSetNum);
	}

	@Override
	public int rowNum10(int rowSetNum, int rowStartNum) {
		return dao.isPageStartRowNum(rowSetNum, rowStartNum);
	}

	@Override
	public int rowNum20(int rowStartNum) {
		return dao.isPageEndedRowNum(rowStartNum);
	}

	@Override
	public CoffeeDto selectOne(String name) {
		return dao.selectOne(name);
	}
	

}
