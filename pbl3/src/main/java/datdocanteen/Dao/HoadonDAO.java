package datdocanteen.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datdocanteen.Model.HoadonModel;
import datdocanteen.Util.connectDB;

public class HoadonDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<HoadonModel> getHoadonforCanteen(int ID_canteen,LocalDate ngay, String tag) throws Exception{
    	List<HoadonModel> result = new ArrayList<HoadonModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	if (tag.equals("choxacnhan")) {
					tag = "đang kiểm tra";
				} else if (tag.equals("dangchuanbi")) {
					tag = "đang chuẩn bị món";
				} else if (tag.equals("dahoantat")) {
					tag = "đã chuẩn bị xong";
				}
            	String sql = "SELECT ID_hoadon, ID_khachhang, ngaytao, tongtien, trangthai, madon FROM hoadon "
            			+ "WHERE ID_canteen = ? AND xoa = 1 AND trangthai = ? AND ngaytao= ? ORDER BY ID_hoadon DESC;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_canteen);
                stm.setString(2, tag);
                stm.setDate(3, Date.valueOf(ngay));
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add(new HoadonModel(rs.getInt(1), ID_canteen, rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), rs.getString(6), null));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    public static void addHoadon(HoadonModel hoadon) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "";
            	sql = "INSERT INTO hoadon(ID_hoadon, ID_canteen, ID_khachhang, ngaytao, tongtien, trangthai, madon, xoa) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, hoadon.getID_hoadon());
                stm.setInt(2, hoadon.getID_canteen());
                stm.setInt(3, hoadon.getID_khachhang());
                stm.setDate(4, hoadon.getNgaytao());
                stm.setDouble(5, hoadon.getTongtien());
                stm.setString(6, hoadon.getTrangthai());
                stm.setString(7, hoadon.getMadon());
                stm.setInt(8, hoadon.getXoa());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean checkMadonhang(String madon) throws Exception{
    	if (madon.equals("")) {
			return true;
		}
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	LocalDate today = LocalDate.now();
            	String sql = "SELECT * FROM hoadon WHERE madon = ? AND ngaytao = ?;";
				stm = conn.prepareStatement(sql);
				stm.setString(1, madon);
				stm.setDate(2, Date.valueOf(today));
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
    
    public static List<HoadonModel> Timkiem(int ID_canteen, String txtsearch) throws Exception{
    	List<HoadonModel> result = new ArrayList<HoadonModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_hoadon, ID_khachhang, ngaytao, tongtien, trangthai FROM hoadon WHERE ID_canteen = ? AND madon = ? AND xoa = 1;";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, ID_canteen);
				stm.setString(2, txtsearch);
				rs = stm.executeQuery();
				if (rs.next()) {
					result.add(new HoadonModel(rs.getInt(1), ID_canteen, rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), txtsearch, null));
				} else {
					sql = "SELECT ID_hoadon, ngaytao, tongtien, trangthai, madon FROM hoadon WHERE ID_canteen = ? AND ID_khachhang = ? AND xoa = 1;";
					stm = conn.prepareStatement(sql);
					stm.setInt(1, ID_canteen);
					stm.setString(2, txtsearch);
					rs = stm.executeQuery();
					while (rs.next()) {
						result.add(new HoadonModel(rs.getInt(1), ID_canteen, Integer.valueOf(txtsearch), rs.getDate(2).toLocalDate(), rs.getDouble(3), rs.getString(4),  rs.getString(5), null));
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
    
    public static boolean checkID_hoadon(int ID_hoadon, int ID_canteen) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT * FROM hoadon WHERE ID_hoadon = ? AND ID_canteen = ?;";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, ID_hoadon);
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
    
    public static List<HoadonModel> getHoadondamua(int ID_khachhang, LocalDate ngay) throws Exception{
    	List<HoadonModel> result = new ArrayList<HoadonModel>();
    	try {
            	conn.prepareStatement("SET NAMES 'UTF8'").execute();
            	String sql = "SELECT ID_hoadon, ID_canteen, ngaytao, tongtien, trangthai, madon FROM hoadon "
            			+ "WHERE ID_khachhang = ? AND xoa = 1 AND ngaytao = ? AND (trangthai = 'đang chuẩn bị món' OR trangthai = 'đã chuẩn bị xong') ORDER BY ID_hoadon DESC;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_khachhang);
                stm.setDate(2, Date.valueOf(ngay));
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add(new HoadonModel(rs.getInt(1), rs.getInt(2), ID_khachhang, rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), rs.getString(6), null));
				}
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        }
    	return result;
    }
    
    public static List<HoadonModel> getHoadondahuya(int ID_khachhang, LocalDate ngay) throws Exception{
    	List<HoadonModel> result = new ArrayList<HoadonModel>();
    	try {
            	conn.prepareStatement("SET NAMES 'UTF8'").execute();
            	String sql = "SELECT ID_hoadon, ID_canteen, ngaytao, tongtien, trangthai, madon FROM hoadon "
            			+ "WHERE ID_khachhang = ? AND xoa = 0 AND ngaytao = ? ORDER BY ID_hoadon DESC;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_khachhang);
                stm.setDate(2, Date.valueOf(ngay));
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add(new HoadonModel(rs.getInt(1), rs.getInt(2), ID_khachhang, rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), rs.getString(6), null));
				}
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        }
    	return result;
    }
    
    public static HoadonModel getHDchuathanhtoan(int ID_khachhang) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_hoadon, ID_canteen, ngaytao, tongtien, trangthai, madon FROM hoadon WHERE ID_khachhang = ? AND xoa = 1 AND trangthai = 'chưa thanh toán';";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_khachhang);
				rs = stm.executeQuery();
				if (rs.next()) {
					return new HoadonModel(rs.getInt(1), rs.getInt(2), ID_khachhang, rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), rs.getString(6), null);
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static List<HoadonModel> getHDforkhachhang(int ID_khachhang, String tag, LocalDate ngay) throws Exception{
    	List<HoadonModel> result = new ArrayList<HoadonModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	if (tag.equals("chuathanhtoan")) {
					tag = "chưa thanh toán";
				} else if (tag.equals("dangkiemtra")) {
					tag = "đang kiểm tra";
				} else if (tag.equals("damua")) {
					return getHoadondamua(ID_khachhang, ngay);
				} else if (tag.equals("dahuy")) {
					return getHoadondahuya(ID_khachhang, ngay);
				} 
            	String sql = "SELECT ID_hoadon, ID_canteen, ngaytao, tongtien, trangthai, madon FROM hoadon WHERE ID_khachhang = ? AND xoa = 1 AND trangthai = ?  ORDER BY ID_hoadon DESC;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_khachhang);
                stm.setString(2, tag); 
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add(new HoadonModel(rs.getInt(1), rs.getInt(2), ID_khachhang, rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getString(5), rs.getString(6), null));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    public static Integer getID_Hoadon(int ID_khachhang) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_hoadon FROM hoadon WHERE ID_khachhang = ? AND trangthai = 'chưa thanh toán' AND xoa = 1;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_khachhang);
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
    
    public static String getTrangthai(int ID_hoadon) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT trangthai FROM hoadon WHERE ID_hoadon = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadon);
				rs = stm.executeQuery();
				if (rs.next()) {
					return rs.getString(1);
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static void changeTongtien(int ID_hoadon, double tongtien) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
                String sql = "UPDATE hoadon SET tongtien = ? WHERE ID_hoadon = ?";
                stm = conn.prepareStatement(sql);
                stm.setDouble(1, tongtien);
                stm.setInt(2, ID_hoadon);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void changeTrangthai(int ID_hoadon, String tag) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (tag.equals("xacnhanthanhtoan")) {
				tag = "đang kiểm tra";
			} else if (tag.equals("dangchuanbi")) {
				tag = "đang chuẩn bị món";
			} else if (tag.equals("daxong")) {
				tag = "đã chuẩn bị xong";
			}
            if (conn != null) { 
                String sql = "UPDATE hoadon SET trangthai = ? WHERE ID_hoadon = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, tag);
                stm.setInt(2, ID_hoadon);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void XoaHoadon(int ID_hoadon) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
                String sql = "UPDATE hoadon SET xoa = 0, trangthai = 'bạn đã huỷ' WHERE ID_hoadon = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_hoadon);
                stm.executeUpdate();
            } 
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) throws Exception {
    	LocalDate nowdate = LocalDate.now();
;		System.out.println(getHDforkhachhang(10001, "damua", nowdate).size());
	}
}
