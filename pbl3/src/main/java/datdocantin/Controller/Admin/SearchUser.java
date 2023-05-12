package datdoancantin.Controller.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.KhachHangModel;


@WebServlet("/search-kh")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String search=request.getParameter("txtSearch");
		KhachhangDAO khd=new KhachhangDAO();
		try {
			List<KhachHangModel> listKHs=khd.searchByName(search);
			request.setAttribute("listKHBySearch", listKHs);
			RequestDispatcher rd=request.getRequestDispatcher("view/admin-homepage.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
