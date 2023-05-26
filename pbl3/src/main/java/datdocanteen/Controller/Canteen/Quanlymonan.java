package datdocanteen.Controller.Canteen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;

@WebServlet({"/monandangban", "/monanngungban", "/monandaxoa"})
public class Quanlymonan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quanlymonan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer ID_canteen = (Integer)session.getAttribute("ID_canteen");
		if (ID_canteen != null) {
			try {
				String uri = request.getRequestURI();  
				String tag = "";
				if (uri.equals("monandangban")) {
					tag = "dangban";
				} else if (uri.equals("monanngungban")) {
					tag = "ngungban";
				} else if (uri.equals("monandaxoa")) {
					tag = "daxoa";
				}  
				session.setAttribute("tag", tag);
				session.setAttribute("listMonan", MonAnDAO.getListMonanByTag(ID_canteen, tag));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("view/cantin-quanlymonanpage.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
