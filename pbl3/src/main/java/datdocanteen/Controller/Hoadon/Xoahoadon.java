package datdocanteen.Controller.Hoadon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HoadonDAO;

@WebServlet("/Xoahoadon")
public class Xoahoadon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Xoahoadon() {
        super();
    } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			int ID_hoadon = HoadonDAO.getID_Hoadon((int)session.getAttribute("ID_khachhang"));
			if (HoadonDAO.getTrangthai(ID_hoadon).equals("chưa thanh toán")) {
				HoadonDAO.XoaHoadon(ID_hoadon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
