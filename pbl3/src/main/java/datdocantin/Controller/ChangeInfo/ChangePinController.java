package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.KhachhangDAO;
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
        	Integer ID = Integer.valueOf(request.getParameter("id_user"));
            String pass = request.getParameter("txtPass");
            Integer pin = Integer.valueOf(request.getParameter("txtNewPin"));
            AccountModel acc = AccountDAO.getAccountInfo(ID, null, pass);
            if (acc!=null) {
            	KhachhangDAO.ChangePin(ID, pin);
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
