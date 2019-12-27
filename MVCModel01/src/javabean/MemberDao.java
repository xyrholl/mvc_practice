package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

// DB와 connection 하는곳
// DB <-> Java

// Spring은 모델2로 되어있음. 지금은 모델1
// 모델1에서는 dao와 controller 가 합쳐저있는 형태
// 모델2에서는 main 에서 controller를 거쳐서 view로 가게됨.
// 

/*
CREATE TABLE MEMBER(
ID VARCHAR2(30) PRIMARY KEY,
PWD VARCHAR2(30) NOT NULL,
NAME VARCHAR2(50) NOT NULL,
EMAIL VARCHAR2(50) UNIQUE,
AUTH NUMBER(1) NOT NULL);
*/
public class MemberDao {

	private static MemberDao mem = null;

	private String loginID;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (mem == null) {
			mem = new MemberDao();
		}
		return mem;
	}

	public boolean getId(String id) {
		String sql = " SELECT ID " + "FROM MEMBER " + "WHERE ID = ? ";

		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SWL
		ResultSet rs = null; // result
		System.out.println("sql:" + sql);

		boolean findid = false;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				findid = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return findid;
	}

	public boolean addMember(MemberDto dto) {
		// db에 추가

		String sql = " INSERT INTO MEMBER " + " VALUES('" + dto.getId() + "', '" + dto.getPwd() + "', '" + dto.getName()
				+ "', '" + dto.getEmail() + "', " + dto.getAuth() + ")";
		System.out.println("addMember sql:" + sql);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDao dao = MemberDao.getInstance();
		boolean emailCheck = false;
		if (dto.getEmail() == null) {
			emailCheck = false;
		} else {
			emailCheck = true;
		}
		try {
			if (!dao.getId(dto.getId()) && emailCheck) {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
				System.out.println("회원추가에 성공하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return emailCheck;
	}

	public MemberDto login(String id, String pw) {
		String sql = " SELECT ID, PWD, NAME, EMAIL, AUTH " + " FROM MEMBER " + " WHERE ID = ? ";

		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SWL
		ResultSet rs = null; // result
		MemberDto dto = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			System.out.println("sql:" + sql);
			if (rs.next()) {
				if (rs.getString(1).equals(id) && rs.getString(2).equals(pw)) {
					dto = new MemberDto();
					dto.setId(rs.getString("ID"));
					dto.setPwd(rs.getString("PWD"));
					dto.setName(rs.getString("NAME"));
					dto.setEmail(rs.getString("EMAIL"));
					dto.setAuth(rs.getInt("auth"));
					loginID = dto.getId();
				} 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}
