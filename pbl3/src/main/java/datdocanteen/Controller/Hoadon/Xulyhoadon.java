package datdocanteen.Controller.Hoadon;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.DoanhthuDAO;
import datdocanteen.Dao.DoanhthuchitietDAO;
import datdocanteen.Dao.HoadonDAO;
import datdocanteen.Dao.HoadonchitietDAO;
import datdocanteen.Dao.MonAnDAO;
import datdocanteen.Model.DoanhthuModel;
import datdocanteen.Model.DoanhthuchitietModel;
import datdocanteen.Model.HoadonchitietModel;
import datdocanteen.Service.getNewIDforTable;

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
        			response.sendRedirect(request.getContextPath() + "/hoadonchoxacnhan");
        		}
        	} else if (session.getAttribute("canteen") != null) {
        		int ID_canteen = (int)session.getAttribute("ID_canteen");
        		int ID_hoadon = Integer.valueOf(request.getParameter("id_hoadon"));
        		if (HoadonDAO.checkID_hoadon(ID_hoadon,ID_canteen)) {
        			HoadonDAO.changeTrangthai(ID_hoadon, tag);
        			if (tag.equals("dangchuanbi")) {
            			MonAnDAO.UpdateSold(HoadonchitietDAO.getHoadonchitiet(ID_hoadon));
            		}
        		} 
        		if (tag.equals("dangchuanbi")) {
        			tag = "/donchoxacnhan";
        			LocalDate today = LocalDate.now();
        			Integer ID_doanhthu = DoanhthuDAO.getID_Doanhthu(ID_canteen, today);
    				if (ID_doanhthu == null) {
    					ID_doanhthu = getNewIDforTable.getNewID("doanhthu");
    					DoanhthuDAO.addDoanhthu(new DoanhthuModel(ID_doanhthu, ID_canteen, today, 0, 0));
    				} 
    				List<HoadonchitietModel> hdcts = HoadonchitietDAO.getHoadonchitiet(ID_hoadon);
    				for (HoadonchitietModel hdct : hdcts) {
    					Integer ID_doanhthuchitiet = DoanhthuchitietDAO.getID_doanhthuchitiet(new DoanhthuchitietModel(0, ID_doanhthu, hdct.getTenmon(), 0, 0));
        				if (ID_doanhthuchitiet != null) {
        					DoanhthuchitietDAO.changesoluong(ID_doanhthuchitiet, DoanhthuchitietDAO.getSoluongmonan(ID_doanhthuchitiet) + 1);
        				} else {
        					DoanhthuchitietDAO.addDoanhthuchitiet(new DoanhthuchitietModel(
            						getNewIDforTable.getNewID("doanhthuchitiet"), ID_doanhthu, hdct.getTenmon(), hdct.getSoluong(), hdct.getGia()));
						}
					}
    				DoanhthuDAO.changeTongtien(ID_doanhthu, DoanhthuchitietDAO.getTongtien(ID_doanhthu));
    				DoanhthuDAO.changeTongsoluong(ID_doanhthu, DoanhthuchitietDAO.getTongsoluong(ID_doanhthu));
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
