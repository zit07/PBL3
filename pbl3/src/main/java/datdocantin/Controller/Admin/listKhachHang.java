package datdocantin.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.KhachHangModel;

/**
 * Servlet implementation class listKhachHang
 */
@WebServlet("/list-khach-hang")
public class listKhachHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public listKhachHang() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KhachhangDAO khd=new KhachhangDAO();
		List<KhachHangModel> list;
		try {
			list=khd.getAllKHActive();
			request.setAttribute("listKHs", list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("view/admin-homepage.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
