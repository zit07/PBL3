package datdocanteen.Controller.Home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.BankDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.GiohoatdongDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.AccountModel;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Service.getNewIDforTable;
import datdocantin.Util.PasswordEncoder;

@WebServlet("/Signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUp() {
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
        	String Ten = request.getParameter("txtHoten");
        	String SDT = request.getParameter("txtSdt");
            String Password = PasswordEncoder.encode(request.getParameter("txtPassword"));
            String TypeUser = request.getParameter("typeUser");
            if (AccountDAO.CheckAccountNotExist(SDT)) {
            	Integer ID = getNewIDforTable.getNewID("account");
            	AccountDAO.addAccount(new AccountModel(ID, SDT, Password, TypeUser, 0));
            	if (TypeUser.equals("customer")) {
                	KhachhangDAO.addKhachhang(new KhachHangModel(ID,Ten,null,null,null,null,SDT,null,null,null,null,null));
            	} 
            	else {
            		int ID_diachi = getNewIDforTable.getNewID("diachi");
            		int ID_bank = getNewIDforTable.getNewID("bank_info");
            		DiachiDAO.AddDiachi(ID_diachi);
            		CanteenDAO.addCanteen(new CanteenModel(ID,Ten,SDT,null,ID_diachi,null,null));
            		BankDAO.AddBank(ID_bank,ID);
            		GiohoatdongDAO.Addgiohoatdong(Integer.valueOf(getNewIDforTable.getNewID("giohoatdong")), ID);
				}
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	request.setAttribute("display_form__signup", "flex");
            	request.setAttribute("display_noti__signup", "flex");
            	request.setAttribute("sdt", SDT);
            	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}
}
