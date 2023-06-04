package datdocanteen.Controller.Cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CartDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.CartModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Service.getNewIDforTable;


@WebServlet("/addtocart")
public class AddProducttoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProducttoCart() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); 
        try { 
        	HttpSession session = request.getSession();
    		KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
    		int ID_khachhang = khachhang.getID_khachhang();
        	int ID_cart = getNewIDforTable.getNewID("cart");
        	int ID_monan =  Integer.valueOf(request.getParameter("id_monan")); 
        	if (MonAnDAO.CheckProduct(ID_monan, khachhang.getID_canteen())) { System.out.println(CartDAO.getSoluong(null, ID_monan, ID_khachhang));
        		CartDAO.AddtoCart(new CartModel(ID_cart, ID_khachhang, ID_monan, null, null, CartDAO.getSoluong(null, ID_monan, ID_khachhang) + 1, null));
			}
        } catch (Exception e) {
            e.printStackTrace();
        } 
        response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
