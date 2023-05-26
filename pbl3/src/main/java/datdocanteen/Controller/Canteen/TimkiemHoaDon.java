package datdocanteen.Controller.Canteen;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HoadonDAO;
import datdocantin.Dao.HoadonchitietDAO;
import datdocantin.Model.HoadonModel;

@WebServlet("/timkiemhoadon")
public class TimkiemHoaDon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TimkiemHoaDon() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("ID_canteen") != null) {
			session.removeAttribute("hoadons");
			session.removeAttribute("hoadonchitiets");
			request.getRequestDispatcher("view/canteen-timkiemhoadonpage.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try { 
			int ID_canteen = (int)session.getAttribute("ID_canteen");
			String txtsearch = request.getParameter("txtSearch");
		    List<HoadonModel> hoadon =  HoadonDAO.Timkiem(ID_canteen, txtsearch);
			session.setAttribute("hoadons", hoadon);
			session.setAttribute("txtSearchHD", txtsearch);
			session.setAttribute("hoadonchitiets", HoadonchitietDAO.getHDCTchonhieuHD(hoadon));
			request.getRequestDispatcher("view/canteen-timkiemhoadonpage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
