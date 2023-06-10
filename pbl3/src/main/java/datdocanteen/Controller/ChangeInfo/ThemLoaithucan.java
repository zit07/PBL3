package datdocanteen.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.LoaithucanDAO;
import datdocanteen.Model.LoaithucanModel;
import datdocanteen.Service.getNewIDforTable;

@WebServlet("/loaithucan")
public class ThemLoaithucan extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThemLoaithucan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true); 
		try {
			Integer ID_canteen = (Integer)session.getAttribute("ID_canteen"); 
			if (ID_canteen != null) {
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan(ID_canteen));
				request.getRequestDispatcher("view/canteen-quanlyloaithucan.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} catch (Exception e) {
			log("error at login servlet: " + e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);  
		try {
			Integer ID_canteen = (Integer)session.getAttribute("ID_canteen"); 
			if (ID_canteen != null) {
				String tenloaithucan = request.getParameter("txtloaithucan");
				tenloaithucan = tenloaithucan.substring(0, 1).toUpperCase() + tenloaithucan.substring(1).toLowerCase();
				if (LoaithucanDAO.CheckLoaithucanNotExist(ID_canteen, tenloaithucan)) {
					LoaithucanDAO.addLoaithucan(new LoaithucanModel(getNewIDforTable.getNewID("loaithucan"), ID_canteen, tenloaithucan));
				}
				response.sendRedirect(request.getContextPath() + "/loaithucan");
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} catch (Exception e) {
			log("error at login servlet: " + e.toString());
		}
	}

}
