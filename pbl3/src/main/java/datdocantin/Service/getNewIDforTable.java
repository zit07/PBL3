package datdocantin.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datdocantin.Util.connectDB;

public class getNewIDforTable {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static String getNewID(String tableName) throws SQLException, Exception {
    	int result = 0;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT MAX(id) FROM " +tableName;
            	stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                  result = rs.getInt(1);
              }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return String.valueOf(result+1);
    }
    
    public static void main(String[] args) {
		try {
			System.out.println(getNewID("khachhang"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
