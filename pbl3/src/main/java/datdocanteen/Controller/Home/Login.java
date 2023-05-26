package datdocanteen.Controller.Home;

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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
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
            AccountModel account = AccountDAO.getAccountInfo(null,sdt, password);
            if (account!=null) {
            	int ID_account = account.getID_account();
            	String role = account.getType_User();
                if (role.equals("admin")) {
                	session.setAttribute("admin", "admin");
                }
                else if (role.equals("cantin")) {
                	session.setAttribute("ID_canteen", ID_account); 
                	session.setAttribute("canteen", CanteenDAO.getInfoCanteen(ID_account)); 
                }
                else {
                	session.setAttribute("ID_khachhang", ID_account); 
                	session.setAttribute("khachhang", KhachhangDAO.getKhachhangInfo(ID_account));
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
