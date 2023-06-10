package datdocanteen.Controller.Home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocanteen.Dao.AccountDAO;
import datdocanteen.Dao.BankDAO;
import datdocanteen.Dao.CanteenDAO;
import datdocanteen.Dao.DiachiDAO;
import datdocanteen.Dao.GiohoatdongDAO;
import datdocanteen.Dao.KhachhangDAO;
import datdocanteen.Model.AccountModel;
import datdocanteen.Model.CanteenModel;
import datdocanteen.Model.KhachHangModel;
import datdocanteen.Service.getNewIDforTable;
import datdocanteen.Util.PasswordEncoder;

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
            if (TypeUser.equals("canteen") || TypeUser.equals("customer")) {
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
                	System.out.println("123123");
                	response.sendRedirect(request.getContextPath());
                }
                else {
                	request.setAttribute("display_form__signup", "flex");
                	request.setAttribute("display_noti__signup", "flex");
                	request.setAttribute("sdt", SDT);
                	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
                }
			} else {
				response.sendRedirect(request.getContextPath());
			}
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}
}
