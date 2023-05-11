package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datdocantin.Model.CartModel;
import datdocantin.Util.connectDB;

public class CartDAO {

	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static void AddtoCart(CartModel cart) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO carts(cart_id, user_id) VALUES(?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cart.getCart_id());
                stm.setInt(2, cart.getUser_id());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
}
