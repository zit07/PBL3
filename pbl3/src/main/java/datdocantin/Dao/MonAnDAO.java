package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import datdocantin.Model.KhachHangModel;
import datdocantin.Model.MonAnModel;
import datdocantin.Util.connectDB;

public class MonAnDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<MonAnModel> getListMonan(String idcantin) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT id, idcantin, tenmon, mota, huongvi, loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban FROM monan WHERE idcantin = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, idcantin);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	byte[] encodedHinhanh = null;
                	byte[] decodedHinhanh = null;
                	if (rs.getBytes(11)!=null){
                		encodedHinhanh = rs.getBytes(10);
                		decodedHinhanh = Base64.getDecoder().decode(encodedHinhanh);
                	}
                	result.add(new MonAnModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), decodedHinhanh, rs.getString(11),rs.getString(12)));
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static void addNewMonan (MonAnModel newMonan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO monan(id, idcantin, tenmon, mota, huongvi, loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, newMonan.getId());
                stm.setString(2, newMonan.getIdcantin());
                stm.setString(3, newMonan.getTenmon());
                stm.setString(4, newMonan.getMota());
                stm.setString(5, newMonan.getHuongvi());
                stm.setString(6, newMonan.getLoaithucan());
                stm.setString(7, newMonan.getGiacu());
                stm.setString(8, newMonan.getGiahientai());
                stm.setString(9, newMonan.getNgaytao());
                stm.setString(10, Base64.getEncoder().encodeToString(newMonan.getHinhanhchinh()));
                stm.setString(11, newMonan.getTrangthai());
                stm.setString(12, newMonan.getDaban());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) throws SQLException, Exception {
    	List<MonAnModel> listMonAn = MonAnDAO.getListMonan("10003");
    	for (MonAnModel monAn : listMonAn) {
    	    System.out.println("ID món ăn: " + monAn.getId());
    	    System.out.println("Tên món ăn: " + monAn.getTenmon());
    	    System.out.println("Giá món ăn: " + monAn.getNgaytao());
    	    // và các thuộc tính khác của MonAnModel tương ứng
    	}
	}
}
