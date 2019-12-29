package controller;

import java.util.List;

import dto.BbsDto;
import service.BbsService;
import service.impl.BbsServiceImpl;
import view.BbsDetailOne;
import view.BbsDetailTwo;
import view.BbsListView;
import view.WriteAddPageView;

public class BbsController {
	
	BbsService bbsServ = new BbsServiceImpl();
	
	public void bbsView() {
		new BbsListView();
	}
	
	public void bbsDetailOne(int seqNum) {
		new BbsDetailOne(seqNum);
	}
	
	public void bbsDetailTwo(int seqNum) {
		new BbsDetailTwo(seqNum);
	}
	
	public void WriteAddPageView() {
		new WriteAddPageView();
	}
	
	public List<BbsDto> viewListSelect(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum) {
		return bbsServ.getBbsList(selectedIndex, text, rowEndNum, rowStartNum, rowSetNum);
	}
	
	public void readCount(int seq) {
		bbsServ.readCount(seq);
	}
	
	public BbsDto selectOne(int seq) {
		return bbsServ.selectOne(seq);
	}

	public void delete(int seqNum) {
		bbsServ.delete(seqNum);
	}
	
	public boolean UpdateWritePage(BbsDto dto, int seq) {
		return bbsServ.UpdateWritePage(dto, seq);
	}

	public void AddWritePage(BbsDto dto) {
		bbsServ.AddWritePage(dto);
		
	}
	
	public int rowNum20(int rowStartNum) {
		return bbsServ.rowNum20(rowStartNum);
	}
	
	public int rowNum10(int rowSetNum, int rowStartNum) {
		return bbsServ.rowNum10(rowSetNum, rowStartNum);
	}


	

}
