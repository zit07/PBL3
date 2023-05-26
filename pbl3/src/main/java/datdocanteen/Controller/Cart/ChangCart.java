package datdocanteen.Controller.Cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import datdocantin.Dao.CartDAO;

@WebServlet("/ChangCart")
public class ChangCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("utf-8");
        try {
        	int ID_cart = Integer.valueOf(request.getParameter("id_cart"));
        	String type = request.getParameter("type"); 
        	if (type.equals("tang") || type.equals("giam")) {
        		CartDAO.Tang_GiamCart(ID_cart, type, CartDAO.getSoluong(ID_cart, null, null));
        	} else if (type.equals("xoa")){
				CartDAO.XoaCart(ID_cart);
			}
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        if (request.getParameter("page").equals("cart")) {
        	response.sendRedirect(request.getContextPath() + "/cart");
		} else { 
			response.sendRedirect(request.getContextPath());
		}
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
