package datdocanteen.Controller.Khachhang;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CartDAO;
import datdocantin.Model.CartModel;

@WebServlet("/cart")
public class Giohang extends HttpServlet {
	private static final long serialVersionUID = 1L;
        

    public Giohang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer ID_khachhang = (Integer)session.getAttribute("ID_khachhang");
		if (ID_khachhang != null) {
			try { 
				List<CartModel> carts = CartDAO.getCarts(ID_khachhang);
				session.setAttribute("carts", carts);
				session.setAttribute("soluonggiohang", CartDAO.getSoluongmon(carts));
				session.setAttribute("tongtiencart", CartDAO.getTongtien(carts));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("view/customer-cartpage.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
