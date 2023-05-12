package datdocantin.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.KhachHangModel;

 
@WebServlet("/khach-hang")
public class listKHByIdCanteen extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public listKHByIdCanteen() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idct=request.getParameter("idcantin");
		KhachhangDAO khd=new KhachhangDAO();
		List<KhachHangModel> list;
		try {
			list=khd.getAllKHActiveByIdCanteen(idct);
			request.setAttribute("listKH", list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("view/admin-homepage.jsp");
		rd.forward(request, response);
					
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
