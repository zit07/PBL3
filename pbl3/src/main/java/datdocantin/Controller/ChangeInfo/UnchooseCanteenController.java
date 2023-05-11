package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.KhachhangDAO;


@WebServlet("/UnchooseCanteen")
public class UnchooseCanteenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UnchooseCanteenController() {
        super();
    } 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Integer ID_khachhang = Integer.valueOf(request.getParameter("id_user"));
		try {
			KhachhangDAO.ChangeCanteen(ID_khachhang, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
