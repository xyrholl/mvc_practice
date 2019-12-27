package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import service.MemberService;

// controller와 dao의 중간에서 중계하는 역할.
public class MemberServiceImpl implements MemberService {
	
	MemberDao dao = new MemberDaoImpl();

	@Override
	public boolean getId(String id) {
		return dao.getId(id);
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return dao.addMember(dto);
	}

	@Override
	public MemberDto loginCheck(String id, String pw) {
		return dao.loginCheck(id, pw);
	}
	
	
}
