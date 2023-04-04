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
import datdocantin.Dao.KhachhangDAO;
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
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1);
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
            if (avatarBytes.length==0) avatarBytes=null;
            if (id!=null) {
            	KhachHangModel khanhhang = new KhachHangModel(id,hoten,ngaysinh,gioitinh,chieucao,cannang,sdt,email,IDcantin,Monyeuthich,"",avatarBytes);
            	KhachhangDAO.updateInfo(khanhhang);
            	AccountDAO.ChangeSdt(id, sdt);
            	session.setAttribute("khachhang", KhachhangDAO.getKhachhangInfo(id));
            	response.sendRedirect(request.getContextPath());
            }
            else {
            	request.getRequestDispatcher("view/homepage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("error at login servlet: " + e.toString());
        } 
	}

}
