package loginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		 logger.log(Level.INFO, "Logout Successfull .....");
    	 System.out.println();
		response.sendRedirect("login.jsp");}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
