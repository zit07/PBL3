package datdocantin.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.KhachHangModel;


@WebServlet({"/quanlycanteen", "/quanlykhachhang", "/timkiem", "/report", "/canteenlock", "/khachhanglock"})
public class Quanlynguoidung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Quanlynguoidung() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			String tag = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
			try {
				if (tag.equals("quanlycanteen")) { 
					List<CanteenModel> listCanteen = CanteenDAO.getAllCanteen(0, -1, -1, -1);
					session.setAttribute("listCanteen", listCanteen);
					session.setAttribute("listDiachi", DiachiDAO.getListDiachi(listCanteen));
					session.setAttribute("listKhachHangOfCanteen", KhachhangDAO.getListKhachHangOfCanteen(listCanteen));
				} else if (tag.equals("quanlykhachhang")) {
					List<KhachHangModel> listkhachhang = KhachhangDAO.getInfoAll(0);
					session.setAttribute("listKhachHang", listkhachhang);
					session.setAttribute("listnamecanteen", CanteenDAO.getListNameCanteen(listkhachhang));
				} else if (tag.equals("canteenlock")) {
					List<CanteenModel> listCanteen =  CanteenDAO.getAllCanteen(1, -1, -1, -1);
					session.setAttribute("listCanteen", listCanteen);
					session.setAttribute("listDiachi", DiachiDAO.getListDiachi(listCanteen));
					session.setAttribute("listKhachHangOfCanteen", KhachhangDAO.getListKhachHangOfCanteen(listCanteen));
				} else if (tag.equals("khachhanglock")) {
					List<KhachHangModel> listkhachhang = KhachhangDAO.getInfoAll(1);
					session.setAttribute("listKhachHang", listkhachhang);
					session.setAttribute("listnamecanteen", CanteenDAO.getListNameCanteen(listkhachhang));
				} else if (tag.equals("timkiem")) {
					session.setAttribute("listKhachHang", null);
					session.setAttribute("listnamecanteen", null);
					session.setAttribute("listCanteen", null);
					session.setAttribute("listDiachi", null);
					session.setAttribute("listKhachHangOfCanteen", null);
				}
				session.setAttribute("tag", tag);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			request.getRequestDispatcher("view/admin-homepage.jsp").forward(request, response);				
		} else {
			request.getRequestDispatcher("view/homepage.jsp").forward(request, response);				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			String tag = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
			try {
				if (tag.equals("quanlycanteen")) {
					Integer tinh = Integer.valueOf(request.getParameter("tinh"));
					Integer huyen = Integer.valueOf(request.getParameter("huyen"));
					Integer xa = Integer.valueOf(request.getParameter("xa"));
					if (tag.equals("quanlycanteen")) { 
						List<CanteenModel> listCanteen = CanteenDAO.getAllCanteen(0, tinh, huyen, xa);
						System.out.println("asdasdasdasdasdasdasd"+listCanteen.size());
						session.setAttribute("listCanteen", listCanteen);
						session.setAttribute("listDiachi", DiachiDAO.getListDiachi(listCanteen));
						session.setAttribute("listKhachHangOfCanteen", KhachhangDAO.getListKhachHangOfCanteen(listCanteen));
					} 
				} else if (tag.equals("timkiem")) {
					String txtSearch = request.getParameter("txtSearch");
					session.setAttribute("txtSearch", txtSearch);
					List<KhachHangModel> listkhachhang = KhachhangDAO.SearchKhachhang(txtSearch);
					session.setAttribute("listKhachHang", listkhachhang);
					session.setAttribute("listnamecanteen", CanteenDAO.getListNameCanteen(listkhachhang));
					
					List<CanteenModel> listCanteen = CanteenDAO.SearchCanteen(txtSearch, null);
					session.setAttribute("listCanteen", listCanteen);
					session.setAttribute("listDiachi", DiachiDAO.getListDiachi(listCanteen));
					session.setAttribute("listKhachHangOfCanteen", KhachhangDAO.getListKhachHangOfCanteen(listCanteen));
				}
				session.setAttribute("tag", tag);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			request.getRequestDispatcher("view/admin-homepage.jsp").forward(request, response);				
		} else {
			request.getRequestDispatcher("view/homepage.jsp").forward(request, response);				
		}
	}

}
