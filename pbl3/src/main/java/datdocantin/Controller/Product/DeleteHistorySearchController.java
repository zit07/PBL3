package datdocantin.Controller.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.HistorySearchDAO;

@WebServlet("/delHistorySearch")
public class DeleteHistorySearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteHistorySearchController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	HistorySearchDAO.deleteSearchHistory(request.getParameter("id"));
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
