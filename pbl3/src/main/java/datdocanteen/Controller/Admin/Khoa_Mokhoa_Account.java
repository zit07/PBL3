package datdocanteen.Controller.Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.AccountDAO;



@WebServlet({"/khoa", "/mokhoa"})
public class Khoa_Mokhoa_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Khoa_Mokhoa_Account() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			Integer ID_account = Integer.valueOf(request.getParameter("ID_account"));
			if (ID_account != null) {
				String tag = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
				try {
					AccountDAO.Khoa_Mokhoa(ID_account, tag);
					String role = AccountDAO.getRole(ID_account);
					if (tag.equals("khoa")) {
						if (role.equals("customer")) {
							response.sendRedirect(request.getContextPath() + "/quanlykhachhang");
						} else if (role.equals("canteen")){
							response.sendRedirect(request.getContextPath() + "/quanlycanteen");
						} else {
							response.sendRedirect(request.getContextPath());
						}
					} else if (tag.equals("mokhoa")) { 
						if (role.equals("customer")) {
							response.sendRedirect(request.getContextPath() + "/khachhanglock");
						} else if (role.equals("canteen")){
							response.sendRedirect(request.getContextPath() + "/canteenlock");
						} else {
							response.sendRedirect(request.getContextPath());
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
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
