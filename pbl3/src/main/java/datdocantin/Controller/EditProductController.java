package datdocantin.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.MonAnDAO;
import datdocantin.Model.MonAnModel;

@WebServlet("/Editproduct")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
       
    public EditProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Chỉnh sửa trạng thái ngưng bán/mở bán
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		try {
			String id = request.getParameter("idmonan");
			String k = request.getParameter("k");
			String idcanteen = request.getParameter("idcanteen");
			if (k.equals("ngungban")) {
				MonAnDAO.EditTrangthai(id, "ngung ban");
			} else {
				MonAnDAO.EditTrangthai(id, "dang ban");
			}
			session.setAttribute("listMonan", MonAnDAO.getListMonan(idcanteen));
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Chỉnh sửa thông tin món ăn
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
        try {
        	String id = request.getParameter("id_monan");
        	String idcantin = request.getParameter("id_canteen");
        	String ten = request.getParameter("txtTenmonNew");
        	String mota = request.getParameter("txtMotaNew");
        	String thanhphan = request.getParameter("txtThanhphanNew");
        	String huongvi = request.getParameter("txtHuongviNew");
        	String loai = request.getParameter("txtLoaiNew");
        	String giacu = Double.toString(Double.parseDouble(request.getParameter("giacu")));
            String giamoi = Double.toString(Double.parseDouble(request.getParameter("txtGiaNew")) / 1000);
            byte[] hinhanhchinhBytes = request.getPart("img1New").getInputStream().readAllBytes();
        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
        	MonAnDAO.EditMonan(new MonAnModel(id,"",ten,mota,thanhphan,huongvi,loai,giacu,giamoi,"",hinhanhchinhBytes,"",""));
        	session.setAttribute("listMonan", MonAnDAO.getListMonan(idcantin));
        	response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            log("ecit: " + e.toString()); 
        } 
	}

}
