package loginPackage;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		String port = "3306";
	    String db_name = "sample_db";
	    String user = "root";
	    String password= "Kishore7!";
	    String url = ((HttpServletRequest) request).getRequestURL().toString();
		String s[] = url.split("/");
		String username = s[s.length-1].equals("welcome.jsp") ? "" : s[s.length-1];
		String userpassword = null;
		response.setContentType("text/html");
		String ret="";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con;
			try {
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/" +db_name,user,password );
				  PreparedStatement preparedStatement = con.prepareStatement("select password from users where name=?");
				  preparedStatement.setString(1,username);
				  ResultSet resultset = preparedStatement.executeQuery();
				  if(resultset.next() ) {
		                int i=1;
		            
		                 ret += resultset.getString(i) + " ";
		                     i++;
		                }
		               

		            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(s);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ret=="") {
			((HttpServletResponse) response).sendRedirect("login.jsp");}
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("username",username);
		session.setAttribute("password", ret);
		PrintWriter out = response.getWriter();
	
		out.println(username);
		out.println(ret== "" ? "Invalid User" : ret);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
