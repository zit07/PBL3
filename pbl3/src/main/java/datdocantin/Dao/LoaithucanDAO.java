package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.LoaithucanModel;
import datdocantin.Util.connectDB;

public class LoaithucanDAO {

	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<LoaithucanModel> getListLoaithucan(int ID_canteen) throws Exception {
    	List<LoaithucanModel> result = new ArrayList<LoaithucanModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_loaithucan, loaithucan FROM loaithucan WHERE ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery(); 
                while (rs.next()) {
                	result.add(new LoaithucanModel(rs.getInt(1), ID_canteen, rs.getString(2)));
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static void addLoaithucan(LoaithucanModel loaithucan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO loaithucan(ID_loaithucan, ID_canteen, loaithucan) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, loaithucan.getID_loaithucan());
                stm.setInt(2, loaithucan.getID_canteen());
                stm.setString(3, loaithucan.getLoaithucan());
                stm.executeUpdate();  
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean CheckLoaithucanNotExist(int ID_canteen, String loaithucan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_loaithucan FROM loaithucan WHERE ID_canteen = ? AND loaithucan = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	stm.setString(2, loaithucan);
            	rs = stm.executeQuery();
                if (rs.next()) {
                    return false;
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return true;
    }
    
    public static void xoaLoaithucan(int ID_loaithucan, int ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM loaithucan WHERE ID_loaithucan = ? AND ID_canteen = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_loaithucan);
                stm.setInt(2, ID_canteen);
                stm.executeUpdate();   
            }
        } catch (Exception e) {
        	e.printStackTrace(); // in ra thông tin lỗi
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) throws Exception {
    	List<LoaithucanModel> result = getListLoaithucan(10002);
    	for (LoaithucanModel loaithucanModel : result) {
			System.out.println(loaithucanModel.getLoaithucan());
		}
	}
}
