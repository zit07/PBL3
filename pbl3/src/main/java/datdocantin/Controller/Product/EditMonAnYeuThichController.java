package datdocantin.Controller.Product;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Dao.MonAnYeuThichDAO;
import datdocantin.Model.KhachHangModel;
import datdocantin.Model.MonAnModel;
import datdocantin.Model.MonAnYeuThichModel;
import datdocantin.Service.getNewIDforTable;


@WebServlet("/add-mon-an-yeu-thich")
public class EditMonAnYeuThichController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditMonAnYeuThichController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	HttpSession session=request.getSession();
    	KhachHangModel khachhang =(KhachHangModel)session.getAttribute("id_khachhang");
    	int ID_khachhang=khachhang.getID_khachhang();
        int ID_monanyeuthich = Integer.valueOf(getNewIDforTable.getNewID("monanyeuthich"));
        int ID_monan = Integer.valueOf(request.getParameter("id_monan"));
        String tag=request.getParameter("tag");
        if(MonAnDAO.CheckProduct(ID_monan, khachhang.getID_canteen())) {
        	MonAnYeuThichDAO.EditMonAnYeuThich(new MonAnYeuThichModel(ID_monanyeuthich,ID_khachhang,ID_monan),tag);
        }
        
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
