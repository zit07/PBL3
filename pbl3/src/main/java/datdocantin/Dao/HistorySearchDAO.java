package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Util.connectDB;

public class HistorySearchDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<String> getSearchHistory(String idkh) throws Exception {
        List<String> history = new ArrayList<String>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT noidung FROM lichsutimkiem WHERE idkh = ? ORDER BY id DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idkh);
                rs = stm.executeQuery();
                while (rs.next() && (history.size()<10)) {
                    if (!history.contains(rs.getString(1))) {
                    	history.add(rs.getString(1));
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return history;
    }
	
    public static int getLastId() throws Exception {
    	int result = 0;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM lichsutimkiem;";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result += 1;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return result;
    }

    public static void addSearchHistory(String id,String idkh, String noidung) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO lichsutimkiem(id, idkh, noidung) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, idkh);
                stm.setString(3, noidung);
                stm.executeUpdate();   
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }

  
	public static void main(String[] args) {
	    try {
//	    	if (CheckSearchNotExist("10002","dsadasd")) {
//	    		SearchHistoryDAO.addSearchHistory(String.valueOf(SearchHistoryDAO.getLastId()+1),"123", "123");
//			}
//	    	System.out.print(HistorySearchDAO.getResultSearchforCanteen("10003", "Banh My"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
