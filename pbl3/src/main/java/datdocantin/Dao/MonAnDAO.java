package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
            	String sql = "SELECT id, idcantin, tenmon, mota, thanhphan, huongvi, loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban FROM monan WHERE idcantin = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, idcantin);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	byte[] encodedHinhanh = null;
                	byte[] decodedHinhanh = null;
                	if (rs.getBytes(11)!=null){
                		encodedHinhanh = rs.getBytes(11);
                		decodedHinhanh = Base64.getDecoder().decode(encodedHinhanh);
                	}
                	result.add(new MonAnModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), decodedHinhanh, rs.getString(12),rs.getString(13)));
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static List<MonAnModel> getResultSearchforCanteen(String idcantin, String tukhoa) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>();
    	tukhoa = tukhoa.toLowerCase();
    	tukhoa = Normalizer.normalize(tukhoa, Normalizer.Form.NFD);
    	tukhoa = tukhoa.replaceAll("\\p{M}", "");
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT id, idcantin, tenmon, mota, thanhphan, huongvi, loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban FROM monan WHERE idcantin = ? ;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, idcantin);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	String t = rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7);
                	t = t.toLowerCase();
                	t = Normalizer.normalize(t, Normalizer.Form.NFD);
                	t = t.replaceAll("\\p{M}", "");
                	if (t.contains(tukhoa)) {
                		byte[] encodedHinhanh = null;
                    	byte[] decodedHinhanh = null;
                    	if (rs.getBytes(11)!=null){
                    		encodedHinhanh = rs.getBytes(11);
                    		decodedHinhanh = Base64.getDecoder().decode(encodedHinhanh);
                    	}
                    	result.add(new MonAnModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), decodedHinhanh, rs.getString(12),rs.getString(13)));
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
                String sql = "INSERT INTO monan(id, idcantin, tenmon, mota, thanhphan, huongvi, loaithucan, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, newMonan.getId());
                stm.setString(2, newMonan.getIdcantin());
                stm.setString(3, newMonan.getTenmon());
                stm.setString(4, newMonan.getMota());
                stm.setString(5, newMonan.getThanhphan());
                stm.setString(6, newMonan.getHuongvi());
                stm.setString(7, newMonan.getLoaithucan());
                stm.setString(8, newMonan.getGiacu());
                stm.setString(9, newMonan.getGiahientai());
                stm.setString(10, newMonan.getNgaytao());
                stm.setString(11, Base64.getEncoder().encodeToString(newMonan.getHinhanhchinh()));
                stm.setString(12, newMonan.getTrangthai());
                stm.setString(13, newMonan.getDaban());
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
                String sql = "UPDATE monan SET tenmon=?, mota=?, thanhphan=?, huongvi=?, loaithucan=?, giacu=?, giahientai=?";
                if (monan.getHinhanhchinh() != null) {
                    sql += ", hinhanhchinh = ?"; 
                }
                sql += " WHERE id = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, monan.getTenmon());
                stm.setString(2, monan.getMota());
                stm.setString(3, monan.getThanhphan());
                stm.setString(4, monan.getHuongvi());
                stm.setString(5, monan.getLoaithucan());
                stm.setString(6, monan.getGiacu());
                stm.setString(7, monan.getGiahientai());
                if (monan.getHinhanhchinh() != null) {
                    stm.setString(8, Base64.getEncoder().encodeToString(monan.getHinhanhchinh()));
                    stm.setString(9, monan.getId());
                } else {
                    stm.setString(8, monan.getId());
                }
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void EditTrangthai(String id, String trangthai) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE monan SET trangthai=? WHERE id = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, trangthai);
                stm.setString(2, id);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) throws SQLException, Exception {
    	List<MonAnModel> listMonAn = MonAnDAO.getResultSearchforCanteen("10003", "my");
    	for (MonAnModel monAn : listMonAn) {
    	    System.out.println("ID món ăn: " + monAn.getId());
    	    System.out.println("Tên món ăn: " + monAn.getTenmon());
    	    System.out.println("Giá món ăn: " + monAn.getNgaytao());
    	    // và các thuộc tính khác của MonAnModel tương ứng
    	}
	}
}
