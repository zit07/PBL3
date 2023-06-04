package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.LoaithucanDAO;

@WebServlet("/xoaloaithucan")
public class XoaLoaithucan extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public XoaLoaithucan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true); 
		try {
			Integer ID_canteen = (Integer)session.getAttribute("ID_canteen"); 
			Integer ID_loaithucan = Integer.valueOf(request.getParameter("id_loaithucan")); 
			if (ID_canteen != null & ID_loaithucan != null) {
				LoaithucanDAO.xoaLoaithucan(ID_loaithucan, ID_canteen);
				session.setAttribute("loaithucan", LoaithucanDAO.getListLoaithucan(ID_canteen));
				response.sendRedirect(request.getContextPath() + "/loaithucan");
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} catch (Exception e) {
			log("error at login servlet: " + e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}
}
