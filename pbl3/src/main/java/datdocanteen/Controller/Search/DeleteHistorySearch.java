package datdocanteen.Controller.Search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HistorySearchDAO;

@WebServlet("/delHistorySearch")
public class DeleteHistorySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteHistorySearch() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
        try {
        	Integer ID_nguoidung = null;
        	if (session.getAttribute("khachhang") != null) {
        		ID_nguoidung = (int)session.getAttribute("ID_khachhang");
        	} else if (session.getAttribute("canteen") != null) {
        		ID_nguoidung = (int)session.getAttribute("ID_canteen");
			}
        	HistorySearchDAO.deleteSearchHistory(Integer.valueOf(request.getParameter("id")), ID_nguoidung);
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        }  
        response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}
 
}
