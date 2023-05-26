package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.HoadonModel;
import datdocantin.Model.HoadonchitietModel;
import datdocantin.Util.connectDB;

public class HoadonchitietDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static void addHoadonchitiet(HoadonchitietModel hoadonchitiet) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO hoadonchitiet(ID_hoadonchitiet, ID_hoadon, ID_monan, tenmon, soluong, gia) VALUES(?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, hoadonchitiet.getID_hoadonchitiet());
                stm.setInt(2, hoadonchitiet.getID_hoadon());
                stm.setInt(3, hoadonchitiet.getID_monan());
                stm.setString(4, hoadonchitiet.getTenmon());
                stm.setInt(5, hoadonchitiet.getSoluong());
                stm.setDouble(6, hoadonchitiet.getGia());
                stm.executeUpdate();
            } 
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    
    public static List<HoadonchitietModel> getHoadonchitiet(int ID_hoadon) throws Exception{
    	List<HoadonchitietModel> result = new ArrayList<HoadonchitietModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_hoadonchitiet, ID_monan, tenmon, soluong, gia FROM hoadonchitiet WHERE ID_hoadon = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadon);
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add( new HoadonchitietModel(rs.getInt(1), ID_hoadon, rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    public static List<List<HoadonchitietModel>> getHDCTchonhieuHD(List<HoadonModel> hoadons) throws Exception{
    	List<List<HoadonchitietModel>> result = new ArrayList<List<HoadonchitietModel>>();
    	for (HoadonModel hoadon : hoadons) {
			result.add(getHoadonchitiet(hoadon.getID_hoadon()));
		}
    	return result;
    }
    
    public static double getTongtien(int ID_hoadon) throws Exception{
    	double tongtien = 0;
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT soluong, gia FROM hoadonchitiet WHERE ID_hoadon = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_hoadon);
	    		rs = stm.executeQuery();
	    		while (rs.next()) {
	    			tongtien += rs.getInt(1) * rs.getDouble(2);
	    		}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return tongtien;
    }
    
    public static Integer GetID(int ID_hoadon, int ID_monan) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT ID_hoadonchitiet FROM hoadonchitiet WHERE ID_hoadon = ? AND ID_monan = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_hoadon);
	    		stm.setInt(2, ID_monan);
	    		rs = stm.executeQuery();
	    		if (rs.next()) {
	    			return rs.getInt(1);
	    		}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static void ChangeSoluong(Integer ID_hoadonchitiet, int soluong) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
            	String sql = "SELECT soluong FROM hoadonchitiet WHERE ID_hoadonchitiet = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_hoadonchitiet);
	    		rs = stm.executeQuery();
	    		if (rs.next()) {
	    			soluong += rs.getInt(1);
	    		}
                sql = "UPDATE hoadonchitiet SET soluong = ? WHERE ID_hoadonchitiet=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, soluong);
                stm.setInt(2, ID_hoadonchitiet);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean CheckHDchitiet(Integer ID_hoadonchitiet, int ID_hoadon) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan FROM hoadonchitiet WHERE ID_hoadonchitiet = ? AND ID_hoadon = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadonchitiet);
                stm.setInt(2, ID_hoadon);
                rs = stm.executeQuery();
	    		if (rs.next()) {
	    			return true;
	    		}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
		return false;
    }
    
    public static boolean ChecksoluongHDCT(int ID_hoadon) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan FROM hoadonchitiet WHERE ID_hoadon = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadon);
                rs = stm.executeQuery();
	    		if (rs.next()) {
	    			return true;
	    		}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
		return false;
    }
    
    public static void TangGiamSoluong(int ID_hoadonchitiet, String type) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
            	int soluong = 0;
            	String sql = "SELECT soluong FROM hoadonchitiet WHERE ID_hoadonchitiet = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_hoadonchitiet);
	    		rs = stm.executeQuery();
	    		if (rs.next()) {
	    			soluong = rs.getInt(1);
	    		}
	    		soluong += type.equals("tang") ? 1 : -1;
	    		sql = "UPDATE hoadonchitiet SET soluong = ? WHERE ID_hoadonchitiet = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, soluong);
                stm.setInt(2, ID_hoadonchitiet);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void XoaHDChitiet(Integer ID_hoadonchitiet) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "DELETE FROM hoadonchitiet WHERE ID_hoadonchitiet = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadonchitiet);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    
    public static void main(String[] args) throws Exception {
		System.out.println(GetID(1, 1));
//		ChangeSoluong(2,2);
		TangGiamSoluong(2, "giam");
	}
}
