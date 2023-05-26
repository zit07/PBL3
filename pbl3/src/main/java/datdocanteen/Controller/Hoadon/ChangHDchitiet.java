package datdocanteen.Controller.Hoadon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.HoadonDAO;
import datdocantin.Dao.HoadonchitietDAO;


@WebServlet("/Hoadonchitiet")
public class ChangHDchitiet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangHDchitiet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
        try { 
        	HttpSession session = request.getSession();
			Integer ID_hoadon = HoadonDAO.getID_Hoadon((int)session.getAttribute("ID_khachhang"));
        	int ID_hoadonchitiet = Integer.valueOf(request.getParameter("id_hdchitiet"));
        	String type = request.getParameter("type"); 
        	if (HoadonchitietDAO.CheckHDchitiet(ID_hoadonchitiet, ID_hoadon)) {
        		if (type.equals("tang") || type.equals("giam")) {
            		HoadonchitietDAO.TangGiamSoluong(ID_hoadonchitiet, type);
            		HoadonDAO.changeTongtien(ID_hoadon, HoadonchitietDAO.getTongtien(ID_hoadon));
            	} else if (type.equals("xoa")){
            		HoadonchitietDAO.XoaHDChitiet(ID_hoadonchitiet);
            		if (HoadonchitietDAO.ChecksoluongHDCT(ID_hoadon) == false) {
            			HoadonDAO.XoaHoadon(ID_hoadon);
					}
    			}
			}
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
        response.sendRedirect(request.getContextPath() + "/hoadonchothanhtoan");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
