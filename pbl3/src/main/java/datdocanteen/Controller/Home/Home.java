package datdocanteen.Controller.Home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.BankDAO;
import datdocanteen.Dao.CanteenDAO;
import datdocanteen.Dao.CartDAO;
import datdocanteen.Dao.DiachiDAO;
import datdocanteen.Dao.GiohoatdongDAO;
import datdocanteen.Dao.HistorySearchDAO;
import datdocanteen.Dao.KhachhangDAO;
import datdocanteen.Dao.LoaithucanDAO;
import datdocanteen.Dao.MonAnDAO;
import datdocanteen.Model.CanteenModel;
import datdocanteen.Model.CartModel;
import datdocanteen.Model.KhachHangModel;
import datdocanteen.Model.MonAnModel;


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
				session.setAttribute("listMonan", MonAnDAO.SortMonanByTag(khachhang, null, "moinhat",null));
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
				session.setAttribute("diachi", DiachiDAO.getDiachi(ID_canteen));
				session.setAttribute("listGiohoatdong", GiohoatdongDAO.getGiohoatdong(ID_canteen)); 
				session.setAttribute("canteen", CanteenDAO.getInfoCanteen(ID_canteen));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_canteen));
				session.setAttribute("bank", BankDAO.getBank(ID_canteen));
				request.getRequestDispatcher("view/canteen-quanlymonanpage.jsp").forward(request, response);
			} 
			//admin
			else if (session.getAttribute("admin") != null) {
				session.setAttribute("tag", "quanlycanteen");
				response.sendRedirect(request.getContextPath() + "/quanlycanteen");
				
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
