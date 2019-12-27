package service;

import java.util.List;

import dto.BbsDto;

public interface BbsService {
	
	public List<BbsDto> getBbsList();
	
	public void readCount(int seq);
	
	public BbsDto selectOne(int seq);
	
	public void delete(int seqNum);
	
	public boolean UpdateWritePage(BbsDto dto, int seq);
	
	public void AddWritePage(BbsDto dto);

}
