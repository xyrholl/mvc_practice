package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BbsDao;
import db.DBClose;
import db.DBConnection;
import dto.BbsDto;
import singleton.Singleton;

public class BbsDaoImpl implements BbsDao {
	
	public int selectedIndex = -1;
	public String text = "";
	public int rowEndNum = 0;
	public int rowStartNum = 0;
	public int rowSetNum = 1;
	
	public int rowNum10() {
		int rowNum10 = rowSetNum + rowStartNum * 10; // 1, 11, 21, 31, 41, 51
		if (rowStartNum < 0) {
			rowStartNum = 0;
			return rowNum10 = 1;
		}
		return rowNum10;
	}

	public int rowNum20() {
		int rowNum20 = 10 + rowStartNum * 10; // 10, 20, 30, 40, 50
		if (rowStartNum < 0) {
			rowStartNum = 0;
			return rowNum20 = 10;
		}
		return rowNum20;
	}
	
	@Override
	public List<BbsDto> getBbsList() {
		Singleton s = Singleton.getInstance();
		
		List<BbsDto> list = null;

		String sql = " SELECT SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT, RNUM, MAX(RNUM)OVER(PARTITION BY RNUM) "
				+ " FROM (SELECT ROWNUM AS RNUM, SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+ " FROM (SELECT SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT " + " FROM BBS ";

		if (s.selectedIndex == 0) { // 작성자검색
			sql = sql + " WHERE ID LIKE '%'||?||'%' AND DEL = 0 ";
		} else if (s.selectedIndex == 1) { // 제목검색
			sql = sql + " WHERE TITLE LIKE '%'||?||'%' AND DEL = 0 ";
		} else if (s.selectedIndex == 2) { // 내용검색
			sql = sql + " WHERE CONTENT LIKE '%'||?||'%' AND DEL = 0 ";
		} else {
			sql = sql + " WHERE DEL = 0 ";
		}
		sql = sql + " ORDER BY WDATE DESC)) " + " WHERE RNUM BETWEEN " + s.rowNum10() + " AND " + s.rowNum20() + " ";

		System.out.println("sql:" + sql);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		list = new ArrayList<BbsDto>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			if (s.selectedIndex == 0 || s.selectedIndex == 1 || s.selectedIndex == 2) {
				psmt.setString(1, s.text);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7));
				s.rowEndNum = Integer.parseInt(rs.getString(9));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	@Override
	public void readCount(int seq) {
		String sql = " UPDATE BBS " + " SET READCOUNT = READCOUNT + 1 " + " WHERE seq = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		int rs = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

	}
	
	@Override
	public BbsDto selectOne(int seq) {
		BbsDto dto = null;

		String sql = " SELECT SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT " + " FROM BBS "
				+ " WHERE SEQ = ? AND DEL = 0 ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto = new BbsDto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setId(rs.getString("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWdate(rs.getString("WDATE"));
				dto.setDel(rs.getInt("DEL"));
				dto.setReadcount(rs.getInt("READCOUNT"));
				System.out.println("sql:" + sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return dto;
	}
	
	@Override
	public int delete(int seq) {
		String sql = " UPDATE BBS SET DEL = 1 " + " WHERE SEQ = " + seq + " ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int rs = 0;

		try {

			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return rs;
	}
	
	@Override
	public boolean UpdateWritePage(BbsDto dto, int seq) {

		boolean check = false;

		if (!dto.getId().equals(selectOne(seq).getId())) {
			return check;
		}
		String sql = " UPDATE BBS " + " SET TITLE = '" + dto.getTitle() + "', CONTENT = '" + dto.getContent()
				+ "', WDATE = SYSDATE " + " WHERE seq = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		int rs = 0;

		System.out.println("sql:" + sql);

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeUpdate();

			check = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return check;
	}
	
	@Override
	public void AddWritePage(BbsDto dto) {
		String sql = " INSERT INTO BBS (SEQ, ID, TITLE, CONTENT, WDATE, DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, ?, ?, SYSDATE, 0, 0)";

		Connection conn = null;
		PreparedStatement psmt = null;
		int rs = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());

			System.out.println("sql:" + sql);
			rs = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}
	
}
