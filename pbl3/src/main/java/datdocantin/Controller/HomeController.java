package datdocantin.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.GiohoatdongDAO;
import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Dao.LoaithucanDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Model.MonAnModel;


@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeController() {
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
				request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
			} 
			//canteen
			else if (session.getAttribute("canteen") != null) {
				CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
				int ID_canteen = canteen.getID_canteen();
				if (session.getAttribute("txtSearch")!=null) {
					session.setAttribute("ResultSearch", MonAnDAO.CanteenGetMenu(ID_canteen,(String)session.getAttribute("txtSearch")));
					request.setAttribute("Search", (String)session.getAttribute("txtSearch"));
					session.removeAttribute("txtSearch");
				} else {
					session.removeAttribute("txtSearch");
					session.removeAttribute("ResultSearch");
					List<MonAnModel> listMonan = MonAnDAO.CanteenGetMenu(ID_canteen, null);
					request.setAttribute("danhmuc", (String)session.getAttribute("danhmuc"));
					session.setAttribute("listMonan", MonAnDAO.getListMonanByTag(listMonan, "tat ca"));
					session.setAttribute("listMonan_dangban", MonAnDAO.getListMonanByTag(listMonan, "dang ban"));
					session.setAttribute("listMonan_ngungban", MonAnDAO.getListMonanByTag(listMonan, "ngung ban"));
					session.setAttribute("listMonan_daxoa", MonAnDAO.getListMonanByTag(listMonan, "da xoa"));
				}
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan());
				session.setAttribute("diachi", DiachiDAO.getDiachi(canteen.getID_diachi()));
				session.setAttribute("listGiohoatdong", GiohoatdongDAO.getGiohoatdong(ID_canteen)); 
				session.setAttribute("canteen", CanteenDAO.getInfoCanteen(ID_canteen));
				session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(ID_canteen));
				request.getRequestDispatcher("view/cantin-homepage.jsp").forward(request, response);
			} 
			//admin
			else if (session.getAttribute("admin") != null) {
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
