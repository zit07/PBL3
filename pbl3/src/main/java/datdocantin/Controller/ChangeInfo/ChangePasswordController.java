package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.AccountDAO;
import datdocantin.Model.AccountModel;

@WebServlet("/ChangePassword")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChangePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("display_form__changepass", "flex");
    	request.setAttribute("notiErrorOldPass", "none");
    	request.setAttribute("notiSuccessNewPass", "none");
    	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	Integer ID = Integer.valueOf(request.getParameter("id_user")); 
            String oldPass = request.getParameter("txtOldPass");
            String newPass = request.getParameter("txtNewPass");
            AccountModel acc = AccountDAO.getAccountInfo(ID, null, oldPass);
            if (acc!=null) {
            	AccountDAO.ChangePassword(ID, newPass);
            	request.setAttribute("display_form__changepass", "flex");
            	request.setAttribute("notiSuccessNewPass", "flex");
            	request.setAttribute("notiErrorOldPass", "none");
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	request.setAttribute("display_form__changepass", "flex");
            	request.setAttribute("notiSuccessNewPass", "none");
            	request.setAttribute("notiErrorOldPass", "flex");
            	request.getRequestDispatcher("view/customer-homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}
 
}
