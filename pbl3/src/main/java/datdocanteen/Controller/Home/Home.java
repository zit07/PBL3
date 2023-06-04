package datdocanteen.Controller.Home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.BankDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.CartDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.GiohoatdongDAO;
import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Dao.LoaithucanDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.AccountModel;
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
		        session.removeAttribute("ListMonanLoc");
		        session.removeAttribute("LoaithucanSelect");
               	session.removeAttribute("ThanhphanSelect");
               	session.removeAttribute("HuongviSelect");
              	session.removeAttribute("giabatdau");
               	session.removeAttribute("giaketthuc");
				session.removeAttribute("txtSearch");
				session.setAttribute("tag", "moinhat");
				session.setAttribute("listMonan", MonAnDAO.SortMonanByTag(khachhang.getID_canteen(), null, "moinhat",null));
				session.setAttribute("khachhang", khachhang);
				session.setAttribute("TenCanteen", CanteenDAO.getNameCanteen(khachhang.getID_canteen()));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_khachhang));
				session.setAttribute("carts", carts);
				session.setAttribute("soluonggiohang", CartDAO.getSoluongmon(carts)); 
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan(khachhang.getID_canteen()));
				request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
			} 
			//canteen
			else if (session.getAttribute("canteen") != null) { 
				CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
				int ID_canteen = canteen.getID_canteen();
				session.setAttribute("tag", "tatca");
				session.setAttribute("listMonan", MonAnDAO.getListMonanByTag(ID_canteen, "tatca"));
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan(ID_canteen));
				session.setAttribute("diachi", DiachiDAO.getDiachi(canteen.getID_diachi()));
				session.setAttribute("listGiohoatdong", GiohoatdongDAO.getGiohoatdong(ID_canteen)); 
				session.setAttribute("canteen", CanteenDAO.getInfoCanteen(ID_canteen));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_canteen));
				session.setAttribute("bank", BankDAO.getBank(ID_canteen));
				request.getRequestDispatcher("view/canteen-quanlymonanpage.jsp").forward(request, response);
			} 
			//admin
			else if (session.getAttribute("admin") != null) {
				//Trả về sanh sách tất cả các tài khoản 
				List<AccountModel> listAccount = AccountDAO.getListAccount();
				//Trả về danh sách tất cả các thông tin canteen
				List<CanteenModel> listCanteen=CanteenDAO.getAllCanteen();
				//Trả về danh sách tất cả các tài khoản khách hàng
				List<KhachHangModel> listKhachHang=KhachhangDAO.getAllKhachHang();
				
				
				//trả về danh sách các canteen còn hoạt động dựa vao danh sách listAccount va listCanteen
				session.setAttribute("listCanteen", CanteenDAO.getListCanteenByTag(listCanteen,listAccount,"active"));
				
				//trả về danh sách khách hàng của canteen 
				//hàm getListKhachHangOfCanteen trả về kiêu List<List<KhachHangModel>>
				session.setAttribute("listKhachHangOfCanteen", KhachhangDAO.getListKhachHangOfCanteen(listKhachHang,listCanteen));
				//trả về sánh sách khách hàng đang hoạt động
				session.setAttribute("listKhachHang", KhachhangDAO.getListKhachhangByTag(listKhachHang,listAccount,"active"));
				
				//trả về danh sách các canteen bị khóa dựa vao danh sách listAccount va listCanteen
				session.setAttribute("listCanteen", CanteenDAO.getListCanteenByTag(listCanteen,listAccount,"locked"));
				//trả về sánh sách khách hàng bị khóa
				session.setAttribute("listKhachHang", KhachhangDAO.getListKhachhangByTag(listKhachHang,listAccount,"locked"));
				
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
