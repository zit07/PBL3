package datdocantin.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import datdocantin.Model.AccountModel;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Util.connectDB;

public class KhachhangDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<KhachHangModel> getListKhachhangByTag(List<KhachHangModel> khachhangs,List<AccountModel> accounts,String tag){
    	List<KhachHangModel> result=new ArrayList<KhachHangModel>();
    	int status_lock= tag.equalsIgnoreCase("active") ? 0 : 1;
    	for(AccountModel account: accounts) {
    			if(account.getType_User().equals("customer") && account.getStatus_lock()== status_lock) {
    				Optional<KhachHangModel> rs=khachhangs.stream()
    						.filter(khachhang -> khachhang.getID_khachhang() == account.getID_account())
    						.findFirst();
    				rs.ifPresent(result::add);
    			}
    	}
    	return result;
    }
    
    public static List<List<KhachHangModel>> getListKhachHangOfCanteen(List<KhachHangModel> khachhangs, List<CanteenModel> canteens) {
        Map<Integer, List<KhachHangModel>> mapKhachHangs = khachhangs.stream()
            .collect(Collectors.groupingBy(KhachHangModel::getID_canteen));
        List<List<KhachHangModel>> results = new ArrayList<>();
        for (CanteenModel canteen : canteens) {
            List<KhachHangModel> khachHangsOfCanteen = mapKhachHangs.getOrDefault(canteen.getID_canteen(), new ArrayList<>());
            results.add(khachHangsOfCanteen);
        }
        return results;
    }
    
    public static List<KhachHangModel> getAllKhachHang() throws Exception{
    	List<KhachHangModel> ketQua=new ArrayList<>();
    	 try {
    		 conn = connectDB.getConnection();
             if (conn != null) {
    		 String sql = "SELECT ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, sodienthoai, email, ID_canteen, yeuthich, avatar FROM khachhang";
         	stm = conn.prepareStatement(sql);
         	rs = stm.executeQuery();
             while (rs.next()) {
					/*
					 * byte[] decodedAvatar = null; if (rs.getBytes(11)!=null){ decodedAvatar =
					 * Base64.getDecoder().decode(rs.getBytes(11)); }
					 */
             	Date date = rs.getDate(3);
             	LocalDate ngaysinh = (date != null) ? date.toLocalDate() : null;
             	ketQua.add(new KhachHangModel(rs.getInt(1), rs.getString(2), ngaysinh, rs.getString(4), rs.getDouble(5),
             			rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), -1, null));
                 	 
                 }
             }
         } catch (Exception e) {
        	 System.out.println(e.getMessage());
         } finally {
         	connectDB.closeConnection(conn, stm, rs);
         }
        return ketQua;
    }
    
    
    
    public List<KhachHangModel> getAllKHActive() throws Exception{
    	List<KhachHangModel> ketQua=new ArrayList<>();
    	 try {
             conn = connectDB.getConnection();
             if (conn != null) {
            	 String sql="SELECT ID_khachhang,hoten,gioitinh,sodienthoai,email FROM khachhang"
            	 		+ "+left join account on khachhang.ID_khachhang=account.ID_account where account.status_lock=?";
             	stm = conn.prepareStatement(sql);
             	stm.setInt(1, 0);
             	rs = stm.executeQuery();
             	while (rs.next()) {
                 	 ketQua.add(new KhachHangModel(rs.getInt("ID_khachhang"), rs.getString("hoten"), null,
                 			 rs.getString("gioitinh"), null, null, rs.getString("sodienthoai"), rs.getString("email"), 
                 			 null, "", null, null));
                 }
             }
         } catch (Exception e) {
        	 System.out.println(e.getMessage());
         } finally {
         	connectDB.closeConnection(conn, stm, rs);
         }
        return ketQua;
    }
     public List<KhachHangModel> getAllKHActiveByIdCanteen(String idcanteen) throws Exception{
        	List<KhachHangModel> ketQua=new ArrayList<>();
        	 try {
                 conn = connectDB.getConnection();
                 if (conn != null) {
                	 String sql="SELECT ID_khachhang,hoten,gioitinh,sodienthoai,email FROM khachhang "
                	 		+ "left join account on khachhang.ID_khachhang=account.ID_account where account.status_lock=?"
                	 		+ " and ID_canteen=?";
                 	stm = conn.prepareStatement(sql);
                 	stm.setInt(1,0);
                 	stm.setString(2,idcanteen);
                 	rs = stm.executeQuery();
                     while (rs.next()) {
                    	 ketQua.add(new KhachHangModel(rs.getInt("ID_khachhang"), rs.getString("hoten"), null,
                     			 rs.getString("gioitinh"), null, null, rs.getString("sodienthoai"), rs.getString("email"), 
                     			 null, "", null, null));
                     }
                 }
             } catch (Exception e) {
            	 System.out.println(e.getMessage());
             } finally {
             	connectDB.closeConnection(conn, stm, rs);
             }
            return ketQua;
        }
        public List<KhachHangModel> searchByName(String txtSearch) throws SQLException, Exception {
        	List<KhachHangModel> result = new ArrayList<>();
        	txtSearch = Normalizer.normalize(txtSearch.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
            try {
                conn = connectDB.getConnection();
                if (conn != null) {
                	String sql = "SELECT ID_khachhang, hoten, gioitinh, sodienthoai, email,ID_canteen  FROM khachhang WHERE hoten like  ?";
                	stm = conn.prepareStatement(sql);
                	stm.setString(1, "%"+txtSearch+"%");
                	rs = stm.executeQuery();
                    while (rs.next()) {
                    	result.add(new KhachHangModel(rs.getInt("ID_khachhang"), rs.getString("hoten"), null,
                    			 rs.getString("gioitinh"), null, null, rs.getString("sodienthoai"), rs.getString("email"), 
                     			 rs.getInt("ID_canteen"), "", null, null));
                     }
                    }
             } catch (Exception e) {
            } finally {
            	connectDB.closeConnection(conn, stm, rs);
            }
            return result;
        }
        
    
    
    public static KhachHangModel getKhachhangInfo(int ID_khachhang) throws SQLException, Exception {
    	KhachHangModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, sodienthoai, email, ID_canteen, yeuthich, avatar FROM khachhang WHERE ID_khachhang = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_khachhang);
            	rs = stm.executeQuery();
                if (rs.next()) {
                	byte[] decodedAvatar = null;
                	if (rs.getBytes(11)!=null){
	                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(11));
                	}
                	Date date = rs.getDate(3);
                	LocalDate ngaysinh = (date != null) ? date.toLocalDate() : null;
                	result = new KhachHangModel(rs.getInt(1), rs.getString(2), ngaysinh, rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), -1, decodedAvatar);
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }
    
    
    public static void addKhachhang(KhachHangModel newKhachhang) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO khachhang(ID_khachhang, hoten, sodienthoai) VALUES(?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, newKhachhang.getID_khachhang());
                stm.setString(2, newKhachhang.getHoten());
                stm.setString(3, newKhachhang.getSodienthoai());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void updateInfo(KhachHangModel khachhang) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE khachhang SET hoten = ?, ngaysinh = ?, gioitinh = ?, chieucao = ?, cannang = ?, email = ?, yeuthich = ?";
                if (khachhang.getAvatar() != null) {
                    sql += ", avatar = ?";
                }
                sql += " WHERE ID_khachhang = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, khachhang.getHoten());
                stm.setDate(2, khachhang.getNgaysinh());
                stm.setString(3, khachhang.getGioitinh());
                stm.setDouble(4, khachhang.getChieucao());
                stm.setDouble(5, khachhang.getCannang());
                stm.setString(6, khachhang.getEmail());
                stm.setString(7, khachhang.getYeuthich());
                if (khachhang.getAvatar() != null) {
                    stm.setString(8, Base64.getEncoder().encodeToString(khachhang.getAvatar()));
                    stm.setInt(9, khachhang.getID_khachhang());
                } else {
                    stm.setInt(8, khachhang.getID_khachhang());
                }
                stm.executeUpdate();
                if (khachhang.getSodienthoai() != null ) {
                	sql = "UPDATE khachhang SET sodienthoai = ? WHERE ID_khachhang = ?;";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, khachhang.getSodienthoai());
                    stm.setInt(2, khachhang.getID_khachhang());
                    stm.executeUpdate();
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }


    public static void ChangePin(int ID_khachhang, int PIN) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE khachhang SET pin=? WHERE ID_khachhang=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, PIN);
                stm.setInt(2, ID_khachhang);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void ChangeCanteen(int ID_khachhang, Integer ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "";
                if (ID_canteen != null) sql = "UPDATE khachhang SET ID_canteen=? WHERE ID_khachhang=?";
                else sql = "UPDATE khachhang SET ID_canteen= null WHERE ID_khachhang=?";
                stm = conn.prepareStatement(sql);
                if (ID_canteen != null) {
                	stm.setInt(1, ID_canteen);
                	stm.setInt(2, ID_khachhang);
                }
                else stm.setInt(1, ID_khachhang);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
   

    
    
    public static void main(String[] args) {
		try {
//			Test function in here
//			KhachHangModel kh = new KhachHangModel("322222","test",null,"","","","sdt","","","");
			KhachhangDAO.ChangeCanteen(10001, null);
//			System.out.print(kh.getNgaysinh());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
