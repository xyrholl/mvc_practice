package controller;

import dto.MemberDto;
import service.impl.MemberServiceImpl;
import serviec.MemberService;
import view.LoginView;
import view.SignView;

public class MemberController {

	MemberService memServ = new MemberServiceImpl();

	public void login() {
		new LoginView();
	}

	public void sign() {
		new SignView();
	}
	
	public MemberDto checkLogin(String id, String pw) {
		return memServ.loginCheck(id, pw);
	}
	
	public void addMember(MemberDto dto) {
		memServ.addMember(dto);
	}
	
	

}
