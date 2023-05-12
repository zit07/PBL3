package datdoancantin.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datdocantin.Dao.AccountDAO;
import datdocantin.Model.AccountModel;

@WebServlet("/list-acc-locked")
public class listAccLocked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public listAccLocked() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO acd=new AccountDAO();
		try {
			List<AccountModel> listacc=acd.getAllAccLock();
			request.setAttribute("listacc", listacc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
