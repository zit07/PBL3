package datdocanteen.Controller.Hoadon;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.CartDAO;
import datdocantin.Dao.HoadonDAO;
import datdocantin.Dao.HoadonchitietDAO;
import datdocantin.Model.CartModel;
import datdocantin.Model.HoadonModel;
import datdocantin.Model.HoadonchitietModel;
import datdocantin.Model.KhachHangModel;
import datdocantin.Service.Taomadonhang;
import datdocantin.Service.getNewIDforTable;


@WebServlet("/taohoadon")
public class Taohoadon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Taohoadon() {
        super();
    }
    
    protected boolean addHoadonchitiet(String[] selectedID_carts, int ID_khachhang, int ID_hoadon) throws SQLException, Exception {
    	for (String selectedID_cart : selectedID_carts) {
	    	int ID_cart = Integer.parseInt(selectedID_cart);
	    	if (CartDAO.checkCart(ID_cart, ID_khachhang)) {
	    		CartModel cart = CartDAO.getID_Ten_soluong_gia(ID_cart);
	    		Integer ID_hoadonchitiet = HoadonchitietDAO.GetID(ID_hoadon, cart.getID_monan()); 
	    		if ( ID_hoadonchitiet == null) {
		    		HoadonchitietDAO.addHoadonchitiet(new HoadonchitietModel(getNewIDforTable.getNewID("hoadonchitiet"), ID_hoadon, cart.getID_monan(), cart.getTenmon(), cart.getSoluong(), cart.getGia()));
				}
	    		else {
	    			HoadonchitietDAO.ChangeSoluong(ID_hoadonchitiet, cart.getSoluong());
				}
	    		CartDAO.XoaCart(ID_cart);
	    	} else {
				return false;
			}
	    }
    	return true;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try { 
			HttpSession session = request.getSession();
			KhachHangModel khachhang = (KhachHangModel) session.getAttribute("khachhang");
			int ID_khachhang = khachhang.getID_khachhang();
			String[] selectedID_carts = request.getParameterValues("id_carts");
			if (selectedID_carts != null) {
				Integer ID_hoadon = HoadonDAO.getID_Hoadon(ID_khachhang);
				if (ID_hoadon == null) {
					ID_hoadon = getNewIDforTable.getNewID("hoadon");
					LocalDate today = LocalDate.now();
				    if (addHoadonchitiet(selectedID_carts, ID_khachhang, ID_hoadon)) { 
					    HoadonDAO.addHoadon(new HoadonModel(ID_hoadon, khachhang.getID_canteen(), ID_khachhang, today, HoadonchitietDAO.getTongtien(ID_hoadon), "chưa thanh toán", Taomadonhang.Creat(), 1));
				    } 
				} else {
				    if (addHoadonchitiet(selectedID_carts, ID_khachhang, ID_hoadon)) {
					    HoadonDAO.changeTongtien(ID_hoadon, HoadonchitietDAO.getTongtien(ID_hoadon));
				    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
