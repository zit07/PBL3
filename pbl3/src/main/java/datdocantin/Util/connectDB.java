package datdocantin.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connectDB {
	public static Connection getConnection() {
		final String url = "jdbc:mysql://localhost:3306/pbl";
		final String user = "root";
		final String pass = "ahihi123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				return DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				throw new RuntimeException("Cannot connect to database", e);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        } 
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
	public static void main(String[] args) {
        Connection conn = connectDB.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
        }
    }
}