package datdocantin.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.AccountModel;
import datdocantin.Model.KhachHangModel;

@WebServlet("/Signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("display_form__signup", "flex");
    	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	String hoten = request.getParameter("txtHoten");
        	String sdt = request.getParameter("txtSdt");
            String password = request.getParameter("txtPassword");
            String typeUser = request.getParameter("typeUser");
            if (AccountDAO.CheckAccountNotExist(sdt)) {
            	String id = String.valueOf(AccountDAO.getLastId()+1);
            	AccountModel acc = new AccountModel(id, sdt, password, typeUser);
            	AccountDAO.addAccount(acc);
            	KhachHangModel newKH = new KhachHangModel(id,hoten,null,"","","",sdt,"","",""); 
            	KhachhangDAO.addKhachhang(newKH);
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	request.setAttribute("display_form__signup", "flex");
            	request.setAttribute("display_noti__signup", "flex");
            	request.setAttribute("sdt", sdt);
            	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	
	}

}
