package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SelectDao;
import db.DBClose;
import db.DBConnection;
import dto.CoffeeDto;

/*
	CREATE TABLE COFFEE(
	SEQ NUMBER(8) PRIMARY KEY,
	COFFEE_NAME VARCHAR2(200) NOT NULL,
	SIZE_Short VARCHAR2(20),
	SIZE_Tall VARCHAR2(20),
	SIZE_Grande VARCHAR2(20)
);
 */

public class SelectDaoImpl implements SelectDao {

	public int isPageStartRowNum(int rowSetNum, int rowStartNum) {
		int isPageStartRowNum = rowSetNum + rowStartNum * 10; // 1, 11, 21, 31, 41, 51
		if (rowStartNum < 0) {
			rowStartNum = 0;
			return isPageStartRowNum = 1;
		}
		return isPageStartRowNum;
	}

	public int isPageEndedRowNum(int rowStartNum) {
		int isPageEndedRowNum = 10 + rowStartNum * 10; // 10, 20, 30, 40, 50
		if (rowStartNum < 0) {
			rowStartNum = 0;
			return isPageEndedRowNum = 10;
		}
		return isPageEndedRowNum;
	}

	public CoffeeDto selectOne(String name) {
		String sql = " SELECT * " + "FROM COFFEE " + "WHERE COFFEE_NAME = ? ";

		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SWL
		ResultSet rs = null; // result
		System.out.println("sql:" + sql);

		CoffeeDto findid = new CoffeeDto();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			rs = psmt.executeQuery();

			if (rs.next()) {
				findid.setSeq(Integer.parseInt(rs.getString(1)));
				findid.setName(rs.getString(2));
				findid.setSize_short(rs.getString(3));
				findid.setSize_tall(rs.getString(4));
				findid.setSize_grande(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return findid;
	}

	@Override
	public List<CoffeeDto> getBbsList(int selectedIndex, String text, int rowEndNum, int rowStartNum, int rowSetNum) {

		List<CoffeeDto> list = null;

		String sql = " SELECT SEQ, COFFEE_NAME, SIZE_Short, SIZE_Tall, SIZE_Grande, RNUM "
				+ " FROM (SELECT ROWNUM AS RNUM, SEQ, COFFEE_NAME, SIZE_Short, SIZE_Tall, SIZE_Grande "
				+ " FROM (SELECT SEQ, COFFEE_NAME, SIZE_Short, SIZE_Tall, SIZE_Grande " + " FROM COFFEE ";

		if (selectedIndex == 0) { //
			sql = sql + " WHERE COFFEE_NAME LIKE '%'||?||'%' ";
		} else if (selectedIndex == 1) {
			text = "아메리카노";
			sql = sql + " WHERE COFFEE_NAME LIKE '%'||?||'%' ";
		} else if (selectedIndex == 2) {
			text = "라떼";
			sql = sql + " WHERE COFFEE_NAME LIKE '%'||?||'%' ";
		} else if (selectedIndex == 3) {
			text = "모카";
			sql = sql + " WHERE COFFEE_NAME LIKE '%'||?||'%' ";
		}

		sql = sql + " ORDER BY SEQ DESC)) " + " WHERE RNUM BETWEEN " + isPageStartRowNum(rowSetNum, rowStartNum)
				+ " AND " + isPageEndedRowNum(rowStartNum) + " ";

		System.out.println("sql:" + sql);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		list = new ArrayList<CoffeeDto>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			if (selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 2 || selectedIndex == 3) {
				psmt.setString(1, text);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				CoffeeDto dto = new CoffeeDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), Integer.parseInt(rs.getString(6)));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

}
