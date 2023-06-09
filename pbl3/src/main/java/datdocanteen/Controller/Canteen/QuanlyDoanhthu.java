package datdocanteen.Controller.Canteen;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.DoanhthuDAO;
import datdocantin.Dao.DoanhthuchitietDAO;
import datdocantin.Model.DoanhthuModel;

@WebServlet({"/doanhthungay", "/doanhthuthang"})
public class QuanlyDoanhthu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuanlyDoanhthu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Integer ID_canteen = (Integer)session.getAttribute("ID_canteen"); 
		if (ID_canteen != null) {
			String tag = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
			try {
				if (tag.equals("doanhthungay")) {
					LocalDate startDate = request.getParameter("startDate") != null ? Date.valueOf(request.getParameter("startDate")).toLocalDate() :  LocalDate.now();
			        LocalDate endDate = request.getParameter("endDate") != null ? Date.valueOf(request.getParameter("endDate")).toLocalDate() :  LocalDate.now();
					List<DoanhthuModel> listDoanhthu = DoanhthuDAO.getListDoanhthungay(ID_canteen, startDate, endDate);
					request.setAttribute("startDate", startDate);
			        request.setAttribute("endDate", endDate);
					session.setAttribute("listDoanhthu", listDoanhthu);
					session.setAttribute("tongdoanhthu", DoanhthuDAO.getTongDoanhthu(listDoanhthu));						
					session.setAttribute("ListDTCT", DoanhthuchitietDAO.getListDoanhthuchitietngay(listDoanhthu));
				} else if (tag.equals("doanhthuthang")) {
					YearMonth startMonth = request.getParameter("startMonth") != null ? YearMonth.parse(request.getParameter("startMonth")) : YearMonth.now();
					YearMonth endMonth = request.getParameter("endMonth") != null ? YearMonth.parse(request.getParameter("endMonth")) : YearMonth.now();
					List<YearMonth> monthList = new ArrayList<>();
			        YearMonth currentMonth = startMonth;
			        while (currentMonth.isBefore(endMonth) || currentMonth.equals(endMonth)) {
			            monthList.add(currentMonth);
			            currentMonth = currentMonth.plusMonths(1);
			        }
			        List<List<DoanhthuModel>> ListDoanhthuthang = DoanhthuDAO.getListDoanhthuthang(ID_canteen, monthList);
			        List<DoanhthuModel> listDoanhthu = DoanhthuDAO.getListTongDoanhthuthang(ListDoanhthuthang);
			        session.setAttribute("listDoanhthu", listDoanhthu);
			        session.setAttribute("listmonth", monthList);
			        session.setAttribute("tongdoanhthu", DoanhthuDAO.getTongDoanhthu(listDoanhthu));	
			        session.setAttribute("ListDTCT", DoanhthuchitietDAO.getListDoanhthuchitietthang(ListDoanhthuthang));
					request.setAttribute("startMonth", startMonth);
			        request.setAttribute("endMonth", endMonth);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        session.setAttribute("tag", tag);
			request.getRequestDispatcher("view/canteen-quanlydoanhthu.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
