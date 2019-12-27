package main;

import jdbc.JdbcTest;

public class mainClass {
	public static void main(String[] args) {

		JdbcTest jt = new JdbcTest();
		
		jt.getConnection();
		
	}
}
