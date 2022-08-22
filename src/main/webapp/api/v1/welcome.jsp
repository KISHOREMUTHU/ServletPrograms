<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  


<%
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
 
if(session.getAttribute("username") == null){
	response.sendRedirect("login.jsp");
}



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To Home Page</title>
<style>
*{
 padding :0;
 margin:0;
 text-decoration: none;
 list-style: none;
 box-sizing: border-box;
 
}

body{
font-family:montserrat;
}

nav{
background: #FF0000;
height: 80px;
width: 100%;
}

label.logo{
 color: white;
 font-size: 35px;
 line-height:80px;
 padding:0 100px;
 font-weight:bold;
}
nav ul{
float: right;
margin-right: 20px;
}
nav ul li{
 display: inline-block;
 line-height:80px;
 margin: 0 5px;
}
nav ul li a{
 color: white;
 font-size: 17px;
 text-transform:uppercase;
}
</style>
</head>
<body>
<nav>
<label class="logo">Home Page </label>
<ul>
 <li><a href="#">Home</a></li>
  <li><a href="#">About</a></li>
   <li><a href="#">Services</a></li>
    <li><form action=HomePageServlet>
    <input type=submit value=logout>
   
    </form></li>
 
</ul>
</nav>


<h2> Hi ${username}</h2><br/>
<p> 
<%

	String url = ((HttpServletRequest) request).getRequestURL().toString();
	String s[] = url.split("/");
	if(!s[s.length-1].equals("welcome.jsp")){
		
     out.println(s[s.length-1]);
		
	}
	
	
%> </p>












</body>
</html>