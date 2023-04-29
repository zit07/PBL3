package datdocantin.Controller.ChangeInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.GiohoatdongDAO;
import datdocantin.Model.GiohoatdongModel;
 
@WebServlet("/ChangeTimeOpen")
public class ChangeTimeOpenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeTimeOpenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("id_canteen");
			List<GiohoatdongModel> giohoatdongList = new ArrayList<GiohoatdongModel>();
			for (int i = 2; i <= 8; i++) {
				String timeOpen = request.getParameter("txtTimeOpen" + i);
				String timeClose = request.getParameter("txtTimeClose" + i);
				timeOpen = "-1".equals(timeOpen) ? null : timeOpen;
				timeClose = "-1".equals(timeClose) ? null : timeClose;
				giohoatdongList.add(new GiohoatdongModel("", id, String.valueOf(i), timeOpen, timeClose));
			}
			GiohoatdongDAO.Changegiohoatdong(giohoatdongList);
		} catch (Exception e) {
			log("error at login servlet: " + e.toString());
		}
		response.sendRedirect(request.getContextPath());
	}

}
