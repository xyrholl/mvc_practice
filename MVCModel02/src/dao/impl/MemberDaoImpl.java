package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.MemberDao;
import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

//repository DB와 대화를 하는 클래스
public class MemberDaoImpl implements MemberDao {

	@Override
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

	@Override
	public boolean addMember(MemberDto dto) {
		String sql = " INSERT INTO MEMBER " + " VALUES('" + dto.getId() + "', '" + dto.getPwd() + "', '" + dto.getName()
				+ "', '" + dto.getEmail() + "', " + dto.getAuth() + ")";
		System.out.println("addMember sql:" + sql);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean emailCheck = false;
		if (dto.getEmail() == null) {
			emailCheck = false;
		} else {
			emailCheck = true;
		}
		try {
			if (!getId(dto.getId()) && emailCheck) {
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

	@Override
	public MemberDto loginCheck(String id, String pw) {
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
				} 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

}
