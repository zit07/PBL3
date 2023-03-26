package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.util.TestUtils;

import datdocantin.Model.AccountModel;
import datdocantin.Util.connectDB;

public class AccountDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static AccountModel getAccountInfo(String id, String pass) throws SQLException, Exception {
    	AccountModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT idAccount, sdt, pass, typeUser FROM account WHERE idAccount = ? AND pass = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, id);
            	stm.setString(2, pass);
            	rs = stm.executeQuery();
                if (rs.next()) {
                    result = new AccountModel(rs.getString("idAccount"), rs.getString("sdt"), rs.getString("pass"), rs.getString("typeUser"));
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static String getIDbySdt(String sdt) throws SQLException, Exception {
        String result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT idAccount FROM account WHERE sdt = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sdt);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("idAccount");
                }
            }
        } catch (Exception e) {
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static boolean CheckAccountNotExist(String sdt) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT sdt FROM account WHERE sdt = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, sdt);
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
    
    public static void addAccount(AccountModel account) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO account(idAccount, sdt, pass, typeUser) VALUES(?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account.getID_Account());
                stm.setString(2, account.getSdt());
                stm.setString(3, account.getPass());
                stm.setString(4, account.getType_User());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void ChangePassword(String id, String pass) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE account SET pass=? WHERE idAccount=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, pass);
                stm.setString(2, id);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static int getLastId() throws SQLException, Exception {
    	int result = -1;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT idAccount FROM account ORDER BY idAccount DESC LIMIT 1;";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("idAccount");
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    
    
    public static void main(String[] args) {
		try {
//			Test function in here
			
			AccountModel acc = new AccountModel("1002", "333", "333", "admin");
			System.out.print(AccountDAO.getIDbySdt("123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
