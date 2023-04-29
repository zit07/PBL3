package datdocantin.Controller.Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
			String id = request.getParameter("idmonan");
			String k = request.getParameter("k");
			if (k.equals("ngungban")) {
				MonAnDAO.EditTrangthai(id, "ngung ban");
			} else {
				MonAnDAO.EditTrangthai(id, "dang ban");
			}
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Chỉnh sửa thông tin món ăn
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try {
        	String id = request.getParameter("id_monan");
        	String ten = request.getParameter("txtTenmonNew");
        	String mota = request.getParameter("txtMotaNew");
        	String thanhphan = request.getParameter("txtThanhphanNew");
        	String huongvi = request.getParameter("txtHuongviNew");
        	String loai = request.getParameter("txtLoaiNew");
        	String giacu = Double.toString(Double.parseDouble(request.getParameter("giacu")));
            String giamoi = Double.toString(Double.parseDouble(request.getParameter("txtGiaNew")) / 1000);
            byte[] hinhanhchinhBytes = request.getPart("img1New").getInputStream().readAllBytes();
        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
        	MonAnDAO.EditMonan(new MonAnModel(id,"",ten,mota,thanhphan,huongvi,loai,giacu,giamoi,"",hinhanhchinhBytes,"","",""));
        	response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            log("ecit: " + e.toString()); 
        } 
	}

}
