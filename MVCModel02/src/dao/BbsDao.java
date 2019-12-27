package dao;

import java.util.List;

import dto.BbsDto;

public interface BbsDao {
	
	public List<BbsDto> getBbsList();
	
	public void readCount(int seq);
	
	public BbsDto selectOne(int seq);
	
	public int delete(int seq);
	
	public boolean UpdateWritePage(BbsDto dto, int seq);
	
	public void AddWritePage(BbsDto dto);

}
