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
			Integer ID_monan = Integer.valueOf(request.getParameter("idmonan"));
			String k = request.getParameter("k");
			if (k.equals("ngungban")) {
				MonAnDAO.EditTrangthai(ID_monan, 0);
			} else {
				MonAnDAO.EditTrangthai(ID_monan, 1);
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
        	Integer ID_monan = Integer.valueOf(request.getParameter("id_monan"));
        	String ten = request.getParameter("txtTenmonNew");
        	String mota = request.getParameter("txtMotaNew");
        	String thanhphan = request.getParameter("txtThanhphanNew");
        	String huongvi = request.getParameter("txtHuongviNew");
        	Integer ID_loaithucan = Integer.valueOf(request.getParameter("txtLoaiNew"));
        	Double giacu = Double.parseDouble(request.getParameter("giacu"));
        	Double giamoi = Double.parseDouble(request.getParameter("txtGiaNew")) / 1000;
            byte[] hinhanhchinhBytes = request.getPart("img1New").getInputStream().readAllBytes();
        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
        	MonAnDAO.EditMonan(new MonAnModel(ID_monan,null,ten,mota,thanhphan,huongvi,ID_loaithucan,giacu,giamoi,null,hinhanhchinhBytes,null,null,null));
        	response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            log("ecit: " + e.toString()); 
        } 
	}

}
