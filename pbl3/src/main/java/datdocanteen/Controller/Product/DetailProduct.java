package datdocanteen.Controller.Product;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.DiachiDAO;
import datdocanteen.Dao.GiohoatdongDAO;
import datdocanteen.Dao.MonAnDAO;
import datdocanteen.Model.GiohoatdongModel;
import datdocanteen.Model.KhachHangModel;
import datdocanteen.Model.MonAnModel;

@WebServlet("/productdetail")
public class DetailProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); 
		HttpSession session = request.getSession(true);
		Integer ID_monan = request.getParameter("id_monan") != null ? Integer.valueOf(request.getParameter("id_monan")) : null;
		try {
			if (session.getAttribute("khachhang") != null && ID_monan != null) {
				KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
				MonAnModel monAnModel = MonAnDAO.getMonan(ID_monan, khachhang.getID_canteen());
				GiohoatdongModel giohoatdong = GiohoatdongDAO.getGiohoatdongDate(khachhang.getID_canteen(),LocalDate.now().getDayOfWeek().getValue()+1);
				boolean check = LocalTime.now().isAfter(LocalTime.parse(giohoatdong.getGiomocua())) && LocalTime.now().isBefore(LocalTime.parse(giohoatdong.getGiodongcua()));
				session.setAttribute("check", check); 
				session.setAttribute("giohoatdong", giohoatdong); 
				session.setAttribute("monan", monAnModel); 
				session.setAttribute("diachicanteen", DiachiDAO.getDiachi(khachhang.getID_canteen()));
				request.getRequestDispatcher("view/customer-detailproduct.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath());
			} 
		} catch (Exception e) {
            e.printStackTrace();
        } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
