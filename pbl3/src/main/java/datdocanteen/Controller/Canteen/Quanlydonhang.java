package datdocanteen.Controller.Canteen;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.HoadonDAO;
import datdocanteen.Dao.HoadonchitietDAO;
import datdocanteen.Model.HoadonModel;

@WebServlet({"/quanlydonhang", "/donchoxacnhan", "/dondangchuanbi", "/dondahoantat"})
public class Quanlydonhang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Quanlydonhang() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Integer ID_canteen = (Integer)session.getAttribute("ID_canteen");
		if (ID_canteen != null) {
			try {
				String uri = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
				String tag = "";  
				if ((uri.equals("quanlydonhang")) || (uri.equals("donchoxacnhan"))) {
					tag = "choxacnhan";
				} else if (uri.equals("dondangchuanbi")) {
					tag = "dangchuanbi";
				} else if (uri.equals("dondahoantat")) {
					tag = "dahoantat";
				} 
				LocalDate ngay = null;
				if (request.getParameter("ngay") != null) {
					ngay = Date.valueOf(request.getParameter("ngay")).toLocalDate();
				} else if (session.getAttribute("ngayloc") != null) {
					ngay = (LocalDate)session.getAttribute("ngayloc");
				} else {
					ngay = LocalDate.now();
				}
				List<HoadonModel> hoadons = HoadonDAO.getHoadonforCanteen(ID_canteen, ngay, tag); 
				session.setAttribute("hoadons", hoadons);
				session.setAttribute("hoadonchitiets", HoadonchitietDAO.getHDCTchonhieuHD(hoadons));
				session.setAttribute("tag", tag);
				session.setAttribute("ngayloc", ngay);
				request.getRequestDispatcher("view/canteen-quanlydonhangpage.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
