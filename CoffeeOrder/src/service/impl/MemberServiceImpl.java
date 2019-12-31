package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import serviec.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao dao = new MemberDaoImpl();

	@Override
	public void addMember(MemberDto dto) {
		dao.addMember(dto);
	}

	@Override
	public boolean getId(String id) {
		return dao.getId(id);
	}
	
	@Override
	public MemberDto loginCheck(String id, String pw) {
		return dao.loginCheck(id, pw);
	}

}
