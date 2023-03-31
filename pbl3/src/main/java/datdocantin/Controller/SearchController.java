package datdocantin.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.SearchHistoryDAO;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
//		session.setMaxInactiveInterval(-1);
        try {
        	String idkh = request.getParameter("id_user");
        	String noidung = request.getParameter("txtSearch");
            if (idkh!=null) { 
            	SearchHistoryDAO.addSearchHistory(String.valueOf(SearchHistoryDAO.getLastId()+1),idkh, noidung);
            	List<String> searchHistory = SearchHistoryDAO.getSearchHistory(idkh);
            	session.setAttribute("searchHistory", searchHistory);
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}

}
