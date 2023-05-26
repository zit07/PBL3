package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import datdocantin.Model.MonAnModel;
import datdocantin.Util.connectDB;

public class MonAnDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<MonAnModel> CanteenGetMenu(Integer ID_canteen, String tukhoa) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
    	if (tukhoa!=null) tukhoa = Normalizer.normalize(tukhoa.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, ID_loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban, xoa FROM monan WHERE ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	LocalDate ngaytao = rs.getDate(9).toLocalDate(); 
                	if (tukhoa==null) {
                    	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), ngaytao, Base64.getDecoder().decode(rs.getBytes(10)), rs.getInt(11),rs.getInt(12),rs.getInt(13)));
                	} else {
                		String t = Normalizer.normalize((rs.getString(2)+rs.getString(4)+rs.getString(6)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
                		if (t.contains(tukhoa)) {
                        	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), ngaytao, Base64.getDecoder().decode(rs.getBytes(10)), rs.getInt(11),rs.getInt(12),rs.getInt(13)));
                        }
                	}
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static List<MonAnModel> getListMonanByTag(Integer ID_canteen, String tag) throws SQLException, Exception {
    	List<MonAnModel> ListMonan = CanteenGetMenu(ID_canteen, null); 
    	List<MonAnModel> result = new ArrayList<MonAnModel>();
			if (tag.equals("tatca")) {
				for (MonAnModel monan : ListMonan) {
					if (monan.getXoa() == 0) result.add(monan);
				}
			} else if (tag.equals("dangban")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getTrangthai() == 1 && monan.getXoa() == 0) result.add(monan);
				}
    		} else if (tag.equals("ngungban")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getTrangthai() == 0 && monan.getXoa() == 0) result.add(monan);
				}
    		} else if (tag.equals("daxoa")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getXoa() == 1) result.add(monan);
				} 
    		}
    	return result;
    }
    
    public static List<MonAnModel> KhachhangGetMenu(Integer ID_canteen, String tukhoa) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
    	if (tukhoa!=null) tukhoa = Normalizer.normalize(tukhoa.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, ID_loaithucan, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE ID_canteen = ? AND trangthai = 1 AND xoa = 0;";            	
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	if (tukhoa==null) {
                    	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), null, Base64.getDecoder().decode(rs.getBytes(9)), -1, rs.getInt(10), -1));
                	} else {
                		String t = Normalizer.normalize((rs.getString(2)+rs.getString(4)+rs.getString(6)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
                		if (t.contains(tukhoa)) {
                        	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), null, Base64.getDecoder().decode(rs.getBytes(9)), -1, rs.getInt(10), -1));
                        }
                	}
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
   
    
    public static void addNewMonan(MonAnModel newMonan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO monan(ID_monan, ID_canteen, tenmon, mota, thanhphan, huongvi, ID_loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban, xoa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, newMonan.getID_monan());
                stm.setInt(2, newMonan.getID_canteen());
                stm.setString(3, newMonan.getTenmon());
                stm.setString(4, newMonan.getMota());
                stm.setString(5, newMonan.getThanhphan());
                stm.setString(6, newMonan.getHuongvi());
                stm.setInt(7, newMonan.getID_loaithucan());
                stm.setDouble(8, newMonan.getGiacu());
                stm.setDouble(9, newMonan.getGiahientai());
                stm.setDate(10, newMonan.getNgaytao());
                stm.setString(11, Base64.getEncoder().encodeToString(newMonan.getHinhanhchinh()));
                stm.setInt(12, newMonan.getTrangthai());
                stm.setInt(13, newMonan.getDaban());
                stm.setInt(14, newMonan.getXoa());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void EditMonan(MonAnModel monan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE monan SET tenmon=?, mota=?, thanhphan=?, huongvi=?, ID_loaithucan=?, giacu=?, giahientai=?";
                if (monan.getHinhanhchinh() != null) {
                    sql += ", hinhanhchinh = ?"; 
                }
                sql += " WHERE ID_monan = ? AND ID_canteen = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, monan.getTenmon());
                stm.setString(2, monan.getMota());
                stm.setString(3, monan.getThanhphan());
                stm.setString(4, monan.getHuongvi());
                stm.setInt(5, monan.getID_loaithucan());
                stm.setDouble(6, monan.getGiacu());
                stm.setDouble(7, monan.getGiahientai());
                if (monan.getHinhanhchinh() != null) {
                    stm.setString(8, Base64.getEncoder().encodeToString(monan.getHinhanhchinh()));
                    stm.setInt(9, monan.getID_monan());
                    stm.setInt(10, monan.getID_canteen());
                } else {
                    stm.setInt(8, monan.getID_monan());
                    stm.setInt(9, monan.getID_canteen());
                }
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void EditTrangthai(Integer ID_monan, Integer trangthai) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE monan SET trangthai=? WHERE ID_monan = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, trangthai);
                stm.setInt(2, ID_monan);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void Delete_Restore__Product(Integer ID_canteen, Integer ID_monan, String type) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = null;
            	if (type.equals("Delete")) {
            		sql = "UPDATE monan SET xoa = '1' WHERE ID_monan = ? AND ID_canteen = ?;";
            	} else if (type.equals("Restore")) {
            		sql = "UPDATE monan SET xoa = '0' WHERE ID_monan = ? AND ID_canteen = ?;";
				} else if (type.equals("DeleteForever")) {
					sql = "DELETE FROM monan WHERE ID_monan = ? AND ID_canteen = ?;";
				}
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_monan);
                stm.setInt(2, ID_canteen);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean CheckProduct(int ID_monan, int ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT * FROM monan WHERE ID_monan = ? AND ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_monan);
            	stm.setInt(2, ID_canteen);
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
    
    public static void main(String[] args) throws SQLException, Exception {
    	List<MonAnModel> listMonAn = getListMonanByTag(10002, "dangban");
    	for (MonAnModel monAn : listMonAn) {
    	    System.out.println("ID món ăn: " + monAn.getID_monan());
    	    System.out.println("Tên món ăn: " + monAn.getTenmon());
    	    System.out.println("ngay tao món ăn: " + monAn.getGiahientai());
    	    // và các thuộc tính khác của MonAnModel tương ứng
    	}
    	System.out.println(CheckProduct(1, 10001));
	}
}
