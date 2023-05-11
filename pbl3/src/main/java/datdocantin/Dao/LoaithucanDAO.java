package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.LoaithucanModel;
import datdocantin.Util.connectDB;

public class LoaithucanDAO {

	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<LoaithucanModel> getListLoaithucan() throws Exception {
    	List<LoaithucanModel> result = new ArrayList<LoaithucanModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_loaithucan, loaithucan FROM loaithucan;";
            	stm = conn.prepareStatement(sql);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	result.add(new LoaithucanModel(rs.getInt(1), rs.getString(2)));
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
    	List<LoaithucanModel> result = getListLoaithucan();
    	for (LoaithucanModel loaithucanModel : result) {
			System.out.println(loaithucanModel.getLoaithucan());
		}
	}
}
