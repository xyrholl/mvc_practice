package controller;

import dto.MemberDto;
import service.MemberService;
import service.impl.MemberServiceImpl;
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
	public boolean checkId(String id) {
		return memServ.getId(id);
	}
	
	public MemberDto checkLogin(String id, String pw) {
		return memServ.loginCheck(id, pw);
	}
	
	public void addMember(MemberDto dto) {
		memServ.addMember(dto);
	}
	
	

}
