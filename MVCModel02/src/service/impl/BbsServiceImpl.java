package service.impl;

import java.util.List;

import dao.BbsDao;
import dao.impl.BbsDaoImpl;
import dto.BbsDto;
import service.BbsService;

public class BbsServiceImpl implements BbsService {
	
	BbsDao dao = new BbsDaoImpl();

	@Override
	public List<BbsDto> getBbsList() {
		return dao.getBbsList();
	}

	@Override
	public void readCount(int seq) {
		dao.readCount(seq);
	}

	@Override
	public BbsDto selectOne(int seq) {
		return dao.selectOne(seq);
	}

	@Override
	public void delete(int seqNum) {
		dao.delete(seqNum);
	}
	
	@Override
	public boolean UpdateWritePage(BbsDto dto, int seq) {
		return dao.UpdateWritePage(dto, seq);
	}

	@Override
	public void AddWritePage(BbsDto dto) {
		dao.AddWritePage(dto);
	}
	
	

}
