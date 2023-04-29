package datdocantin.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CanteenDAO;
import datdocantin.Model.CanteenModel;

@WebServlet("/SearchCanteen")
public class SearchCanteenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchCanteenController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		session.removeAttribute("menu");
		session.removeAttribute("canteenList");
		session.removeAttribute("txtSearchCanteen");
        try {
        	String txtSearch = request.getParameter("txtSearchCanteen"); 
        	String txtTinh = request.getParameter("txtTinh");  
        	String txtHuyen = request.getParameter("txtHuyen");
        	String txtXa = request.getParameter("txtXa");
        	session.setAttribute("tinh", txtTinh);
        	session.setAttribute("huyen", txtHuyen);
        	session.setAttribute("xa", txtXa);
            List<CanteenModel> canteens = CanteenDAO.SearchCanteen(txtSearch, txtTinh, txtHuyen, txtXa);
            if (canteens.size()!=0) {
				session.setAttribute("canteenList", canteens);
			}
            if (txtTinh.equals("-1")) session.setAttribute("txtSearchCanteen", txtSearch);
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}

}
