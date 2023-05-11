package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datdocantin.Model.DiachiModel;
import datdocantin.Util.connectDB;

public class DiachiDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static DiachiModel getDiachi(Integer ID_nguoidung) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_diachi, ID_nguoidung, tinh, huyen, xa FROM diachi WHERE ID_nguoidung = ?;";
                stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_nguoidung); 
            	rs = stm.executeQuery();
                if (rs.next()) {
                	return new DiachiModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static void AddDiachi(DiachiModel diachi) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO diachi(ID_diachi, ID_nguoidung, tinh, huyen, xa) VALUES(?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, diachi.getID_diachi());
                stm.setInt(2, diachi.getID_nguoidung());
                stm.setInt(3, diachi.getTinh());
                stm.setInt(4, diachi.getHuyen());
                stm.setInt(5, diachi.getXa());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void ChangeAddress(DiachiModel diachi) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE diachi SET tinh=?, huyen=?, xa=? WHERE ID_nguoidung = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, diachi.getTinh());
                stm.setInt(2, diachi.getHuyen());
                stm.setInt(3, diachi.getXa());
                stm.setInt(4, diachi.getID_nguoidung());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) {
		try {
			
			ChangeAddress(new DiachiModel(null, 10002, 48, 490, 20197));
			DiachiModel dc = getDiachi(10002);
			System.out.println(dc.getHuyen());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
