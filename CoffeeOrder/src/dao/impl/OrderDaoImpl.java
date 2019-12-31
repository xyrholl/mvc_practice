package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import db.DBClose;
import db.DBConnection;
import dto.OrderDto;
/*
CREATE TABLE ORDERTABLE (
	LOGIN_ID VARCHAR2(30),
	COFFEE_NAME VARCHAR2(200) NOT NULL,
	COFFEE_SIZE VARCHAR2(50) NOT NULL,
	COFFEE_EA VARCHAR(50),
	COFFEE_PRICE VARCHAR2(100),
	ORDER_DATE DATE,
	CONSTRAINT FK_MEMBERID FOREIGN KEY (LOGIN_ID) REFERENCES MEMBER (ID)
);
*/
public class OrderDaoImpl implements OrderDao {

	public void addOrder(List<OrderDto> orderList) {

		for (int i = 0; i < orderList.size(); i++) {
			OrderDto dto = orderList.get(i);

			String sql = " INSERT INTO ORDERTABLE( LOGIN_ID, COFFEE_NAME, COFFEE_SIZE, COFFEE_EA, COFFEE_PRICE, ORDER_DATE )"
					+ " VALUES ( ?, ?, ?, ?, ?, SYSDATE )";
			System.out.println(sql);

			Connection conn = null;
			PreparedStatement psmt = null;
			int rs = 0;

			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getLoginID());
				psmt.setString(2, dto.getCoffeeName());
				psmt.setString(3, dto.getSize());
				psmt.setString(4, dto.getEA());
				psmt.setString(5, dto.getPrice());

				System.out.println("sql:" + sql);
				rs = psmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose.close(psmt, conn, null);
			}
		}
	}

	public List<OrderDto> selectOne(String id) {
		List<OrderDto> list = new ArrayList<OrderDto>();

		String sql = " SELECT COFFEE_NAME, COFFEE_SIZE ,COFFEE_EA, COFFEE_PRICE, ORDER_DATE "
				+ " FROM ORDERTABLE " 
				+ " WHERE LOGIN_ID = ? "
				+ " ORDER BY ORDER_DATE DESC ";
		System.out.println(sql);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			System.out.println("sql:" + sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setCoffeeName(rs.getString(1));
				dto.setSize(rs.getString(2));
				dto.setEA(rs.getString(3));
				dto.setPrice(rs.getString(4));
				dto.setOrderDate(rs.getString(5));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return list;
	}

}
