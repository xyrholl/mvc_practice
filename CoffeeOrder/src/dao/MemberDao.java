package dao;

import dto.MemberDto;

public interface MemberDao {
	
	public void addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public MemberDto loginCheck(String id, String pw);
	
}
