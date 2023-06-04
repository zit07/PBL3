package datdocantin.Controller.ChangeInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.BankDAO;
import datdocantin.Model.BankModel;
import datdocantin.Model.CanteenModel;


@WebServlet("/updateBank")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class UpdateBankinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateBankinfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true); 
		try {
			CanteenModel canteen = (CanteenModel)session.getAttribute("canteen"); 
			if (canteen != null) {
				String nganhang = request.getParameter("txtTennganhang");
				String stk = request.getParameter("txtStk");
				String hoten = request.getParameter("txthoten");
				byte[] maQR = request.getPart("maQR").getInputStream().readAllBytes();
				if (maQR.length == 0) maQR = null;
				int ID_bank_info= BankDAO.getBank(canteen.getID_canteen()).getID_bank_info();
				BankDAO.updateBank(new BankModel(ID_bank_info, nganhang, stk, hoten,canteen.getID_canteen(), maQR));
			}
		} catch (Exception e) {
			log("error at login servlet: " + e.toString());
		}
		response.sendRedirect(request.getContextPath());
	}
}
