package controller;

import dto.MemberDto;
import service.MemberService;
import service.impl.MemberServiceImpl;
import view.BbsListView;
import view.LoginView;
import view.SignView;

// service하고만 대화를 한다.
public class MemberController {
	MemberService memServ = new MemberServiceImpl();
	
	public void login() {
		new LoginView();
	}
	
	public void regi() {
		new SignView();
	}
	
	public void bbsListView() {
		new BbsListView();
	}

	public void refiAf(MemberDto dto) {
		memServ.addMember(dto);
	}
	
	public boolean idCheck(String id) {
		return memServ.getId(id);
	}
	
	public MemberDto loginCheck(String id, String pw) {
		return memServ.loginCheck(id, pw);
	}
}
