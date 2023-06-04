package datdocanteen.Controller.Hoadon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HoadonDAO;
import datdocantin.Dao.HoadonchitietDAO;
import datdocantin.Dao.MonAnDAO;

@WebServlet("/Xulyhoadon")
public class Xulyhoadon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Xulyhoadon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			String tag = request.getParameter("tag"); 
        	if (session.getAttribute("khachhang") != null) { 
        		int ID_hoadon = HoadonDAO.getID_Hoadon((int)session.getAttribute("ID_khachhang"));
        		if (HoadonDAO.getTrangthai(ID_hoadon).equals("chưa thanh toán") & tag.equals("xacnhanthanhtoan")) {
        			HoadonDAO.changeTrangthai(ID_hoadon, tag);
        			response.sendRedirect(request.getContextPath() + "/hoadonchothanhtoan");
        		}
        	} else if (session.getAttribute("canteen") != null) {
        		int ID_hoadon = Integer.valueOf(request.getParameter("id_hoadon"));
        		if (HoadonDAO.checkID_hoadon(ID_hoadon, (int)session.getAttribute("ID_canteen"))) {
        			HoadonDAO.changeTrangthai(ID_hoadon, tag);
        			if (tag.equals("dangchuanbi")) {
            			MonAnDAO.UpdateSold(HoadonchitietDAO.getHoadonchitiet(ID_hoadon));
            		}
        		} 
        		if (tag.equals("dangchuanbi")) {
        			tag = "/donchoxacnhan";
        		} else if (tag.equals("daxong")) {
        			tag = "/dondangchuanbi";
    			} 
        		response.sendRedirect(request.getContextPath() + tag);
    		}
        	else {
        		response.sendRedirect(request.getContextPath());
			}
    		
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
