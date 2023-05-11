package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.GiohoatdongModel;
import datdocantin.Util.connectDB;

public class GiohoatdongDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<GiohoatdongModel> getGiohoatdong(int ID_canteen) throws Exception {
        List<GiohoatdongModel> giohoatdong_List = new ArrayList<GiohoatdongModel>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT ID_giohoatdong, ID_canteen, thu, giomocua, giodongcua FROM giohoatdong WHERE ID_canteen = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_canteen);
                rs = stm.executeQuery();
                while (rs.next()) {
                	String giomocua = rs.getString(4) != null ? rs.getString(4).substring(0, rs.getString(4).length()-3) : "-1";
                	String giodongcua = rs.getString(5) != null ? rs.getString(5).substring(0, rs.getString(5).length()-3) : "-1";
                	giohoatdong_List.add(new GiohoatdongModel(rs.getInt(1),rs.getInt(2),rs.getInt(3),giomocua, giodongcua));
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return giohoatdong_List;
    }
    
    public static void Addgiohoatdong(int ID_giohoatdong, int ID_canteen) throws Exception {
		try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	for (int i=2; i<=8; i++) {
                	String sql = "INSERT INTO giohoatdong(ID_giohoatdong, ID_canteen, thu, giomocua, giodongcua) VALUES(?, ?, ?, ?, ?);";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID_giohoatdong + i - 2);
                    stm.setInt(2, ID_canteen);
                    stm.setInt(3, i);
                    stm.setString(4, null);
                    stm.setString(5, null);
                    stm.executeUpdate();
            	}
            }
        } catch (Exception e) {
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
	} 
    	
    public static void Changegiohoatdong(List<GiohoatdongModel> giohoatdong_List) throws Exception {
		try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	for (int i = 0; i < giohoatdong_List.size(); i++) {
                	String sql = "UPDATE giohoatdong SET giomocua=?, giodongcua=? WHERE ID_canteen=? AND thu=?;";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, giohoatdong_List.get(i).getGiomocua());
                    stm.setString(2, giohoatdong_List.get(i).getGiodongcua());
                    stm.setInt(3, giohoatdong_List.get(i).getID_canteen());
                    stm.setInt(4, giohoatdong_List.get(i).getThu());
                    stm.executeUpdate();
            	}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
	} 
    
    public static void main(String[] args) {
		try {
			List<GiohoatdongModel> giohoatdongList = new ArrayList<GiohoatdongModel>();
			giohoatdongList = getGiohoatdong(10002);System.out.println(giohoatdongList.size());
			for (int i = 0; i < giohoatdongList.size(); i++) {
			    String num = giohoatdongList.get(i).getGiomocua();
			    System.out.println(num+"-"+giohoatdongList.get(i).getGiodongcua());
			}
//			Addgiohoatdong(Integer.valueOf(getNewIDforTable.getNewID("giohoatdong")), "10003");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
