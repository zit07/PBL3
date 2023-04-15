package datdocantin.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HistorySearchDAO;
import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.MonAnModel;
import datdocantin.Service.getNewIDforTable;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("utf-8");
//		HttpSession session = request.getSession(true);
//        try {
//        	String idkh = request.getParameter("id_user");
//        	String tukhoa = request.getParameter("txtSearch");System.err.println(idkh+tukhoa);
//            if (idkh!=null) { 
//            	HistorySearchDAO.addSearchHistory(getNewIDforTable.getNewID("lichsutimkiem"),idkh, tukhoa);
//            	session.setAttribute("listMonan", MonAnDAO.getResultSearchforCanteen(idkh,tukhoa));
//            	response.sendRedirect(request.getContextPath());
//            } 
//            else {
//            	response.sendRedirect(request.getContextPath());
//            }
//        } catch (Exception e) {
//            log("error at login servlet: " + e.toString());
//        } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
        try {
        	String idkh = request.getParameter("id_user");
        	String tukhoa = request.getParameter("txtSearch");
            if (idkh!=null) {
//            	List<MonAnModel> listMonAn = MonAnDAO.getResultSearchforCanteen("10003", "my");
//            	for (MonAnModel monAn : listMonAn) {
//            	    System.out.println("ID món ăn: " + monAn.getId());
//            	    System.out.println("Tên món ăn: " + monAn.getTenmon());
//            	    System.out.println("Giá món ăn: " + monAn.getNgaytao());
//            	    // và các thuộc tính khác của MonAnModel tương ứng
//            	}
            	HistorySearchDAO.addSearchHistory(getNewIDforTable.getNewID("lichsutimkiem"),idkh, tukhoa);
            	session.setAttribute("searchHistory", HistorySearchDAO.getSearchHistory(idkh));
            	session.setAttribute("listMonan", MonAnDAO.getResultSearchforCanteen(idkh,tukhoa));
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	response.sendRedirect(request.getContextPath());
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}

}
