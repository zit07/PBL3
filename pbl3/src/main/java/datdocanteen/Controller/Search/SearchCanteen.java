package datdocanteen.Controller.Search;

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
import datdocantin.Model.DiachiModel;

@WebServlet("/SearchCanteen")
public class SearchCanteen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchCanteen() {
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
        	int Tinh = Integer.parseInt(request.getParameter("txtTinh"));  
        	int Huyen = Integer.parseInt(request.getParameter("txtHuyen"));
        	int Xa = Integer.parseInt(request.getParameter("txtXa"));
        	session.setAttribute("tinh", Tinh);
        	session.setAttribute("huyen", Huyen);
        	session.setAttribute("xa", Xa); 
            List<CanteenModel> canteens = CanteenDAO.SearchCanteen(txtSearch, new DiachiModel(null, Tinh, Huyen, Xa));
            if (canteens.size()!=0) {
				session.setAttribute("canteenList", canteens);
			}
            if (Tinh != -1) session.setAttribute("txtSearchCanteen", txtSearch);
            if (session.getAttribute("khachhang")!=null) session.setAttribute("showcanteen", "flex");
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}

}
