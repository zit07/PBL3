package datdocanteen.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import datdocanteen.Model.HoadonchitietModel;
import datdocanteen.Model.KhachHangModel;
import datdocanteen.Model.LichsutimkiemModel;
import datdocanteen.Model.MonAnModel;
import datdocanteen.Util.connectDB;

public class MonAnDAO {
	private static Connection conn = null;
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    
    public static List<MonAnModel> CanteenGetMenu(Integer ID_canteen, String tukhoa) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
    	if (tukhoa!=null) tukhoa = Normalizer.normalize(tukhoa.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban, xoa FROM monan WHERE ID_canteen = ? ORDER BY ID_monan DESC;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	LocalDate ngaytao = rs.getDate(8).toLocalDate(); 
                	if (tukhoa==null) {
                    	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), 
                    			rs.getDouble(7), ngaytao, Base64.getDecoder().decode(rs.getBytes(9)), rs.getInt(10),rs.getInt(11),rs.getInt(12)));
                	} else {
                		String t = Normalizer.normalize((rs.getString(2)+rs.getString(4)+rs.getString(6)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
                		if (t.contains(tukhoa)) {
                        	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), 
                        			rs.getDouble(7), ngaytao, Base64.getDecoder().decode(rs.getBytes(9)), rs.getInt(10),rs.getInt(11),rs.getInt(12)));
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
    
    public static List<MonAnModel> getListMonanByTag(Integer ID_canteen, String tag) throws SQLException, Exception {
    	List<MonAnModel> ListMonan = CanteenGetMenu(ID_canteen, null); 
    	List<MonAnModel> result = new ArrayList<MonAnModel>();
			if (tag.equals("tatca")) {
				for (MonAnModel monan : ListMonan) {
					if (monan.getXoa() == 0) result.add(monan);
				}
			} else if (tag.equals("dangban")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getTrangthai() == 1 && monan.getXoa() == 0) result.add(monan);
				}
    		} else if (tag.equals("ngungban")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getTrangthai() == 0 && monan.getXoa() == 0) result.add(monan);
				}
    		} else if (tag.equals("daxoa")) {
    			for (MonAnModel monan : ListMonan) {
					if (monan.getXoa() == 1) result.add(monan);
				} 
    		} 
    	return result;
    }
    
    public static List<MonAnModel> KhachhangGetMenu(Integer ID_canteen, String tukhoa) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
    	if (tukhoa!=null) tukhoa = Normalizer.normalize(tukhoa.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE ID_canteen = ? AND trangthai = 1 AND xoa = 0 ORDER BY ID_monan DESC;";            	
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_canteen);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	if (tukhoa==null) {
                    	result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
                    			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
                	} else {
                		String t = Normalizer.normalize((rs.getString(2)+rs.getString(4)+rs.getString(5)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
                		if (t.contains(tukhoa)) {
                			result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
                        			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));                        }
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
    
    public static MonAnModel getMonan(int ID_monan,int ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT tenmon, mota, thanhphan, huongvi, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE ID_monan = ? AND ID_canteen = ? AND trangthai = 1 AND xoa = 0;";            	
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_monan);
            	stm.setInt(2, ID_canteen);
            	rs = stm.executeQuery();
                if (rs.next()) {
                	return new MonAnModel(ID_monan, ID_canteen, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
                			rs.getDouble(6), null, Base64.getDecoder().decode(rs.getBytes(7)), -1, rs.getInt(8), -1);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return null;
    }
    
    public static String getTenmon(Integer ID_monan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT tenmon FROM monan WHERE ID_monan = ? AND trangthai = 1 AND xoa = 0;";            	
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_monan);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	return rs.getString(1);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return null;
    }
    
    public static Double getGia(Integer ID_monan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT giahientai FROM monan WHERE ID_monan = ? AND trangthai = 1 AND xoa = 0;";            	
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_monan);
            	rs = stm.executeQuery();
                while (rs.next()) {
                	return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return null;
    }
    
    public static List<MonAnModel> SearchMonan(String txtSearch) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
    	if (txtSearch != null && !txtSearch.equals("")) {
    		txtSearch = Normalizer.normalize(txtSearch.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
	        try {
	            conn = connectDB.getConnection();
	            if (conn != null) {
	            	String sql = "SELECT ID_monan, ID_canteen, tenmon, mota, thanhphan, huongvi, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE trangthai = 1 AND xoa = 0 ORDER BY ID_monan DESC;";            	
	            	stm = conn.prepareStatement(sql);
	            	rs = stm.executeQuery();
	                while (rs.next()) {
	                		String t = Normalizer.normalize((rs.getString(1)+rs.getString(3)+rs.getString(4)+rs.getString(5)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
	                		if (t.contains(txtSearch)) {
	                			result.add(new MonAnModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7),
	                        			rs.getDouble(8), null, Base64.getDecoder().decode(rs.getBytes(9)), -1, rs.getInt(10), -1));                        
	                		}
	                }
	            }
	        } catch (Exception e) {
	        	e.printStackTrace(); 
	        } finally {
	        	connectDB.closeConnection(conn, stm, rs);
	        }
    	}
        return result;
    }
    
    public static List<MonAnModel> GetMonanGoiy(KhachHangModel khachang) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
        try {
            conn = connectDB.getConnection();
            if (conn != null) { 
            	String sql = "";
            	//lay mon an chay
            	if (isLunarFirstOrFifteenth()) {
            		sql = "SELECT monan.ID_monan, monan.tenmon, monan.mota, monan.thanhphan, monan.huongvi, monan.giacu, monan.giahientai, monan.hinhanhchinh, monan.daban"
                			+ " FROM monan INNER JOIN monan_loaithucan ON monan.ID_monan = monan_loaithucan.ID_monan "
                			+ "INNER JOIN loaithucan ON monan_loaithucan.ID_loaithucan = loaithucan.ID_loaithucan "
                			+ "WHERE loaithucan.loaithucan = 'chay' AND monan.ID_canteen = ? AND monan.trangthai = 1 AND monan.xoa = 0 ORDER BY monan.ID_monan DESC;";
            		stm = conn.prepareStatement(sql);
                	stm.setInt(1, khachang.getID_canteen());
                	rs = stm.executeQuery();
            		while (rs.next()) {
                		result.add(new MonAnModel(rs.getInt(1), khachang.getID_canteen(), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
	                    		rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
                	}
				}
            	//lay mon an yeu thich
            	if (khachang.getYeuthich() != null) {
            		String[] yeuthichs = khachang.getYeuthich().split(",\\s*");
                	for (String yeuthich : yeuthichs) {
                		System.out.println(yeuthich);
                	}
                	sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE ID_canteen = ? AND trangthai = 1 AND xoa = 0 ORDER BY ID_monan DESC;";            	
                	stm = conn.prepareStatement(sql);
                	stm.setInt(1, khachang.getID_canteen());
                	rs = stm.executeQuery();
                    while (rs.next()) {
                    	boolean found = result.stream().anyMatch(monAn -> {
							try {
								return monAn.getID_monan() == rs.getInt(1);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return false;
						});
                    	if (!found) {
                    		String t = Normalizer.normalize((rs.getString(2)+rs.getString(4)+rs.getString(5)).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", ""); 
                        	for (String yeuthich : yeuthichs) {
        						if (t.contains(yeuthich)) {
        							result.add(new MonAnModel(rs.getInt(1), khachang.getID_canteen(), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
        		                    		rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
        							break;
        						}
        					}
						}
                    }
				}
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        
        //lay mon an tu lich su tim kiem
        List<LichsutimkiemModel> lstk = HistorySearchDAO.getSearchHistory(khachang.getID_khachhang());
        for (LichsutimkiemModel ls : lstk) {
			List<MonAnModel> listmonan = KhachhangGetMenu(khachang.getID_canteen(), ls.getNoidung());
			for (MonAnModel monan : listmonan) {
				boolean found = result.stream().anyMatch(monAn -> {
					return monAn.getID_monan() == monan.getID_monan();
				});
            	if (!found) {
            		result.add(monan);
            	}
			}
		}
        return result;
    }
    
    public static List<List<MonAnModel>> SortMonanByTag(KhachHangModel khachhang, String tukhoa, String tag, List<MonAnModel> monans) throws SQLException, Exception {
    	List<MonAnModel> ListMonan = monans;
    	if (monans == null) {
        	ListMonan = KhachhangGetMenu(khachhang.getID_canteen(), tukhoa); 
		}
    	
    	List<List<MonAnModel>> result = new ArrayList<List<MonAnModel>>();
    	int batchSize = 15;
		if (tag.equals("banchay")) {
		    Collections.sort(ListMonan, Comparator.comparingInt(MonAnModel::getDaban).reversed());
    	} else if (tag.equals("giamdan")) {
   	        Collections.sort(ListMonan, Comparator.comparingDouble(MonAnModel::getGiahientai).reversed());
   		} else if (tag.equals("tangdan")) {
            Collections.sort(ListMonan, Comparator.comparingDouble(MonAnModel::getGiahientai));
   		} else if (tag.equals("goiy")) {
   			ListMonan = GetMonanGoiy(khachhang);
		}
		for (int i = 0; i < ListMonan.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, ListMonan.size());
            List<MonAnModel> sublist = ListMonan.subList(i, endIndex);
            result.add(sublist);
        }
    	return result;
    }
   
    
    public static void addNewMonan(MonAnModel newMonan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO monan(ID_monan, ID_canteen, tenmon, mota, thanhphan, huongvi, giacu, giahientai, ngaytao, hinhanhchinh, trangthai, daban, xoa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, newMonan.getID_monan());
                stm.setInt(2, newMonan.getID_canteen());
                stm.setString(3, newMonan.getTenmon());
                stm.setString(4, newMonan.getMota());
                stm.setString(5, newMonan.getThanhphan());
                stm.setString(6, newMonan.getHuongvi());
                stm.setDouble(7, newMonan.getGiacu());
                stm.setDouble(8, newMonan.getGiahientai());
                stm.setDate(9, newMonan.getNgaytao());
                stm.setString(10, Base64.getEncoder().encodeToString(newMonan.getHinhanhchinh()));
                stm.setInt(11, newMonan.getTrangthai());
                stm.setInt(12, newMonan.getDaban());
                stm.setInt(13, newMonan.getXoa());
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void EditMonan(MonAnModel monan) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE monan SET tenmon=?, mota=?, thanhphan=?, huongvi=?, giacu=?, giahientai=?";
                if (monan.getHinhanhchinh() != null) {
                    sql += ", hinhanhchinh = ?"; 
                }
                sql += " WHERE ID_monan = ? AND ID_canteen = ?;";
                stm = conn.prepareStatement(sql);
                stm.setString(1, monan.getTenmon());
                stm.setString(2, monan.getMota());
                stm.setString(3, monan.getThanhphan());
                stm.setString(4, monan.getHuongvi());
                stm.setDouble(5, monan.getGiacu());
                stm.setDouble(6, monan.getGiahientai());
                if (monan.getHinhanhchinh() != null) {
                    stm.setString(7, Base64.getEncoder().encodeToString(monan.getHinhanhchinh()));
                    stm.setInt(8, monan.getID_monan());
                    stm.setInt(9, monan.getID_canteen());
                } else {
                    stm.setInt(7, monan.getID_monan());
                    stm.setInt(8, monan.getID_canteen());
                }
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void EditTrangthai(Integer ID_monan, Integer trangthai) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
                String sql = "UPDATE monan SET trangthai=? WHERE ID_monan = ?;";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, trangthai);
                stm.setInt(2, ID_monan);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static void UpdateSold(List<HoadonchitietModel> hdcts) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	for (HoadonchitietModel hdct : hdcts) {
            		int sold = 0;
            		String sql = "SELECT daban FROM monan WHERE ID_monan = ?";            	
                	stm = conn.prepareStatement(sql);
                	stm.setInt(1, hdct.getID_monan());
                	rs = stm.executeQuery();
                    if (rs.next()) {
                    	sold += rs.getInt(1);
                    }
                    sql = "UPDATE monan SET daban = ? WHERE ID_monan = ?;";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, hdct.getSoluong() + sold);
                    stm.setInt(2, hdct.getID_monan());
                    stm.executeUpdate();
				}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    
    public static void Delete_Restore__Product(Integer ID_canteen, Integer ID_monan, String type) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = null;
            	if (type.equals("Delete")) {
            		sql = "UPDATE monan SET xoa = '1' WHERE ID_monan = ? AND ID_canteen = ?;";
            	} else if (type.equals("Restore")) {
            		sql = "UPDATE monan SET xoa = '0' WHERE ID_monan = ? AND ID_canteen = ?;";
				} else if (type.equals("DeleteForever")) {
					sql = "DELETE FROM monan WHERE ID_monan = ? AND ID_canteen = ?;";
				}
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID_monan);
                stm.setInt(2, ID_canteen);
                stm.executeUpdate();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
    }
    
    public static boolean CheckProduct(int ID_monan, int ID_canteen) throws SQLException, Exception {
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "SELECT * FROM monan WHERE ID_monan = ? AND ID_canteen = ?;";
            	stm = conn.prepareStatement(sql);
            	stm.setInt(1, ID_monan);
            	stm.setInt(2, ID_canteen);
            	rs = stm.executeQuery();
                if (rs.next()) {
                	return true;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace(); 
        } finally {
        	connectDB.closeConnection(conn, stm, rs);
        }
        return false;
    }
    
    public static List<MonAnModel> Locmonan(int ID_canteen,String[] loaithucans, String[] thanhphans, String[] huongvis, Double giabatdau, Double giaketthuc) throws SQLException, Exception {
    	List<MonAnModel> result = new ArrayList<MonAnModel>(); 
        try {
            conn = connectDB.getConnection();
            if (conn != null) {
            	String sql = "";
            	if (loaithucans != null) {
            		sql = "SELECT monan.ID_monan, monan.tenmon, monan.mota, monan.thanhphan, monan.huongvi, monan.giacu,"
                			+ " monan.giahientai, monan.hinhanhchinh, monan.daban FROM monan"
                			+ " JOIN monan_loaithucan ON monan.ID_monan = monan_loaithucan.ID_monan"
                			+ " WHERE monan_loaithucan.ID_loaithucan = ? AND monan.ID_canteen = ? AND monan.trangthai = 1 AND monan.xoa = 0 ORDER BY monan.ID_monan DESC;";
                	for (String loaithucan : loaithucans) {
                		stm = conn.prepareStatement(sql);
                    	stm.setInt(1, Integer.valueOf(loaithucan));
                    	stm.setInt(2, ID_canteen);
                    	rs = stm.executeQuery();
                    	while (rs.next()) {
                    		result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
                        			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
						}
					}
				}
                sql = "SELECT ID_monan, tenmon, mota, thanhphan, huongvi, giacu, giahientai, hinhanhchinh, daban FROM monan WHERE ID_canteen = ? AND trangthai = 1 AND xoa = 0 ORDER BY ID_monan DESC;";
               	stm = conn.prepareStatement(sql);
               	stm.setInt(1, ID_canteen);
                rs = stm.executeQuery();
              	while (rs.next()) {
              		if (thanhphans != null) {
              			for (String thanhphan : thanhphans) {
                       		if (Normalizer.normalize(rs.getString(4).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").contains(thanhphan)) {
                       			result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
                               			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
                   				break;
    						} else if (thanhphan.equals("hai san")) {
								String[] haisans = {"cá", "tôm", "mực", "nghêu", "hàu", "sò", "ốc", "cua"};
								for (String haisan : haisans) {
									if (rs.getString(4).toLowerCase().replaceAll("\\p{M}", "").contains(haisan)) {
		                       			result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
		                               			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
		                   				break;
		    						}
								}
							} else if (thanhphan.equals("rau cu")) {
								String[] raucus = {"rau", "củ", "quả", "cải", "cà", "chua", "diếp", "khoai", "hành", "dứa", 
										"bí", "ngô", "cần", "tây", "lách", "xà", "mồng", "ngót", "râm", "dền"};
								for (String raucu : raucus) {
									if (rs.getString(4).toLowerCase().replaceAll("\\p{M}", "").contains(raucu)) {
			                   			result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
			                           			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
			               				break;
									}
								}
							}
                   		}
					}
              		if (huongvis != null) {
              			for (String huongvi : huongvis) {
                   			if (Normalizer.normalize(rs.getString(5).toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").contains(huongvi)) {
                   				result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
                               			rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
                   				break;
    						} 
                   		}
					}
               		if (giabatdau != null && giaketthuc != null && rs.getDouble(7) > giabatdau && rs.getDouble(7) < giaketthuc) {
               		    result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
               		        rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
               		}
               		else if (giabatdau != null && giaketthuc == null && rs.getDouble(7) > giabatdau) {
               		    result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
               		        rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
               		}
               		else if (giabatdau == null && giaketthuc != null && rs.getDouble(7) < giaketthuc) {
               		    result.add(new MonAnModel(rs.getInt(1), ID_canteen, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6),
               		        rs.getDouble(7), null, Base64.getDecoder().decode(rs.getBytes(8)), -1, rs.getInt(9), -1));
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
    
    public static boolean isLunarFirstOrFifteenth() {
        Calendar calendar = new GregorianCalendar();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth == 1 || dayOfMonth == 15 || dayOfMonth == 30 || dayOfMonth == 14;
    }

    
    public static void main(String[] args) throws SQLException, Exception {
    	MonAnModel monAnModel = MonAnDAO.getMonan(20,127);
		System.out.println(monAnModel.getHinhanhchinh().toString());
	}
}
