package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.KhachhangDAO;


@WebServlet("/ChooseCanteen")
public class ChooseCanteenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChooseCanteenController() {
        super();
    } 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Integer ID_khachhang = Integer.valueOf(request.getParameter("id_user"));
		Integer ID_canteen = Integer.valueOf(request.getParameter("id_canteen"));
		try {
			KhachhangDAO.ChangeCanteen(ID_khachhang, ID_canteen);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.removeAttribute("showcanteen");
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
