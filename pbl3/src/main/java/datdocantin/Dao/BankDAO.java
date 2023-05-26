package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import datdocantin.Model.BankModel;
import datdocantin.Util.connectDB;

public class BankDAO {
	
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static void AddBank(int ID_bank_info) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO bank_info (ID_bank_info, tennganhang, stk, hovaten, maqr) VALUES (?, null, null, null, null);";     
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_bank_info);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void updateBank(BankModel bank) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE bank_info SET tennganhang=?, stk=?, hovaten=?";
                if (bank.getMaQR() != null) {
                    sql += ", maqr = ?"; 
                }
                sql += " WHERE ID_bank_info = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, bank.getTennganhang().toUpperCase());
                stm.setString(2, bank.getStk());
                stm.setString(3, bank.getHovaten().toUpperCase());
                if (bank.getMaQR() != null) {
                    stm.setString(4, Base64.getEncoder().encodeToString(bank.getMaQR()));
                    stm.setInt(5, bank.getID_bank_info());
                } else {
                	stm.setInt(4, bank.getID_bank_info());
                }
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static BankModel getBank(int ID_canteen) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT bank_info.ID_bank_info, bank_info.tennganhang, bank_info.stk, bank_info.hovaten, bank_info.maqr FROM canteen "
            			+ "JOIN bank_info ON canteen.ID_bank_info = bank_info.ID_bank_info "
            			+ "WHERE canteen.ID_canteen = ?;";
                stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen); 
            	rs = stm.executeQuery();
                if (rs.next()) {
                	byte[] maQR = null;
                	if (rs.getBytes(5)!=null){
                		maQR = Base64.getDecoder().decode(rs.getBytes(5));
                	}
                	return new BankModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), maQR);
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static void main(String[] args) throws Exception {
		System.out.println(getBank(10002).getID_bank_info());
	}
}
