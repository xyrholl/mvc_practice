package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public JdbcTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클 드라이버 클래스명
			System.out.println("Driver Loading Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int Insert(String id, String name, int age) {
		// createStatement, preperedStatement
		String sql = "INSERT INTO userdto (ID, NAME, AGE, JOINDATE)" + "VALUES('" + id + "','" + name + "'," + age
				+ ", SYSDATE)";

		Connection conn = this.getConnection();
		Statement stmt = null;
		System.out.println("성공적으로 추가 되었습니다.");

		int count = 0; // 데이터가 몇개 변경되었는지 확인 변수
		System.out.println("sql:" + sql);

		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 이클립스는 자동으로 커밋이 되므로, 따로 해줄 필요가 없다.
		return count;
	}

}
