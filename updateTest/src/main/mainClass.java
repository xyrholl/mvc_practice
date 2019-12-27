package main;

import db.DBConnection;
import jdbc.UpdateTest;

public class mainClass {
	public static void main(String[] args) {
		
//		JdbcTest jt = new JdbcTest();
//		jt.Update("bbb", "홍길동", 15);
		
		DBConnection.initConnection();
		UpdateTest ut = new UpdateTest();
		boolean b = ut.Update("bbb", 20);
		if(b) {
			System.out.println("성공적으로 수정되었습니다.");
		}else {
			System.out.println("수정이 되지 않았습니다.");
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	}
}
