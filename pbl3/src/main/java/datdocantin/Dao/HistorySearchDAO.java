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

    public static List<LichsutimkiemModel> getSearchHistory(String idkh) throws Exception {
        List<LichsutimkiemModel> historys = new ArrayList<LichsutimkiemModel>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT id, idkh, noidung FROM lichsutimkiem WHERE idkh = ? ORDER BY id DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idkh);
                rs = stm.executeQuery();
                while (rs.next() && (historys.size()<10)) {
                	boolean check = true;
                	for (LichsutimkiemModel ls : historys) {
						if (ls.getNoidung().equals(rs.getString(3))) {
							check = false;
						}
					}
                    if (check) {
                    	historys.add(new LichsutimkiemModel(rs.getString(1),rs.getString(2),rs.getString(3)));
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return historys;
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
            	String sql = "DELETE FROM lichsutimkiem WHERE idkh = ? AND noidung = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idkh);
                stm.setString(2, noidung);
                stm.executeUpdate();   
                sql = "INSERT INTO lichsutimkiem(id, idkh, noidung) VALUES(?, ?, ?);";
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
    
    public static void deleteSearchHistory(String id) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM lichsutimkiem WHERE id = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.executeUpdate();   
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static String getLastNoidung(String idkh) throws SQLException, Exception {
    	String result = null;
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT noidung FROM lichsutimkiem WHERE idkh = ? ORDER BY id DESC LIMIT 1;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idkh);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString(1);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
  
	public static void main(String[] args) {
	    try {
//	    	if (CheckSearchNotExist("10002","dsadasd")) {
//	    		SearchHistoryDAO.addSearchHistory(String.valueOf(SearchHistoryDAO.getLastId()+1),"123", "123");
//			}
//	    	List<LichsutimkiemModel> historys = HistorySearchDAO.getSearchHistory("10001");
	    	System.out.print(HistorySearchDAO.getLastNoidung("10001"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
