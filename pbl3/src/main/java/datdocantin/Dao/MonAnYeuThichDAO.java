package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datdocantin.Model.MonAnModel;
import datdocantin.Model.MonAnYeuThichModel;
import datdocantin.Util.connectDB;

public class MonAnYeuThichDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    public static void  EditMonAnYeuThich(MonAnYeuThichModel monan,String tag) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	if(tag == "yêu thích") {
            		String sql = "INSERT INTO monanyeuthich(ID_monanyeuthich, ID_khachhang, ID_monan) VALUES(?, ?, ?);";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, monan.getID_monanyeuthich());
                    stm.setInt(2, monan.getID_khachhang());
                    stm.setInt(3, monan.getID_monan());
            	}
            	else if(tag == "bỏ thích") {
            		String sql = "delete from monanyeuthich where ID_monanyeuthich=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, monan.getID_monanyeuthich());
            	}
                
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
   
    public static List<MonAnYeuThichModel> getListMonAnYeuThich(int id_khachhang) throws Exception{
    	List<MonAnYeuThichModel> list=new ArrayList<MonAnYeuThichModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "Select * from monanyeuthich where ID_khachhang=? Order by ID_monanyeuthich DESC";
                stm = conn.prepareStatement(sql);
                stm.setInt(1,id_khachhang);
                rs=stm.executeQuery();
                while(rs.next()) {
                	list.add(new MonAnYeuThichModel(rs.getInt("ID_monanyeuthich"),rs.getInt("ID_khachhang"),rs.getInt("ID_monan")));
                }
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return list;
    }
    public static void main(String[] args) throws Exception {
    	EditMonAnYeuThich(new MonAnYeuThichModel(7,109,2),"bỏ thích");
    	List<MonAnYeuThichModel> list=getListMonAnYeuThich(109);
    	for(MonAnYeuThichModel i : list){
    		System.out.println(i);
    	}
	}
}
