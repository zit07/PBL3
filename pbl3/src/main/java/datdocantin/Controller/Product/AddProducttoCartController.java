package datdocantin.Controller.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Service.getNewIDforTable;

@WebServlet("/addtocart")
public class AddProducttoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProducttoCartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	Integer id_cart = getNewIDforTable.getNewID("carts");
        	String id_user = request.getParameter("id_user");
        	
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
