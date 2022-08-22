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
		String port = "5432";
	    String db_name = "sample_db";
	    String user = "postgres";
	    String password= "Kishore7!";
		String username = ((HttpServletRequest) request).getParameter("name");
		String userpassword = null;
		response.setContentType("text/html");
		String ret="";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con;
			try {
				
				con = DriverManager.getConnection("jdbc:postgresql://localhost:"+port+"/" +db_name,user,password );
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
		PrintWriter out = response.getWriter();
		out.println("<%\r\n"
				+ "response.setHeader(\"Cache-Control\",\"no-cache, no-store, must-revalidate\");\r\n"
				+ " \r\n"
				+ "if(session.getAttribute(\"username\") == null){\r\n"
				+ "	response.sendRedirect(\"login.jsp\");\r\n"
				+ "}\r\n"
				+ "%>");
		
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
