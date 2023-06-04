package datdocantin.Controller.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.KhachHangModel;



@WebServlet("/Locmonan")
public class LocmonanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LocmonanController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); 
		HttpSession session = request.getSession(true);
        try {
        	KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
        	if (khachhang != null) {
        		String[] Loaithucans = request.getParameterValues("txtloaimonan"); 
            	String[] Thanhphans  = request.getParameterValues("txtThanhphan");
            	String[] Huongvis = request.getParameterValues("txtHuongvi");
            	Double giabatdau = request.getParameter("GiaStart") != "" ? Double.parseDouble(request.getParameter("GiaStart")) / 1000 : null; 
            	Double giaketthuc = request.getParameter("GiaEnd") != "" ? Double.parseDouble(request.getParameter("GiaEnd")) / 1000 : null; 
            	if ((Loaithucans != null) || (Thanhphans != null) || (Huongvis != null) || (giabatdau != null) || (giaketthuc != null)) {
                	session.setAttribute("ListMonanLoc", MonAnDAO.Locmonan(khachhang.getID_canteen(), Loaithucans, Thanhphans, Huongvis, giabatdau, giaketthuc));
                	session.setAttribute("LoaithucanSelect", Loaithucans);
                	session.setAttribute("ThanhphanSelect", Thanhphans);
                	session.setAttribute("HuongviSelect", Huongvis);
                	session.setAttribute("giabatdau", giabatdau);
                	session.setAttribute("giaketthuc", giaketthuc);
                	session.removeAttribute("txtSearch");
    			}
			}
        	response.sendRedirect(request.getContextPath() + "/moinhat");
        } catch (Exception e) {
            e.printStackTrace();
        }  
	}

}
