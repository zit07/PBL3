package datdocantin.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.CanteenModel;


@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("canteen") != null) {
			CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
			try {
				session.setAttribute("listMonan", MonAnDAO.getListMonan(canteen.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("view/cantin-homepage.jsp").forward(request, response);
		} else if (session.getAttribute("admmin") != null) {
			request.getRequestDispatcher("view/admin-homepage.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
