package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.LichsutimkiemModel;
import datdocantin.Util.connectDB;

public class HistorySearchDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public static List<LichsutimkiemModel> getSearchHistory(int ID_nguoidung) throws Exception {
        List<LichsutimkiemModel> historys = new ArrayList<LichsutimkiemModel>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT ID_lichsutimkiem, ID_nguoidung, noidung FROM lichsutimkiem WHERE ID_nguoidung = ? ORDER BY ID_lichsutimkiem DESC";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_nguoidung);
                rs = stm.executeQuery();
                while (rs.next() && (historys.size()<10)) {
                	boolean check = true;
                	for (LichsutimkiemModel lichsu : historys) {
						if (lichsu.getNoidung().equals(rs.getString(3))) {
							check = false;
						}
					}
                    if (check) {
                    	historys.add(new LichsutimkiemModel(rs.getInt(1),rs.getInt(2),rs.getString(3)));
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return historys;
    }

    public static void addSearchHistory(LichsutimkiemModel lichsutimkiem) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "DELETE FROM lichsutimkiem WHERE ID_nguoidung = ? AND noidung = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lichsutimkiem.getID_nguoidung());
                stm.setString(2, lichsutimkiem.getNoidung());
                stm.executeUpdate();   
                sql = "INSERT INTO lichsutimkiem(ID_lichsutimkiem, ID_nguoidung, noidung) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lichsutimkiem.getID_lichsutimkiem());
                stm.setInt(2, lichsutimkiem.getID_nguoidung());
                stm.setString(3, lichsutimkiem.getNoidung());
                stm.executeUpdate();  
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void deleteSearchHistory(int ID_lichsutimkiem, int ID_nguoidung) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM lichsutimkiem WHERE ID_lichsutimkiem = ? AND ID_nguoidung = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_lichsutimkiem);
                stm.setInt(1, ID_nguoidung);
                stm.executeUpdate();   
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static String getLastNoidung(int ID_nguoidung) throws SQLException, Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT noidung FROM lichsutimkiem WHERE ID_nguoidung = ? ORDER BY ID_lichsutimkiem DESC LIMIT 1;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_nguoidung);
                rs = stm.executeQuery();
                if (rs.next()) {
                	return rs.getString(1);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
  
	public static void main(String[] args) {
	    try {
	    	List<LichsutimkiemModel> historys = HistorySearchDAO.getSearchHistory(10001);
	    	for (LichsutimkiemModel lichsutimkiemModel : historys) {
	    		System.out.println(lichsutimkiemModel.getNoidung());
			}
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
