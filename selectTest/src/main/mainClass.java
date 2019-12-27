package main;

import java.util.List;

import db.DBConnection;
import dto.UserDto;
import jdbc.SelectTest;

public class mainClass {
	public static void main(String[] args) {
		DBConnection.initConnection();
		SelectTest st = new SelectTest();
		
		String id = "ccc";
		UserDto dto = st.search(id);
		if(dto != null) {
			System.out.println(dto.toString());
		}else {
			System.out.println("아이디를 찾을 수 없습니다.");
		}
		
		
		id = "aaa";
		UserDto dto1 = st.select(id);
		if(dto1 != null) {
			System.out.println(dto1.toString());
		}else {
			System.out.println("아이디를 찾을 수 없습니다.");
		}
		
		List<UserDto> list = st.getUsetList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
	}
}
