package datdocantin.Controller;

import java.io.IOException;
import java.io.InputStream;

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
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.CanteenModel;
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
		if (session.getAttribute("khachhang") != null) {
			try {
				String id = request.getParameter("id_user");
				String hoten = request.getParameter("txtHoten");
				String ngaysinh = request.getParameter("txtNgaysinh");
				String gioitinh = request.getParameter("txtGioitinh");
				String chieucao = request.getParameter("txtChieucao");
				String cannang = request.getParameter("txtCannang");
				String sdt = request.getParameter("txtSdt");
				String email = request.getParameter("txtEmail");
				String IDcantin = request.getParameter("txtIDCantin");
				String Monyeuthich = request.getParameter("txtMonyeuthich");
				Part avatarPart = request.getPart("avatar");
				InputStream inputStream = avatarPart.getInputStream();
				byte[] avatarBytes = inputStream.readAllBytes();
				if (avatarBytes.length == 0)
					avatarBytes = null;
				if (id != null) {
					KhachhangDAO.updateInfo(new KhachHangModel(id, hoten, ngaysinh, gioitinh, chieucao, cannang, sdt,
							email, IDcantin, Monyeuthich, "", avatarBytes));
					AccountDAO.ChangeSdt(id, sdt);
					session.setAttribute("khachhang", KhachhangDAO.getKhachhangInfo(id));
				}
			} catch (Exception e) {
				log("error at login servlet: " + e.toString());
			}
		} else if (session.getAttribute("canteen") != null) {
			try {
				String id = request.getParameter("id_canteen");
				String ten = request.getParameter("txtTencanteen");
				String sdt = request.getParameter("txtSodienthoai");
				String email = request.getParameter("txtEmail");
				String tinh = request.getParameter("tinh");
				String huyen = request.getParameter("huyen");
				String xa = request.getParameter("xa");
				Part avatarPart = request.getPart("avatar");
				InputStream inputStream = avatarPart.getInputStream();
				byte[] avatarBytes = inputStream.readAllBytes();
				if (avatarBytes.length == 0)
					avatarBytes = null;
				if (id != null) {
					CanteenDAO.updateInfo(new CanteenModel(id, ten, sdt, email, tinh, huyen, xa, "", avatarBytes));
					AccountDAO.ChangeSdt(id, sdt);
					session.setAttribute("canteen", CanteenDAO.getInfoCanteen(id));
				}
			} catch (Exception e) {
				log("error at login servlet: " + e.toString());
			}
		}
		response.sendRedirect(request.getContextPath());
	}

}
