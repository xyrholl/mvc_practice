package service.impl;

import java.util.List;

import dao.BbsDao;
import dao.impl.BbsDaoImpl;
import dto.BbsDto;
import service.BbsService;

public class BbsServiceImpl implements BbsService {

	BbsDao dao = new BbsDaoImpl();

	@Override
	public int rowNum10(int rowSetNum, int rowStartNum) {
		return dao.rowNum10(rowSetNum, rowStartNum);
	}

	@Override
	public int rowNum20(int rowStartNum) {
		return dao.rowNum20(rowStartNum);
	}

	@Override
	public List<BbsDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum) {
		return dao.getBbsList(selectedIndex, text, rowEndNum, rowStartNum, rowSetNum);
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
	
	@Override
	public int getRowEndNum(int rowEndNum) {
		return dao.getRowEndNum(rowEndNum);
	}

}
