package datdocanteen.Controller.Search;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.KhachHangModel;
import datdocantin.Model.LichsutimkiemModel;
import datdocantin.Service.getNewIDforTable;

@WebServlet("/search") 
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchProduct() {
        super();

    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) { 
			e.printStackTrace();
		}
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
	    String txtSearch = request.getParameter("txtSearch");
    	if (session.getAttribute("khachhang") != null) {
    		KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
        	if (txtSearch != null && !txtSearch.isEmpty()) {
    	        if (!txtSearch.equals(HistorySearchDAO.getLastNoidung(khachhang.getID_khachhang()))) {
    	            HistorySearchDAO.addSearchHistory(new LichsutimkiemModel(getNewIDforTable.getNewID("lichsutimkiem"), khachhang.getID_khachhang(), txtSearch));
    	        }
    	        session.setAttribute("listMonan", MonAnDAO.SortMonanByTag(khachhang.getID_canteen(),txtSearch,"moinhat",null));
    	        session.setAttribute("txtSearch", txtSearch);
    	        session.setAttribute("tag", "moinhat");
    			session.removeAttribute("LoaithucanSelect");
            	session.removeAttribute("ThanhphanSelect");
            	session.removeAttribute("HuongviSelect");
            	session.removeAttribute("giabatdau");
            	session.removeAttribute("giaketthuc");
            	request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
    	    }
    	} else if (session.getAttribute("ID_canteen") != null) {
    		int ID_canteen = (int)session.getAttribute("ID_canteen"); 
    		session.setAttribute("listMonan", MonAnDAO.CanteenGetMenu(ID_canteen,(String)session.getAttribute("txtSearch")));
			request.setAttribute("Search", (String)session.getAttribute("txtSearch"));
			session.removeAttribute("txtSearch");
		} else {
		    response.sendRedirect(request.getContextPath());
		}
	}
}
