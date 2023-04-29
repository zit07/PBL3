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
    
    public static AccountModel getAccountInfo(String id, String pass) throws SQLException, Exception {
    	AccountModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT id, sdt, pass, typeUser FROM account WHERE id = ? AND pass = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, id); 
            	stm.setString(2, PasswordEncoder.encode(pass));
            	rs = stm.executeQuery();
                if (rs.next()) {
                    result = new AccountModel(rs.getString("id"), rs.getString("sdt"), rs.getString("pass"), rs.getString("typeUser"));
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
                String sql = "SELECT id FROM account WHERE sdt = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sdt);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("id");
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
                String sql = "INSERT INTO account(id, sdt, pass, typeUser) VALUES(?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account.getid());
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
                String sql = "UPDATE account SET pass=? WHERE id=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, PasswordEncoder.encode(pass));
                stm.setString(2, id);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void ChangeSdt(String id, String sdt) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE account SET sdt=? WHERE id=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sdt);
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
                String sql = "SELECT * FROM account;";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result += 1;
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result+10000;
    }
    
    
    
    public static void main(String[] args) {
		try {
//			Test function in here
			
//			AccountModel acc = new AccountModel("1002", "333", "333", "admin");
			System.out.print(AccountDAO.getLastId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
