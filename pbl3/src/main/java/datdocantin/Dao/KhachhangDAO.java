package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datdocantin.Model.KhachHangModel;
import datdocantin.Util.connectDB;

public class KhachhangDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static KhachHangModel getKhachhangInfo(String id) throws SQLException, Exception {
    	KhachHangModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT idkh, hoten, ngaysinh, gioitinh, chieucao, cannang, sodienthoai, email, idcantin, monyeuthich FROM khachhang WHERE idkh = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, id);
            	rs = stm.executeQuery();
                if (rs.next()) {
                    result = new KhachHangModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),"");

                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    
    public static void addKhachhang(KhachHangModel newKhachhang) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO khachhang(idkh, hoten, ngaysinh, gioitinh, chieucao, cannang, sodienthoai, email, idcantin, monyeuthich, pin) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, newKhachhang.getIDKH());
                stm.setString(2, newKhachhang.getHoten());
                stm.setString(3, newKhachhang.getNgaysinh());
                stm.setString(4, newKhachhang.getGioitinh());
                stm.setString(5, newKhachhang.getChieucao());
                stm.setString(6, newKhachhang.getCannang());
                stm.setString(7, newKhachhang.getSodienthoai());
                stm.setString(8, newKhachhang.getEmail());
                stm.setString(9, newKhachhang.getIDCantin());
                stm.setString(10, newKhachhang.getMonyeuthich());
                stm.setString(11, newKhachhang.getPIN());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void updateInfo(KhachHangModel khachhang) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE khachhang SET hoten = ?, ngaysinh = ?, gioitinh = ?, chieucao = ?, cannang = ?, sodienthoai = ?, email = ?, idcantin = ?, monyeuthich = ? WHERE idkh = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, khachhang.getHoten());
                stm.setString(2, khachhang.getNgaysinh());
                stm.setString(3, khachhang.getGioitinh());
                stm.setString(4, khachhang.getChieucao());
                stm.setString(5, khachhang.getCannang());
                stm.setString(6, khachhang.getSodienthoai());
                stm.setString(7, khachhang.getEmail());
                stm.setString(8, khachhang.getIDCantin());
                stm.setString(9, khachhang.getMonyeuthich());
                stm.setString(10, khachhang.getIDKH());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }


    public static void ChangePin(String id, String pin) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE khachhang SET pin=? WHERE idkh=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, pin);
                stm.setString(2, id);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
     
    
    public static void main(String[] args) {
		try {
//			Test function in here
//			KhachHangModel kh = new KhachHangModel("322222","test",null,"","","","sdt","","","");
			KhachHangModel kh = KhachhangDAO.getKhachhangInfo("10001");
			KhachhangDAO.ChangePin("10001", "321");
			System.out.print(kh.getIDKH());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
