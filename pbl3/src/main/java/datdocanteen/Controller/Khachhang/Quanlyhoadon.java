package datdocanteen.Controller.Khachhang;

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

import datdocantin.Dao.BankDAO;
import datdocantin.Dao.HoadonDAO;
import datdocantin.Dao.HoadonchitietDAO;
import datdocantin.Model.HoadonModel;
import datdocantin.Model.KhachHangModel;

@WebServlet({"/hoadonchothanhtoan", "/hoadonchoxacnhan", "/Donhangdamua", "/Donhangdahuy"})
public class Quanlyhoadon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Quanlyhoadon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
		if (khachhang != null) { 
			try {
				String uri = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
				String tag = "";
				if (uri.equals("hoadonchothanhtoan")) { 
					tag = "chuathanhtoan";
				} else if (uri.equals("hoadonchoxacnhan")) {
					tag = "dangkiemtra";
				} else if (uri.equals("Donhangdamua")) {
					tag = "damua";
				} else if (uri.equals("Donhangdahuy")) {
					tag = "dahuy";
				} 
				int ID_khachhang = khachhang.getID_khachhang();
				LocalDate ngay = request.getParameter("ngay") != null ? Date.valueOf(request.getParameter("ngay")).toLocalDate() :  LocalDate.now();
				List<HoadonModel> hoadons = HoadonDAO.getHDforkhachhang(ID_khachhang, tag, ngay);
				if (hoadons != null) { 
					session.setAttribute("tag", tag);
					session.setAttribute("ngayloc", ngay); 
					session.setAttribute("hoadons", hoadons);
					session.setAttribute("bank", BankDAO.getBank(khachhang.getID_canteen()));
					session.setAttribute("hoadonchitiets", HoadonchitietDAO.getHDCTchonhieuHD(hoadons));
				} else {
					session.removeAttribute("hoadons");
					session.removeAttribute("hoadonchitiets"); 
				}
				request.getRequestDispatcher("view/customer-quanlyhoadonpage.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
