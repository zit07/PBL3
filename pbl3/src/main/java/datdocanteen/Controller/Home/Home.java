package datdocanteen.Controller.Home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.BankDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.CartDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.GiohoatdongDAO;
import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Dao.LoaithucanDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.CartModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Model.MonAnModel;


@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//khach hang 
			if (session.getAttribute("khachhang") != null) {
				KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
				int ID_khachhang = khachhang.getID_khachhang();
				khachhang = KhachhangDAO.getKhachhangInfo(ID_khachhang);
				List<CartModel> carts = CartDAO.getCarts(ID_khachhang);
				if (session.getAttribute("txtSearch")!=null) { 
					session.setAttribute("listMonan", MonAnDAO.KhachhangGetMenu(khachhang.getID_canteen(),(String)session.getAttribute("txtSearch")));
					request.setAttribute("txtSearch", (String)session.getAttribute("txtSearch"));
					session.removeAttribute("txtSearch");
				} else {
		            session.setAttribute("listMonan", MonAnDAO.KhachhangGetMenu(khachhang.getID_canteen(), null));
				}
				session.setAttribute("khachhang", khachhang);
				session.setAttribute("TenCanteen", CanteenDAO.getNameCanteen(khachhang.getID_canteen()));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_khachhang));
				session.setAttribute("carts", carts);
				session.setAttribute("soluonggiohang", CartDAO.getSoluongmon(carts)); 
				request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
			} 
			//canteen
			else if (session.getAttribute("canteen") != null) {
				CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
				int ID_canteen = canteen.getID_canteen();
				if (session.getAttribute("txtSearch")!=null) {
					session.setAttribute("listMonan", MonAnDAO.CanteenGetMenu(ID_canteen,(String)session.getAttribute("txtSearch")));
					request.setAttribute("Search", (String)session.getAttribute("txtSearch"));
					session.removeAttribute("txtSearch");
				} else {
					session.removeAttribute("txtSearch");
					session.setAttribute("tag", "tatca");
					session.setAttribute("listMonan", MonAnDAO.getListMonanByTag(ID_canteen, "tatca"));
				}
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan());
				session.setAttribute("diachi", DiachiDAO.getDiachi(canteen.getID_diachi()));
				session.setAttribute("listGiohoatdong", GiohoatdongDAO.getGiohoatdong(ID_canteen)); 
				session.setAttribute("canteen", CanteenDAO.getInfoCanteen(ID_canteen));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_canteen));
				session.setAttribute("bank", BankDAO.getBank(ID_canteen));
				request.getRequestDispatcher("view/cantin-quanlymonanpage.jsp").forward(request, response);
			} 
			//admin
			else if (session.getAttribute("admin") != null) {
//				//trả về danh sách tất cả các tài khoản
//				List<AccountDAO> listAccount = AccountDAO.getListAccount();
//				//trả về danh sách tất cả các thông tin canteen 
//				List<CanteenModel> listCanteen = CanteenDAO.getAllCanteen();
//				//trả về danh sách tất cả các tài khoản khachhang 
//				List<KhachHangModel> listKhachhang = KhachhangDAO.getAllKhachhang();
//				
//				//trả về danh sách các canteen còn hoạt động dự vào danh sách listAccount và listAccount
//				session.setAttribute("listCanteen", CanteenDAO.getListCanteenByTag(listCanteen, listAccount, "chua khoa")); 
				//trả về danh sách khách hàng của canteen 
				//hàm getListKhachhangOfCanteen trả về kiểu List<List<KhachhangModel>>
//				session.setAttribute("listKhachhangOfCanteen", KhachhangDAO.getListKhachhangOfCanteen(listKhachhang, listCanteen)); 
//				//trả về danh sách khách hàng dang hoat dong
//				session.setAttribute("listKhachhang", KhachhangDAO.getListKhachhangByTag(listKhachhang, listAccount, "chua khoa")); 
//				//trả về danh sách các canteen bị khoá
//				session.setAttribute("listCanteenLock", CanteenDAO.getListCanteenByTag(listCanteen, listAccount, "da khoa"));
//				//trả về danh sách khách hàng bị khoá
//				session.setAttribute("listKhachhangLock", KhachhangDAO.getListKhachhangByTag(listKhachhang, listAccount, "da khoa"));  
				request.getRequestDispatcher("view/admin-homepage.jsp").forward(request, response);
				
			}
			//trang chu
			else {
				if (session.getAttribute("menu") != null) {
					request.setAttribute("menu", (List<MonAnModel>)session.getAttribute("menu"));
					session.removeAttribute("menu");
				}
				request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
