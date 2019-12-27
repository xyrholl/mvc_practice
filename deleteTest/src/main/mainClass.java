package main;

import jdbc.JdbcTest;

public class mainClass {
	public static void main(String[] args) {
		JdbcTest jt = new JdbcTest();
		
		String id = "aaa";
		int count = jt.Delete(id);
		if(count > 0) {
			System.out.println("성공적으로 삭제하였습니다.");
		}
	}
}
