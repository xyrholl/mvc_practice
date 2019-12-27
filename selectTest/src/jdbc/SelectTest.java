package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.UserDto;

public class SelectTest {

	public SelectTest() {

		// 1개의 데이터(row)만을 취득

		// 데이터 전부를 취득
		//
	}

	public UserDto search(String id) {

		String sql = " SELECT ID, NAME, AGE, JOINDATE " + " FROM USERDTO " + " WHERE ID = '" + id + "'";

		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserDto dto = null;
		System.out.println("sql:" + sql); // 확인작업 무조건 해야함.

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // select에서는 executeQuery로 사용함.

			if (rs.next()) { // 데이터가 있는경우
				String _id = rs.getString("id");
				String _name = rs.getString("name");
				int _age = rs.getInt("age");
				String _joindate = rs.getString("joindate"); // column명과 일치해야함.

				dto = new UserDto(_id, _name, _age, _joindate); // 실질적으로 데이터를 받아오는 곳.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	
	//앞으로 사용할 preparestatement
	
	public UserDto select(String id) {
		String sql = " SELECT ID, NAME, AGE, JOINDATE " + "FROM userdto " + " WHERE ID = ? ";
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserDto dto = null;
		System.out.println("sql:" + sql);
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id); // String sql첫번째 와일드카드, 매개변수로 받아온 id값
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDto();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setJoindate(rs.getString("joindate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	
	// 데이터 다수의 데이터 취득
	
	public List<UserDto> getUsetList(){
		String sql = " SELECT ID, NAME, AGE, JOINDATE " + " FROM USERDTO ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<UserDto> list = new ArrayList<UserDto>();
		System.out.println("sql:" + sql); // 위에서 선언
		
		try { // 아래에서 처리
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String joindate = rs.getString("joindate");
				
				list.add(new UserDto(id, name, age, joindate));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	

}
