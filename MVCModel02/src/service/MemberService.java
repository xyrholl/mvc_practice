package service;


import dto.MemberDto;

public interface MemberService {
	
	public boolean getId(String id);

	public boolean addMember(MemberDto dto);
	
	public MemberDto loginCheck(String id, String pw);
	
}
