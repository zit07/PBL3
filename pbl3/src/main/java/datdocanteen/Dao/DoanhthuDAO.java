package datdocanteen.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import datdocanteen.Model.DoanhthuModel;
import datdocanteen.Util.connectDB;

public class DoanhthuDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<DoanhthuModel> getListDoanhthungay(int ID_canteen,LocalDate DateStart, LocalDate DateEnd) throws Exception{
    	List<DoanhthuModel> result = new ArrayList<DoanhthuModel>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_doanhthu, ngay, soluongdaban, tongdoanhthu FROM doanhthu WHERE ID_canteen = ? AND ngay >= ? AND ngay <= ?;";
                stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
                stm.setDate(2, Date.valueOf(DateStart));
                stm.setDate(3, Date.valueOf(DateEnd));
    			rs = stm.executeQuery();
    			while (rs.next()) {
    				result.add(new DoanhthuModel(rs.getInt(1), ID_canteen, rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getDouble(4)));
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    public static List<List<DoanhthuModel>> getListDoanhthuthang(int ID_canteen, List<YearMonth> monthList) throws Exception{
    	List<List<DoanhthuModel>> result = new ArrayList<List<DoanhthuModel>>();
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_doanhthu, ngay, soluongdaban, tongdoanhthu FROM doanhthu WHERE ID_canteen = ? AND MONTH(ngay) = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_canteen);
                for (YearMonth month : monthList) {
                	stm.setInt(2, month.getMonthValue());
                	rs = stm.executeQuery();
        			List<DoanhthuModel> doanhthu = new ArrayList<DoanhthuModel>();
        			while (rs.next()) {
        				doanhthu.add(new DoanhthuModel(rs.getInt(1), ID_canteen, rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getDouble(4)));
       				}
        			result.add(doanhthu);
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return result;
    }
    
    public static List<DoanhthuModel> getListTongDoanhthuthang(List<List<DoanhthuModel>> ListDoanhthuthang) throws Exception{
    	List<DoanhthuModel> result = new ArrayList<DoanhthuModel>();
    	for (List<DoanhthuModel> doanhthuthang : ListDoanhthuthang) {
			int soluongdaban = 0;
			double tongdoanhthu = 0;
			for (DoanhthuModel doanhthu : doanhthuthang) {
				soluongdaban += doanhthu.getSoluongdaban();
				tongdoanhthu += doanhthu.getTongdoanhthu();
			}
			result.add(new DoanhthuModel(0, 0, null, soluongdaban, tongdoanhthu));
		}
    	return result;
    }
    
    public static DoanhthuModel getTongDoanhthu(List<DoanhthuModel> Listdoanhthu) throws Exception{
    	int soluong = 0;
    	double tongdoanhthu = 0;
    	for (DoanhthuModel doanhthu : Listdoanhthu) {
			soluong += doanhthu.getSoluongdaban();
			tongdoanhthu += doanhthu.getTongdoanhthu();
		}
    	return new DoanhthuModel(0,0,null,soluong,tongdoanhthu);
    }
    
    public static void addDoanhthu(DoanhthuModel doanhthu) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "";
            	sql = "INSERT INTO doanhthu(ID_doanhthu, ID_canteen, ngay, soluongdaban, tongdoanhthu) VALUES(?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, doanhthu.getID_doanhthu());
                stm.setInt(2, doanhthu.getID_canteen());
                stm.setDate(3, doanhthu.getNgay());
                stm.setInt(4, doanhthu.getSoluongdaban());
                stm.setDouble(5, doanhthu.getTongdoanhthu());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void changeTongtien(int ID_doanhthu, double tongdoanhthu) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
                String sql = "UPDATE doanhthu SET tongdoanhthu = ? WHERE ID_doanhthu = ?";
                stm = conn.prepareStatement(sql);
                stm.setDouble(1, tongdoanhthu);
                stm.setInt(2, ID_doanhthu);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void changeTongsoluong(int ID_doanhthu, int tongsoluong) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
                String sql = "UPDATE doanhthu SET soluongdaban = ? WHERE ID_doanhthu = ?";
                stm = conn.prepareStatement(sql);
                stm.setDouble(1, tongsoluong);
                stm.setInt(2, ID_doanhthu);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static Integer getID_Doanhthu(int ID_canteen, LocalDate day) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) { 
            	String sql = "SELECT ID_Doanhthu FROM doanhthu WHERE ID_canteen = ? AND ngay = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_canteen);
                stm.setDate(2, Date.valueOf(day));
				rs = stm.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return null;
    }
    
    public static void main(String[] args) {
		
	}
}
