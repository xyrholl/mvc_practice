package dao;

import dto.MemberDto;

public interface MemberDao {
	
	public boolean getId(String id);
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto loginCheck(String id, String pw);
}
