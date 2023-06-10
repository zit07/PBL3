package datdocanteen.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocanteen.Model.MonAnModel;
import datdocanteen.Model.Monan_loaithucanModel;
import datdocanteen.Util.connectDB;

public class Monan_LoaithucanDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<List<Monan_loaithucanModel>> selectMonan_loaithucan(List<MonAnModel> monans) throws Exception {
    	List<List<Monan_loaithucanModel>> result = new ArrayList<List<Monan_loaithucanModel>>();
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT ID_monan_loaithucan, ID_loaithucan FROM monan_loaithucan WHERE ID_monan = ?;";
                stm = conn.prepareStatement(sql);
                for (MonAnModel monan : monans) {
                	List<Monan_loaithucanModel> loaimonans = new ArrayList<Monan_loaithucanModel>();
                	stm.setInt(1, monan.getID_monan());
                    rs = stm.executeQuery();
                    while (rs.next()) {
                    	loaimonans.add(new Monan_loaithucanModel(rs.getInt(1),  monan.getID_monan(), rs.getInt(2)));
                    }
                    result.add(loaimonans);
				}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static void AddMonan_loaithucan(List<Monan_loaithucanModel> monan_loaithucans) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO monan_loaithucan(ID_monan_loaithucan, ID_monan, ID_loaithucan) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
            	for (Monan_loaithucanModel monan_loaithucan : monan_loaithucans) {
                    stm.setInt(1, monan_loaithucan.getID_monan_loaithucan());
                    stm.setInt(2, monan_loaithucan.getID_monan());
                    stm.setInt(3, monan_loaithucan.getID_loaithucan());
                    stm.executeUpdate();  
				}
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void DeleteMonan_loaithucan(int ID_monan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "DELETE FROM monan_loaithucan WHERE ID_monan = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_monan);
                stm.executeUpdate();   
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void main(String[] args) throws Exception {
//		System.out.println(selectMonan_loaithucan(11).size());
	}
}
