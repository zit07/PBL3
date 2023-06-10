package datdocanteen.Controller.Product;

import java.io.IOException;
import java.time.LocalDate;
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


@WebServlet("/Addproduct")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddProduct() {
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
			Integer ID_canteen = (Integer)session.getAttribute("ID_canteen");
			if (ID_canteen != null) {
				Integer ID_monan = Integer.valueOf(getNewIDforTable.getNewID("monan"));
	        	String ten = request.getParameter("txtTenmon"); 
	        	String mota = request.getParameter("txtMota"); 
	        	String thanhphan = request.getParameter("txtThanhphan");
	        	String huongvi = request.getParameter("txtHuongvi"); 
	        	String[] selectedID_loaithucans = request.getParameterValues("loaithucans");
	        	Double gia = Double.parseDouble(request.getParameter("txtGia")) / 1000; 
	        	LocalDate today = LocalDate.now();
	        	byte[] hinhanhchinhBytes = request.getPart("img1").getInputStream().readAllBytes();
	        	if (hinhanhchinhBytes.length == 0) hinhanhchinhBytes = null;
	        	if (ID_monan!=null & ten!=null & mota!=null & thanhphan!=null & huongvi!=null & gia!=null & hinhanhchinhBytes!=null & selectedID_loaithucans.length > 0) {
		        	MonAnDAO.addNewMonan(new MonAnModel(ID_monan,ID_canteen,ten,mota,thanhphan,huongvi,gia,gia,today,hinhanhchinhBytes,1,0,0));
		        	List<Monan_loaithucanModel> loaithucans = new ArrayList<Monan_loaithucanModel>();
		        	int ID_monan_loaithucan = getNewIDforTable.getNewID("monan_loaithucan");
		        	for (int i=0; i<selectedID_loaithucans.length; i++) {
						loaithucans.add(new Monan_loaithucanModel(ID_monan_loaithucan + i, ID_monan, Integer.valueOf(selectedID_loaithucans[i])));
					}
		        	Monan_LoaithucanDAO.AddMonan_loaithucan(loaithucans);
				}
			}    	
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath());
	}
}
