package datdocanteen.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datdocanteen.Model.DoanhthuModel;
import datdocanteen.Model.DoanhthuchitietModel;
import datdocanteen.Util.connectDB;

public class DoanhthuchitietDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<List<DoanhthuchitietModel>> getListDoanhthuchitietngay(List<DoanhthuModel> ListDoanhthu) throws Exception {
    	List<List<DoanhthuchitietModel>> result = new ArrayList<List<DoanhthuchitietModel>>();
    	try {
    		conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_doanhthuchitiet, tenmonan, soluong, gia FROM doanhthuchitiet WHERE ID_doanhthu = ?";
                stm = conn.prepareStatement(sql);
                for (DoanhthuModel doanhthu : ListDoanhthu) {
                	List<DoanhthuchitietModel> listDTCT = new ArrayList<DoanhthuchitietModel>();
                	stm.setInt(1, doanhthu.getID_doanhthu());
                	rs = stm.executeQuery();
    				while (rs.next()) {
    					listDTCT.add(new DoanhthuchitietModel(rs.getInt(1), doanhthu.getID_doanhthu(), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
    				}
    				result.add(listDTCT);
				}
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
		return result;
    }
    
    public static List<List<DoanhthuchitietModel>> getListDoanhthuchitietthang(List<List<DoanhthuModel>> ListDoanhthu) throws Exception {
    	List<List<DoanhthuchitietModel>> result = new ArrayList<List<DoanhthuchitietModel>>();
    	try {
    		conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_doanhthuchitiet, tenmonan, soluong, gia FROM doanhthuchitiet WHERE ID_doanhthu = ?";
                stm = conn.prepareStatement(sql);
                for (List<DoanhthuModel> doanhthuthang : ListDoanhthu) {
                	List<DoanhthuchitietModel> listDTCT = new ArrayList<DoanhthuchitietModel>();
                	for (DoanhthuModel doanhthungay : doanhthuthang) {
                		stm.setInt(1, doanhthungay.getID_doanhthu());
                		rs = stm.executeQuery();
        				while (rs.next()) {
        					boolean check = true;
        					for (DoanhthuchitietModel dtct : listDTCT) {
        					    if (dtct.getTenmon().equals("Cơm")) {
        					        check = false;
        					        dtct.setSoluong(dtct.getSoluong() + rs.getInt(3));
        					        break;
        					    }
        					}
        					if (check) {
            					listDTCT.add(new DoanhthuchitietModel(rs.getInt(1), doanhthungay.getID_doanhthu(), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
							}
        				}
					}
    				result.add(listDTCT);
				}
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
		return result;
    }
    
    
    public static void addDoanhthuchitiet(DoanhthuchitietModel doanhthuchitiet) throws Exception {
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "INSERT INTO doanhthuchitiet(ID_doanhthuchitiet, ID_doanhthu, tenmonan, soluong, gia) VALUES(?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, doanhthuchitiet.getID_doangthuchitiet());
                stm.setInt(2, doanhthuchitiet.getID_doanhthu());
                stm.setString(3, doanhthuchitiet.getTenmon());
                stm.setInt(4, doanhthuchitiet.getSoluong());
                stm.setDouble(5, doanhthuchitiet.getGia());
                stm.executeUpdate();
            } 
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static Integer getID_doanhthuchitiet(DoanhthuchitietModel doanhthuchitiet) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT ID_doanhthuchitiet FROM doanhthuchitiet WHERE ID_doanhthu = ? AND tenmonan = ?;";				
	            stm = conn.prepareStatement(sql);
	            stm.setInt(1, doanhthuchitiet.getID_doanhthu());
	    		stm.setString(2, doanhthuchitiet.getTenmon());
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
    
    public static double getTongtien(int ID_doanhthu) throws Exception{
    	double tongtien = 0;
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT soluong, gia FROM doanhthuchitiet WHERE ID_doanhthu = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_doanhthu);
	    		rs = stm.executeQuery();
	    		while (rs.next()) {
	    			tongtien += rs.getInt(1) * rs.getDouble(2);
	    		}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return tongtien;
    }
    
    public static void changesoluong(int ID_doanhthuchitiet, int soluong) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
                String sql = "UPDATE doanhthuchitiet SET soluong = ? WHERE ID_doanhthuchitiet = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, soluong);
                stm.setInt(2, ID_doanhthuchitiet);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static int getTongsoluong(int ID_doanhthu) throws Exception{
    	int tongsoluong = 0;
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT soluong FROM doanhthuchitiet WHERE ID_doanhthu = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_doanhthu);
	    		rs = stm.executeQuery();
	    		while (rs.next()) {
	    			tongsoluong += rs.getInt(1);
	    		}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    	return tongsoluong;
    }
    
    public static int getSoluongmonan(int ID_doanhthuchitiet) throws Exception{
    	try {
            conn = connectDB.getConnection();
            if (conn != null) {
	            String sql = "SELECT soluong FROM doanhthuchitiet WHERE ID_doanhthuchitiet = ?;";				
	            stm = conn.prepareStatement(sql);
	    		stm.setInt(1, ID_doanhthuchitiet);
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
    	return 0;
    }
    
    public static void main(String[] args) {
		
	}
}
