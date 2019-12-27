package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;

public class UpdateTest {
	public boolean Update(String id, int age) {
		
		String sql = " UPDATE userdto SET AGE = '" + age +"' WHERE id = '" + id +"'";
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		
		int count  = 0;
		
		System.out.println("sql:" + sql);
		try {
			stmt = conn.prepareStatement(sql);
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(stmt, conn, null);
		}
		
		return count > 0? true:false; // 삼항연산자
	}
}
