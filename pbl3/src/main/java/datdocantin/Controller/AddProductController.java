package datdocantin.Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CanteenDAO;
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
        	String id = getNewIDforTable.getNewID("monan");
        	String idcantin = request.getParameter("id_canteen");
        	String ten = request.getParameter("txtTenmon");
        	String mota = request.getParameter("txtMota");
        	String thanhphan = request.getParameter("txtThanhphan");
        	String huongvi = request.getParameter("txtHuongvi");
        	String loai = request.getParameter("txtLoai");
            String gia = Double.toString(Double.parseDouble(request.getParameter("txtGia")) / 1000);
        	String today = LocalDate.now().toString();
        	byte[] hinhanhchinhBytes = request.getPart("img1").getInputStream().readAllBytes();
        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
        	MonAnDAO.addNewMonan(new MonAnModel(id,idcantin,ten,mota,thanhphan,huongvi,loai,gia,gia,today,hinhanhchinhBytes,"dang ban","0"));
        	session.setAttribute("canteen", CanteenDAO.getInfoCanteen(idcantin));
        	session.setAttribute("listMonan", MonAnDAO.getListMonan(idcantin));
        	response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}

}
