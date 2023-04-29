package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import datdocantin.Model.CanteenModel;
import datdocantin.Util.connectDB;

public class CanteenDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static CanteenModel getInfoCanteen(String id) throws SQLException, Exception {
    	CanteenModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT id, ten, sodienthoai, email, tinh, huyen, xa, pin, avatar FROM canteen WHERE id = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setString(1, id);
            	rs = stm.executeQuery();
                if (rs.next()) { 
                	byte[] decodedAvatar = null;
                	if (rs.getBytes(9)!=null){
	                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(9));
                	}
                	result = new CanteenModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), decodedAvatar);
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }

    public static void addCanteen(CanteenModel newcanteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO canteen(id, ten, sodienthoai) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setString(1, newcanteen.getId());
                stm.setString(2, newcanteen.getTen());
                stm.setString(3, newcanteen.getSodienthoai());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void updateInfo(CanteenModel canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE canteen SET ten = ?, sodienthoai = ?, email = ?, tinh = ?, huyen = ?, xa = ?";
                if (canteen.getAvatar() != null) {
                    sql += ", avatar = ?";
                }
                sql += " WHERE id = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, canteen.getTen());
                stm.setString(2, canteen.getSodienthoai());
                stm.setString(3, canteen.getEmail());
                stm.setString(4, canteen.getTinh());
                stm.setString(5, canteen.getHuyen());
                stm.setString(6, canteen.getXa());
                if (canteen.getAvatar() != null) {	
                    stm.setString(7, Base64.getEncoder().encodeToString(canteen.getAvatar()));
                    stm.setString(8, canteen.getId());
                } else {
                    stm.setString(7, canteen.getId());
                }
                stm.executeUpdate();
            }

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static List<CanteenModel> SearchCanteen(String txtSearch, String Tinh, String Huyen, String Xa) throws SQLException, Exception {
    	List<CanteenModel> result = new ArrayList<CanteenModel>();
    	txtSearch = Normalizer.normalize(txtSearch.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    	try {
    		conn = connectDB.getConnection();
			if (conn != null) {
				if (!Tinh.equals("-1")) {
					String sql = "SELECT id, ten, sodienthoai, email, tinh, huyen, xa, avatar FROM canteen WHERE tinh = ?" 
							+ (!Huyen.equals("-1") ? " AND huyen = ?" : "") 
					        + (!Xa.equals("-1") ? " AND xa = ?" : "")  + ";";
					stm = conn.prepareStatement(sql);
					stm.setString(1, Tinh);
					int index = 2;
					if (!Huyen.equals("-1")) {
					    stm.setString(index, Huyen);
					    index++;
					}
					if (!Xa.equals("-1")) {
					    stm.setString(index, Xa);
					}
					rs = stm.executeQuery();
					while (rs.next()) {
						byte[] decodedAvatar = null;
	                	if (rs.getBytes(8)!=null){
		                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(8));
	                	}
	                	result.add(new CanteenModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),"", "", "", "", decodedAvatar));
					}
				}
				else if (!txtSearch.equals("")) {
					String sql = "SELECT id, ten, sodienthoai, email, tinh, huyen, xa, avatar FROM canteen;";
					stm = conn.prepareStatement(sql);
	            	rs = stm.executeQuery();
	                while (rs.next()) {
	                	byte[] decodedAvatar = null;
	                	if (rs.getBytes(8)!=null){
		                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(8));
	                	}
	                	String t = Normalizer.normalize((rs.getString(1)+rs.getString(2)+rs.getString(3)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
	                	if (t.contains(txtSearch)) {
	                		result.add(new CanteenModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),"", "", "", "", decodedAvatar));
	                	}
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
    
    
    public static void main(String[] args) {
		try {
			List<CanteenModel> result = SearchCanteen("", "48", "490", "20197");
			System.out.println(result.size());		
//			20197
		} 
		catch (Exception e) {
			
		}
	}
}
