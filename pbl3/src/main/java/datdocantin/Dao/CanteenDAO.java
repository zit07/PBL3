package datdocantin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import datdocantin.Model.AccountModel;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.DiachiModel;
import datdocantin.Util.connectDB;

public class CanteenDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
  
    public static List<CanteenModel> getListCanteenByTag(List<CanteenModel> canteens, List<AccountModel> accounts,String tag){
      	 List<CanteenModel> result=new ArrayList<CanteenModel>();
      	 int status_lock= tag.equalsIgnoreCase("active") ? 0 : 1;
       	for(AccountModel account: accounts) {
       			if(account.getType_User().equals("cantin") && account.getStatus_lock()== status_lock) {
       				Optional<CanteenModel> rs=canteens.stream()
       						.filter(khachhang -> khachhang.getID_canteen() == account.getID_account())
       						.findFirst();
       				rs.ifPresent(result::add);
       			}
       	}
       	return result;
      }
      
      public static List<CanteenModel> getAllCanteen() throws Exception{
      	List<CanteenModel> ketQua=new ArrayList<>();
      	 try { 
               conn = connectDB.getConnection();
                  if (conn != null) {
                   	String sql = "Select ID_canteen, ten, sodienthoai, email, ID_diachi, pin, avatar from canteen ";
                   	stm = conn.prepareStatement(sql);
                   	rs = stm.executeQuery();
                   	while(rs.next()) { 
                   		ketQua.add(new CanteenModel(rs.getInt(1),
                       			rs.getString(2), rs.getString(3),
                       			rs.getString(4), rs.getInt(5), 
                       			rs.getInt(6), null));
                   		
                       }
                   }
               }
      	  catch (Exception e) {
      		  e.printStackTrace();
               } finally {
               	connectDB.closeConnection(conn, stm, rs);
               }
          return ketQua;
      } 
       
       public static List<CanteenModel> getAllCanteenActive() throws Exception{
       	List<CanteenModel> ketQua=new ArrayList<>();
       	 try { 
                conn = connectDB.getConnection();
                   if (conn != null) {
                    	String sql = "Select ID_canteen,ten,sodienthoai,email,ID_diachi from canteen left join account on canteen.ID_canteen=account.ID_account where account.status_lock=0";
                    	stm = conn.prepareStatement(sql);
                    	rs = stm.executeQuery();
                    	while(rs.next()) {
                           ketQua.add(new CanteenModel(rs.getInt(1),
                        			rs.getString(2), rs.getString(3),
                        			rs.getString(4), rs.getInt(5), 
                        			null, null));
                        }
                    }
                }
       	  catch (Exception e) {
       		  System.out.println(e.getMessage());
                } finally {
                	connectDB.closeConnection(conn, stm, rs);
                }
           return ketQua;
       }
       
    public static CanteenModel getInfoCanteen(int ID_canteen) throws SQLException, Exception {
    	CanteenModel result = null;
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_canteen, ten, sodienthoai, email, ID_diachi, pin, avatar FROM canteen WHERE ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                if (rs.next()) { 
                	byte[] decodedAvatar = null;
                	if (rs.getBytes(7)!=null){
	                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(7));
                	}
                	result = new CanteenModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), decodedAvatar);
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return result;
    }

    public static String getNameCanteen(int ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ten FROM canteen WHERE ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                if (rs.next()) { 
                	return rs.getString(1);
                }
            }
        } catch (Exception e) {
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return null;
    }

    public static void addCanteen(CanteenModel newcanteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO canteen(ID_canteen, ten, sodienthoai, ID_diachi) VALUES(?, ?,?,?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, newcanteen.getID_canteen());
                stm.setString(2, newcanteen.getTen());
                stm.setString(3, newcanteen.getSodienthoai());
                stm.setInt(4,newcanteen.getID_diachi());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void updateInfo(CanteenModel canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE canteen SET ten = ?, email = ?";
                if (canteen.getAvatar() != null) {
                    sql += ", avatar = ?";
                }
                sql += " WHERE ID_canteen = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, canteen.getTen());
                stm.setString(2, canteen.getEmail());
                if (canteen.getAvatar() != null) {	
                    stm.setString(3, Base64.getEncoder().encodeToString(canteen.getAvatar()));
                    stm.setInt(4, canteen.getID_canteen());
                } else {
                    stm.setInt(3, canteen.getID_canteen());
                }
                stm.executeUpdate();
                if (canteen.getSodienthoai() != null) {
                	sql = "UPDATE canteen SET sodienthoai = ? WHERE ID_canteen = ?;";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, canteen.getSodienthoai());
                    stm.setInt(2, canteen.getID_canteen());
                    stm.executeUpdate();
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static List<CanteenModel> SearchCanteen(String txtSearch, DiachiModel diachi) throws SQLException, Exception {
    	List<CanteenModel> result = new ArrayList<CanteenModel>();
    	if (txtSearch!=null) txtSearch = Normalizer.normalize(txtSearch.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    	try {
    		conn = connectDB.getConnection();
    		if (conn != null) { 
				if (!txtSearch.equals("") && txtSearch!=null) {
					String sql = "SELECT ID_canteen, ten, sodienthoai, email, ID_diachi, avatar FROM canteen;";
					stm = conn.prepareStatement(sql); 
	            	rs = stm.executeQuery();
	                while (rs.next()) {
	                	byte[] decodedAvatar = null;
	                	if (rs.getBytes(6)!=null){
		                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(6));
	                	}
	                	String t = Normalizer.normalize((rs.getString(1)+rs.getString(2)+rs.getString(3)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
	                	if (t.contains(txtSearch)) {
	                		result.add(new CanteenModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), null, decodedAvatar));
	                	}
	                }
				}  
				
				if (diachi.getTinh() != -1) {
					String sql = "SELECT canteen.ID_canteen, canteen.ten, canteen.sodienthoai, canteen.email, canteen.ID_diachi, canteen.avatar "
							+ "FROM canteen JOIN diachi ON canteen.ID_diachi = diachi.ID_diachi WHERE diachi.tinh = ?"
							+ (diachi.getHuyen() != -1 ? " AND huyen = ?" : "") 
					        + (diachi.getXa() != -1  ? " AND xa = ?" : "")  + ";";;
					stm = conn.prepareStatement(sql); 
					stm.setInt(1, diachi.getTinh());
					int index = 2;
					if (diachi.getHuyen() != -1) {
					    stm.setInt(index, diachi.getHuyen());
					    index++;
					}
					if (diachi.getXa() != -1) {
					    stm.setInt(index, diachi.getXa());
					}
	            	rs = stm.executeQuery(); 
	                while (rs.next()) {
	                	byte[] decodedAvatar = null;
	                	if (rs.getBytes(6)!=null){
		                	decodedAvatar = Base64.getDecoder().decode(rs.getBytes(6));
	                	}
	                	boolean check = true;
	                	for (CanteenModel canteen : result) {
	                		if (canteen.getID_canteen() == rs.getInt(1)) {
	                			check = false;
	                			break;
	                		}
	                    }
	                	if (check) {
		                	result.add(new CanteenModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), null, decodedAvatar));
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
			//List<CanteenModel> result = SearchCanteen("111", new DiachiModel(-1,49,-1,-1));
			//System.out.println(result.size());		
//			20197
			addCanteen(new CanteenModel(5,"Be Da","098123987",null,2,null,null));
			List<CanteenModel> list=getAllCanteen();
			for(CanteenModel i : list){
				System.out.println(i);
			}
		} 
		catch (Exception e) {
			
		}
	}
}
