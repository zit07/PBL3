package datdocanteen.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.KhachhangDAO;


@WebServlet("/ChangeCanteen")
public class ChangeCanteenChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeCanteenChoose() {
        super();
    } 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		if (session.getAttribute("ID_khachhang") != null) {
			Integer ID_khachhang = (int)session.getAttribute("ID_khachhang");
			Integer ID_canteen = request.getParameter("id_canteen") != null ? Integer.valueOf(request.getParameter("id_canteen")) : null; 
			try { 
				KhachhangDAO.ChangeCanteen(ID_khachhang, ID_canteen);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
		session.removeAttribute("showcanteen");
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
