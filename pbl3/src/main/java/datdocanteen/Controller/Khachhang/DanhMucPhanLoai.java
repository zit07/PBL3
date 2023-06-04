package datdocanteen.Controller.Khachhang;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.KhachHangModel;
import datdocantin.Model.MonAnModel;

@WebServlet({"/moinhat", "/banchay", "/giamdan", "/tangdan"})
public class DanhMucPhanLoai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhMucPhanLoai() {
        super();
    }
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
		if (khachhang != null) { 
			String tag = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
			try {		
				if (session.getAttribute("ListMonanLoc")!=null) { 
					List<MonAnModel> monans = (List<MonAnModel>)session.getAttribute("ListMonanLoc");
					List<List<MonAnModel>> monanss = MonAnDAO.SortMonanByTag(khachhang.getID_canteen(), (String)session.getAttribute("txtSearch"), tag, monans);
					session.setAttribute("listMonan", monanss);
				} else {
					session.setAttribute("listMonan", MonAnDAO.SortMonanByTag(khachhang.getID_canteen(), (String)session.getAttribute("txtSearch"), tag, null));
				}
				session.setAttribute("tag", tag);
				request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
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
