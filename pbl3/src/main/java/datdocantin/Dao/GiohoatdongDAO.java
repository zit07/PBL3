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
    
    public static List<GiohoatdongModel> getGiohoatdong(String idcanteen) throws Exception {
        List<GiohoatdongModel> giohoatdongList = new ArrayList<GiohoatdongModel>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT id, idcanteen, thu, giomocua, giodongcua FROM giohoatdong WHERE idcanteen = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idcanteen);
                rs = stm.executeQuery();
                while (rs.next()) {
                	String giomocua = rs.getString(4) != null ? rs.getString(4).substring(0, rs.getString(4).length()-3) : "-1";
                	String giodongcua = rs.getString(5) != null ? rs.getString(5).substring(0, rs.getString(5).length()-3) : "-1";
                	giohoatdongList.add(new GiohoatdongModel(rs.getString(1),rs.getString(2),rs.getString(3),giomocua, giodongcua));
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return giohoatdongList;
    }
    
    public static void Addgiohoatdong(int newid, String idcanteen) throws Exception {
    	for (int i=2; i<=8; i++) {
    		try {
                conn = connectDB.getConnection();
                if (conn != null) {
                	String sql = "INSERT INTO giohoatdong(id, idcanteen, thu, giomocua, giodongcua) VALUES(?, ?, ?, ?, ?);";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, newid + i - 2);
                    stm.setString(2, idcanteen);
                    stm.setInt(3, i);
                    stm.setString(4, null);
                    stm.setString(5, null);
                    stm.executeUpdate();
                }
            } catch (Exception e) {
            } finally {
                connectDB.closeConnection(conn, stm, rs);
            }
    	} 
    }
    	
    public static void Changegiohoatdong(List<GiohoatdongModel> giohoatdongList) throws Exception {
    	for (int i = 0; i < giohoatdongList.size(); i++) {
    		try {
                conn = connectDB.getConnection();
                if (conn != null) {
                	String sql = "UPDATE giohoatdong SET giomocua=?, giodongcua=? WHERE idcanteen=? AND thu=?;";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, giohoatdongList.get(i).getGiomocua());
                    stm.setString(2, giohoatdongList.get(i).getGiodongcua());
                    stm.setString(3, giohoatdongList.get(i).getIdcanteen());
                    stm.setString(4, giohoatdongList.get(i).getThu());
                    stm.executeUpdate();
                }
            } catch (Exception e) {
            	e.printStackTrace();
            } finally {
                connectDB.closeConnection(conn, stm, rs);
            }
    	} 
    }
    
    public static void main(String[] args) {
		try {
			List<GiohoatdongModel> giohoatdongList = new ArrayList<GiohoatdongModel>();
			giohoatdongList = getGiohoatdong("10003");System.out.println(giohoatdongList.size());
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
