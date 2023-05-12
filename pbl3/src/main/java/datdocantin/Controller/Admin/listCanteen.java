package datdocantin.Controller.Admin;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datdocantin.Dao.AccountDAO;
import datdocantin.Dao.CanteenDAO;
import datdocantin.Model.CanteenModel;

@WebServlet("/listCanteen")
public class listCanteen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public listCanteen() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CanteenModel> listCanteen ;
		
		try {
			listCanteen =CanteenDAO.getAllCanteenActive();
			request.setAttribute("listCanteen", listCanteen);
			RequestDispatcher rd=request.getRequestDispatcher("view/admin-homepage.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		}
		
		
	}
	
	



