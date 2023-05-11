package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datdocantin.Model.AccountModel;
import datdocantin.Util.PasswordEncoder;
import datdocantin.Util.connectDB;

public class AccountDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static AccountModel getAccountInfo(Integer ID_account, String sdt, String pass) throws SQLException, Exception {
    	AccountModel result = null;
        try {
        	conn = connectDB.getConnection();
        	if (conn != null) {
        	    String sql = "SELECT ID_account, sdt, pass, typeUser FROM account WHERE pass = ? AND " 
        	                    + (ID_account != null ? "ID_account = ?;" : "sdt = ?;");
        	    stm = conn.prepareStatement(sql);
        	    stm.setString(1, PasswordEncoder.encode(pass));
        	    if (ID_account != null) {
        	        stm.setInt(2, ID_account); 
        	    } else {
        	        stm.setString(2, sdt); 
        	    }
        	    rs = stm.executeQuery();
        	    if (rs.next()) {
        	        result = new AccountModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        	    }
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static AccountModel getsdt(String sdt, String pass) throws SQLException, Exception {
    	AccountModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_account, sdt, pass, typeUser FROM account WHERE sdt = ? AND pass = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, sdt); 
            	stm.setString(2, PasswordEncoder.encode(pass));
            	rs = stm.executeQuery();
                if (rs.next()) {
                    result = new AccountModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
                String sql = "INSERT INTO account(ID_account, sdt, pass, typeUser) VALUES(?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, account.getID_account());
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
    
    public static void ChangePassword(int ID_account, String pass) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE account SET pass=? WHERE ID_account=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, PasswordEncoder.encode(pass));
                stm.setInt(2, ID_account);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void ChangeSdt(int ID_account, String sdt) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE account SET sdt=? WHERE ID_account=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sdt);
                stm.setInt(2, ID_account);
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
//			Test function in here
			
//			AccountModel acc = new AccountModel("1002", "333", "333", "admin");
//			AccountModel result = getAccountInfo(10001, null, "123");
			AccountModel result = getAccountInfo(null, "123", "1");
			System.out.println(result.getID_account());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
