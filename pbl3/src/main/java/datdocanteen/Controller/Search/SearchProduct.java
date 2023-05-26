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
	    Integer ID_nguoidung = null;
    	if (session.getAttribute("khachhang") != null) {
    		ID_nguoidung = (int)session.getAttribute("ID_khachhang");
    	} else if (session.getAttribute("canteen") != null) {
    		ID_nguoidung = (int)session.getAttribute("ID_canteen");
		}
	    if (txtSearch != null && !txtSearch.isEmpty()) {
	        session.setAttribute("txtSearch", txtSearch);
	        if (ID_nguoidung != null) {
	            if (!txtSearch.equals(HistorySearchDAO.getLastNoidung(ID_nguoidung))) {
	                HistorySearchDAO.addSearchHistory(new LichsutimkiemModel(getNewIDforTable.getNewID("lichsutimkiem"), ID_nguoidung, txtSearch));
	            }
	        }
	    }
	    response.sendRedirect(request.getContextPath());
	}
}
