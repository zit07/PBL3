package datdocantin.Controller.Product;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;


@WebServlet("/productmanager")
public class Xoa_Khoiphuc_MonanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Xoa_Khoiphuc_MonanController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		try {
			int ID_canteen = (int)session.getAttribute("ID_canteen");
			int ID_monan = Integer.valueOf(request.getParameter("idmonan"));
			String type = request.getParameter("type");
			if (MonAnDAO.CheckProduct(ID_monan, ID_canteen)) {
				if (type.equals("xoa")) {
					MonAnDAO.Delete_Restore__Product(ID_canteen, ID_monan, "Delete");
				} else if (type.equals("khoiphuc")) {
					MonAnDAO.Delete_Restore__Product(ID_canteen, ID_monan, "Restore");
				} else if (type.equals("xoavinhvien")) {
					MonAnDAO.Delete_Restore__Product(ID_canteen, ID_monan, "DeleteForever");
				}
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/monandaxoa");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
