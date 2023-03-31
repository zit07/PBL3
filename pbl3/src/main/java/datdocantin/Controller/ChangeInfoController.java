package datdocantin.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.KhachhangDAO;
import datdocantin.Model.KhachHangModel;

@WebServlet("/ChangeInfo")
public class ChangeInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
//            System.out.println(id+hoten+ngaysinh+gioitinh+chieucao+cannang+sdt+email+IDcantin+Monyeuthich);
            if (id!=null) {
            	KhachHangModel khanhhang = new KhachHangModel(id,hoten,ngaysinh,gioitinh,chieucao,cannang,sdt,email,IDcantin,Monyeuthich,"");
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
