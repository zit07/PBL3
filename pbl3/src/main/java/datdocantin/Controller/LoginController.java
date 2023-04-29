package datdocantin.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.AccountModel;
import datdocantin.Model.KhachHangModel;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Trả về trang đăng nhập 
    	request.setAttribute("display_form__login", "flex");
    	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        String url = "view/homepage.jsp";
        try {
        	String sdt = request.getParameter("txtSdt");
        	String password = request.getParameter("txtPassword"); 
            String id = AccountDAO.getIDbySdt(sdt);
            AccountModel acc = AccountDAO.getAccountInfo(id, password);
            if (acc!=null) {
            	String role = acc.getType_User();
                if (role.equals("admin")) {
                	session.setAttribute("role", "admin");
                }
                else if (role.equals("cantin")) {
                	session.setAttribute("canteen", CanteenDAO.getInfoCanteen(id)); 
                }
                else {
                	KhachHangModel khachhang = KhachhangDAO.getKhachhangInfo(id);
                	session.setAttribute("khachhang", khachhang);
                } 
                response.sendRedirect(request.getContextPath());
			} 
            else {
            	if (AccountDAO.CheckAccountNotExist(sdt)) {
                	request.setAttribute("noti_login__ErrorSdt", "flex");
                	request.setAttribute("noti_login__ErrorPass", "none");
            	}
            	else {
                	request.setAttribute("noti_login__ErrorSdt", "none");
                	request.setAttribute("noti_login__ErrorPass", "flex");
				}
            	request.setAttribute("display_form__login", "flex");
            	request.setAttribute("sdt", sdt);
            	request.getRequestDispatcher(url).forward(request, response);
            }
            
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        }
	}
}
