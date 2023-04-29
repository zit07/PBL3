package datdocantin.Controller.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.MonAnModel;


@WebServlet("/Locmonan")
public class LocmonanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LocmonanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	String txtLoaimonan = request.getParameter("txtloaimonan");
        	System.out.println(txtLoaimonan);
        	response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            log("ecit: " + e.toString()); 
        } 
	}

}
