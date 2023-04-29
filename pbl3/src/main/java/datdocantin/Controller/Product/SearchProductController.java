package datdocantin.Controller.Product;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Service.getNewIDforTable;

@WebServlet("/search") 
public class SearchProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchProductController() {
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
	    String id = request.getParameter("id_user");
	    String txtSearch = request.getParameter("txtSearch");
	    if (txtSearch != null && !txtSearch.isEmpty()) {
	        HttpSession session = request.getSession();
	        session.setAttribute("txtSearch", txtSearch);
	        if (id != null && !id.isEmpty()) {
	            String lastNoidung = HistorySearchDAO.getLastNoidung(id);
	            if (!txtSearch.equals(lastNoidung)) {
	                HistorySearchDAO.addSearchHistory(getNewIDforTable.getNewID("lichsutimkiem"), id, txtSearch);
	            }
	        }
	    }
	    response.sendRedirect(request.getContextPath());
	}


}
