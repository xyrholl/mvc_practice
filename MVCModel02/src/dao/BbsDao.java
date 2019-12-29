package dao;

import java.util.List;

import dto.BbsDto;

public interface BbsDao {
	
	public List<BbsDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum);
	
	public void readCount(int seq);
	
	public BbsDto selectOne(int seq);
	
	public int delete(int seq);
	
	public boolean UpdateWritePage(BbsDto dto, int seq);
	
	public void AddWritePage(BbsDto dto);

	public int rowNum20(int rowStartNum);
	
	public int rowNum10(int rowSetNum, int rowStartNum);
	
	public int getRowEndNum(int rowEndNum);
}
