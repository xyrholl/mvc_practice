package service;

import dto.MemberDto;

public interface MemberService {
	
	public void addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public MemberDto loginCheck(String id, String pw);
}
