package datdocantin.Controller.Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Dao.CanteenDAO;

@WebServlet("/Menu")
public class GetMenuCanteen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetMenuCanteen() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		session.removeAttribute("Canteen");
		try {
			String idcanteen = request.getParameter("id_canteen");
			session.setAttribute("menu", MonAnDAO.getListMonanforKhach(idcanteen));
			session.setAttribute("Canteen", CanteenDAO.getInfoCanteen(idcanteen));
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
