package datdocantin.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.AccountDAO;
import datdocantin.Model.AccountModel;

@WebServlet("/ChangePin")
public class ChangePinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePinController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("display_form__changepin", "flex");
    	request.setAttribute("notiErrorOldPass", "none");
    	request.setAttribute("notiSuccessNewPin", "none");
    	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	String user = request.getParameter("user");
            String oldPass = request.getParameter("txtOldPass");
            String pin = request.getParameter("txtNewPin");
            AccountModel acc = AccountDAO.getAccountInfo(user, oldPass);
            if (acc!=null) {
//            	AccountDAO.ChangePin(user, pin);
            	request.setAttribute("display_form__changepin", "flex");
            	request.setAttribute("notiSuccessNewPin", "flex");
            	request.setAttribute("notiErrorOldPass", "none");
            	response.sendRedirect(request.getContextPath());
            } 
            else {
            	request.setAttribute("display_form__changepin", "flex");
            	request.setAttribute("notiSuccessNewPin", "none");
            	request.setAttribute("notiErrorOldPass", "flex");
            	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}

}
