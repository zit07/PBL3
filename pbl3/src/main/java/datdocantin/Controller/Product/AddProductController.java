package datdocantin.Controller.Product;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.MonAnModel;
import datdocantin.Service.getNewIDforTable;


@WebServlet("/Addproduct")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); 
		HttpSession session = request.getSession(true);
		try {
			int ID_canteen = (int)session.getAttribute("ID_canteen");
        	Integer ID_monan = Integer.valueOf(getNewIDforTable.getNewID("monan"));
        	String ten = request.getParameter("txtTenmon");
        	String mota = request.getParameter("txtMota");
        	String thanhphan = request.getParameter("txtThanhphan");
        	String huongvi = request.getParameter("txtHuongvi");
        	Integer ID_Loaithucan = Integer.valueOf(request.getParameter("txtLoai"));
        	Double gia = Double.parseDouble(request.getParameter("txtGia")) / 1000;
        	LocalDate today = LocalDate.now();
        	byte[] hinhanhchinhBytes = request.getPart("img1").getInputStream().readAllBytes();
        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
        	MonAnDAO.addNewMonan(new MonAnModel(ID_monan,ID_canteen,ten,mota,thanhphan,huongvi,ID_Loaithucan,gia,gia,today,hinhanhchinhBytes,1,0,0));
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}

}
