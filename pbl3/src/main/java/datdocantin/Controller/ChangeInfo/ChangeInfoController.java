package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Dao.DiachiDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.CanteenModel;
import datdocantin.Model.DiachiModel;
import datdocantin.Model.KhachHangModel;

@WebServlet("/ChangeInfo")
@MultipartConfig(
		  fileSizeThreshold   = 1024 * 1024 * 10, 
		  maxFileSize         = 1024 * 1024 * 50, 
		  maxRequestSize      = 1024 * 1024 * 100)
public class ChangeInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeInfoController() {
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
					int ID_khachhang = Integer.valueOf(request.getParameter("id_user"));
					String hoten = request.getParameter("txtHoten");
					LocalDate ngaysinh = Date.valueOf(request.getParameter("txtNgaysinh")).toLocalDate();
					String gioitinh = request.getParameter("txtGioitinh");
					Double chieucao = Double.valueOf(request.getParameter("txtChieucao"));
					Double cannang = Double.valueOf(request.getParameter("txtCannang"));
					String sdt = request.getParameter("txtSdt");
					String email = request.getParameter("txtEmail");
					String yeuthich = request.getParameter("txtMonyeuthich");
					Part avatarPart = request.getPart("avatar");
					InputStream inputStream = avatarPart.getInputStream();
					byte[] avatarBytes = inputStream.readAllBytes();
					if (avatarBytes.length == 0) avatarBytes = null;
					if (AccountDAO.CheckAccountNotExist(sdt)) {
						KhachhangDAO.updateInfo(new KhachHangModel(ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, sdt, email, null, yeuthich, null, avatarBytes));
						AccountDAO.ChangeSdt(ID_khachhang, sdt);
					} else {
						KhachhangDAO.updateInfo(new KhachHangModel(ID_khachhang, hoten, ngaysinh, gioitinh, chieucao, cannang, null, email, null, yeuthich, null, avatarBytes));
					}
			} else if (session.getAttribute("canteen") != null) {
					Integer ID_canteen = Integer.valueOf(request.getParameter("id_canteen"));
					String ten = request.getParameter("txtTencanteen");
					String sdt = request.getParameter("txtSodienthoai");
					String email = request.getParameter("txtEmail");
					Integer tinh = Integer.valueOf(request.getParameter("tinh"));
					Integer huyen = Integer.valueOf(request.getParameter("huyen"));
					Integer xa = Integer.valueOf(request.getParameter("xa"));
					Part avatarPart = request.getPart("avatar");
					InputStream inputStream = avatarPart.getInputStream();
					byte[] avatarBytes = inputStream.readAllBytes();
					if (avatarBytes.length == 0) avatarBytes = null;
					if (AccountDAO.CheckAccountNotExist(sdt)) {
						CanteenDAO.updateInfo(new CanteenModel(ID_canteen, ten, sdt, email, null, null, avatarBytes));
						AccountDAO.ChangeSdt(ID_canteen, sdt);
					} else {
						CanteenDAO.updateInfo(new CanteenModel(ID_canteen, ten, null, email, null, null, avatarBytes));
					}
					DiachiDAO.ChangeAddress(new DiachiModel(CanteenDAO.getIDDiachi(ID_canteen), tinh, huyen, xa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath());
	}

}
