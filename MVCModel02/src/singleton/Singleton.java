package singleton;

import controller.BbsController;
import controller.MemberController;

public class Singleton {
	
	public int selectedIndex = -1;
	public String text = "";
	public int rowEndNum = 0;
	public int rowStartNum = 0;
	public int rowSetNum = 1;
	
	public MemberController memCtrl = null;
	public BbsController bbsCtrl = null;
	
	private String loginID = null;

	private static Singleton s = null;
	
	private Singleton() {
		memCtrl = new MemberController();
		bbsCtrl = new BbsController();
	}
	
	public static Singleton getInstance() {
		if ( s == null) {
			s = new Singleton();
		}
		return s;
	}
	
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}
