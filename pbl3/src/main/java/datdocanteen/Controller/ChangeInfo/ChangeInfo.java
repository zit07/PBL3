package datdocanteen.Controller.ChangeInfo;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocanteen.Dao.AccountDAO;
import datdocanteen.Dao.CanteenDAO;
import datdocanteen.Dao.DiachiDAO;
import datdocanteen.Dao.KhachhangDAO;
import datdocanteen.Model.CanteenModel;
import datdocanteen.Model.DiachiModel;
import datdocanteen.Model.KhachHangModel;

@WebServlet("/ChangeInfo")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		try { 
			if (session.getAttribute("khachhang") != null) {
					int ID_khachhang = (int)session.getAttribute("ID_khachhang");
					String hoten = request.getParameter("txtHoten");
					LocalDate ngaysinh = Date.valueOf(request.getParameter("txtNgaysinh")).toLocalDate();
					String gioitinh = request.getParameter("txtGioitinh");
					Double chieucao = Double.valueOf(request.getParameter("txtChieucao"));
					Double cannang = Double.valueOf(request.getParameter("txtCannang"));
					String sdt = request.getParameter("txtSdt");
					String email = request.getParameter("txtEmail");
					String yeuthich = request.getParameter("txtMonyeuthich");
					byte[] avatarBytes = request.getPart("avatar").getInputStream().readAllBytes();
					if (avatarBytes.length == 0) avatarBytes = null;
					if (AccountDAO.CheckAccountNotExist(sdt)) {
						KhachhangDAO.updateInfo(new KhachHangModel(ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, sdt, email, null, yeuthich, null, avatarBytes));
						AccountDAO.ChangeSdt(ID_khachhang, sdt);
					} else {
						KhachhangDAO.updateInfo(new KhachHangModel(ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, null, email, null, yeuthich, null, avatarBytes));
					}
			} else if (session.getAttribute("canteen") != null) {
					CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
					String ten = request.getParameter("txtTencanteen");
					String sdt = request.getParameter("txtSodienthoai");
					String email = request.getParameter("txtEmail");
					Integer tinh = Integer.valueOf(request.getParameter("tinh"));
					Integer huyen = Integer.valueOf(request.getParameter("huyen"));
					Integer xa = Integer.valueOf(request.getParameter("xa"));
					byte[] avatarBytes = request.getPart("avatar").getInputStream().readAllBytes();
					if (avatarBytes.length == 0) avatarBytes = null;
					if (AccountDAO.CheckAccountNotExist(sdt)) {
						CanteenDAO.updateInfo(new CanteenModel(canteen.getID_canteen(), ten, sdt, email, null, null, avatarBytes));
						AccountDAO.ChangeSdt(canteen.getID_canteen(), sdt);
					} else {
						CanteenDAO.updateInfo(new CanteenModel(canteen.getID_canteen(), ten, null, email, null, null, avatarBytes));
					}
					DiachiDAO.ChangeAddress(new DiachiModel(canteen.getID_diachi(), tinh, huyen, xa)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath());
	}

}
