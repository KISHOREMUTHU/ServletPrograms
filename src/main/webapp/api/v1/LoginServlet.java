package loginPackage;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String port = "5432";
    String db_name = "sample_db";
    String username = "postgres";
    String password= "Kishore7!";
       
	 public LoginServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	     
	    public void init(ServletConfig config) throws ServletException {    
	        super.init(config);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        //doPost(request,response);
	         
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		  Logger logger = Logger.getLogger(LoginServlet.class.getName());
		    
	        FileHandler fileHandler = new FileHandler("C:\\Users\\kishore-pt5893\\Downloads\\login_page_new_logs.log",true);
	        
		response.setContentType("text/html");
		Class.forName("org.postgresql.Driver");
		
		Connection con  = DriverManager.getConnection("jdbc:postgresql://localhost:"+port+"/" +db_name,username,password );
		
        String username = request.getParameter("txtName"); 
        String password = request.getParameter("txtPwd"); 
        PreparedStatement preparedStatement = con.prepareStatement("select name from users where name=? and password=?");
	     preparedStatement.setString(1,username);
	     preparedStatement.setString(2,password);
	  
	     ResultSet resultset = preparedStatement.executeQuery();
	    
	     if(resultset.next()) {
	    	 logger.log(Level.INFO, "Login Successfull .....");
	    	 fileHandler.setFormatter(new SimpleFormatter());
	         logger.addHandler(fileHandler);
	         
	    	 System.out.println();
	    	try { 
	    
	    	 HttpSession session = request.getSession();
             session.setAttribute("username",username);
             session.setAttribute("password",password);
            
	    	
	    	if(session.getAttribute("username") != null){
	    		//PrintWriter out = response.getWriter(); 
	    		//out.println(username);
	    		response.sendRedirect("welcome.jsp?name="+username);
	    	}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	
	            
	     }
	     else {
	    	 logger.log(Level.INFO, "Login Failed .....");
	    	 fileHandler .setFormatter(new SimpleFormatter());
	         logger.addHandler(fileHandler );
	         System.out.println();
	    	 response.sendRedirect("login.jsp");

	    	
	     }
	}catch(Exception e) {
		e.printStackTrace();
		
	}
	}

}
