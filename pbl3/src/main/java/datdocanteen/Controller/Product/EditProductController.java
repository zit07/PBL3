package datdocanteen.Controller.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.MonAnDAO;
import datdocanteen.Dao.Monan_LoaithucanDAO;
import datdocanteen.Model.MonAnModel;
import datdocanteen.Model.Monan_loaithucanModel;
import datdocanteen.Service.getNewIDforTable;

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
			int ID_canteen = (int)session.getAttribute("ID_canteen");
			Integer ID_monan = Integer.valueOf(request.getParameter("idmonan")); 
			String k = request.getParameter("k");
			if (MonAnDAO.CheckProduct(ID_monan, ID_canteen)) {
				if (k.equals("ngungban")) {
					MonAnDAO.EditTrangthai(ID_monan, 0);
					response.sendRedirect(request.getContextPath() + "/monandangban");
				} else {
					MonAnDAO.EditTrangthai(ID_monan, 1);
					response.sendRedirect(request.getContextPath() + "/monanngungban");
				}
			} else {
				response.sendRedirect(request.getContextPath());
			}
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
			Integer ID_canteen = (Integer)session.getAttribute("ID_canteen");
			int ID_monan = Integer.valueOf(request.getParameter("id_monan"));
			if (ID_canteen != null) { 
				if (MonAnDAO.CheckProduct(ID_monan, ID_canteen)) { 
					String ten = request.getParameter("txtTenmonNew");
		        	String mota = request.getParameter("txtMotaNew");
		        	String thanhphan = request.getParameter("txtThanhphanNew");
		        	String huongvi = request.getParameter("txtHuongviNew");
		        	String[] selectedID_loaithucans = request.getParameterValues("loaithucans");
		        	Double giacu = Double.parseDouble(request.getParameter("giacu"));
		        	Double giamoi = Double.parseDouble(request.getParameter("txtGiaNew")) / 1000;
		        	byte[] hinhanhchinhBytes = request.getPart("img1New").getInputStream().readAllBytes();
		        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;  
		        	if (ten!=null & mota!=null & thanhphan!=null & huongvi!=null & giacu!=null & giamoi!=null & selectedID_loaithucans.length > 0) {	        		
		        		MonAnDAO.EditMonan(new MonAnModel(ID_monan,ID_canteen,ten,mota,thanhphan,huongvi,giacu,giamoi,null,hinhanhchinhBytes,null,null,null));
		        		List<Monan_loaithucanModel> loaithucans = new ArrayList<Monan_loaithucanModel>();
			        	int ID_monan_loaithucan = getNewIDforTable.getNewID("monan_loaithucan");
			        	for (int i=0; i<selectedID_loaithucans.length; i++) {
							loaithucans.add(new Monan_loaithucanModel(ID_monan_loaithucan + i, ID_monan, Integer.valueOf(selectedID_loaithucans[i])));
						}
			        	Monan_LoaithucanDAO.DeleteMonan_loaithucan(ID_monan);
			        	Monan_LoaithucanDAO.AddMonan_loaithucan(loaithucans);
			        	if (session.getAttribute("tag").equals("dangban")) {
			        		response.sendRedirect(request.getContextPath() + "/monandangban");
						} else if (session.getAttribute("tag").equals("ngungban")) {
			        		response.sendRedirect(request.getContextPath() + "/monanngungban");
						} 
		        	} else {
		        		session.setAttribute("tag", "tatca");
		        		response.sendRedirect(request.getContextPath());
		        	}
				}
			} else {
				response.sendRedirect(request.getContextPath());
			}
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}

}
