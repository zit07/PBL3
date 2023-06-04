package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import datdocantin.Model.CartModel;
import datdocantin.Util.connectDB;

public class CartDAO {

	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<CartModel> getCarts(int ID_khachhang) throws Exception{
    	List<CartModel> result =  new ArrayList<CartModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT cart.ID_cart, monan.tenmon, monan.hinhanhchinh, cart.soluong, monan.giahientai  "
            			+ "FROM cart INNER JOIN monan ON cart.ID_monan = monan.ID_monan "
            			+ "WHERE cart.ID_khachhang = ? AND monan.trangthai = 1;";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, ID_khachhang);
				rs = stm.executeQuery();
				while (rs.next()) {
					result.add(new CartModel(rs.getInt(1), null, null, rs.getString(2), Base64.getDecoder().decode(rs.getBytes(3)), rs.getInt(4), rs.getDouble(5)));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    
    public static void AddtoCart(CartModel cart) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "";
            	sql = "DELETE FROM cart WHERE ID_khachhang = ? AND ID_monan = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cart.getID_khachhang());
                stm.setInt(2, cart.getID_monan());
                stm.executeUpdate();   
            	sql = "INSERT INTO cart(ID_cart, ID_khachhang, ID_monan, soluong) VALUES(?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cart.getID_cart());
                stm.setInt(2, cart.getID_khachhang());
                stm.setInt(3, cart.getID_monan());
                stm.setInt(4, cart.getSoluong());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static int getSoluong(Integer ID_cart, Integer ID_monan, Integer ID_khachhang) {
    	try {
			conn = connectDB.getConnection();
			if (conn != null) {
				String sql = "";
				if (ID_cart!=null) {
					sql = "SELECT soluong from cart WHERE ID_cart = ?";
					stm = conn.prepareStatement(sql);
					stm.setInt(1, ID_cart);
				} else {
					sql = "SELECT soluong from cart WHERE ID_monan = ? AND ID_khachhang = ?";
					stm = conn.prepareStatement(sql);
					stm.setInt(1, ID_monan);
					stm.setInt(2, ID_khachhang);
				}
				rs = stm.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    public static int getSoluongmon(List<CartModel> carts) {
    	int sum = 0;
    	for (CartModel cart : carts) {
			sum += cart.getSoluong();
		}
    	return sum;
    }
    
    public static double getTongtien(List<CartModel> carts) {
    	double sum = 0;
    	for (CartModel cart : carts) {
			sum += cart.getSoluong() * cart.getGia();
		}
    	return sum;
    }
    
    public static void Tang_GiamCart(Integer ID_cart, String type, int soluong) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	soluong = type.equals("tang") ? soluong+1 : soluong-1; 
            	if (soluong < 0) soluong = 0;
            	String sql = "UPDATE cart SET soluong = ? WHERE ID_cart = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, soluong);
                stm.setInt(2, ID_cart);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void XoaCart(Integer ID_cart) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "DELETE FROM cart WHERE ID_cart = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_cart);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean checkCart( int ID_cart, int ID_khachhang) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT * FROM cart WHERE ID_cart = ? AND ID_khachhang = ?;";
				stm = conn.prepareStatement(sql);
				stm.setInt(1, ID_cart);
				stm.setInt(2, ID_khachhang);
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
    
    
    public static CartModel getID_Ten_soluong_gia(int ID_cart) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT m.ID_monan, m.tenmon, c.soluong, m.giahientai FROM cart c INNER JOIN monan m ON c.ID_monan = m.ID_monan WHERE c.ID_cart = ?;";				stm = conn.prepareStatement(sql);
				stm.setInt(1, ID_cart);
				rs = stm.executeQuery();
				while (rs.next()) {
					return new CartModel(null, null, rs.getInt(1), rs.getString(2), null, rs.getInt(3), rs.getDouble(4));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    public static void main(String[] args) throws Exception {
//		System.out.println(getCarts(10001).get(1).getGia());
	}
}
