<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<script type=text/javascript src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script type=text/javascript>
 
 function callJqueryAjax(){
	 var name = $('#txtName').val();
	 $.ajax({
	 url     : '/LoginServlet',
	 method     : 'POST',
	 data     : {name : name},
	 dataType : "html",
	 success    : function(resultText){
	 $('#result').html(resultText);
	 },
	 error : function(jqXHR, exception){
	 console.log('Error occured!!');
	 }
	 });
	 }
 
</script>
</head>
<body>

<div>
<h1>User Login</h1>
</div>

<form action="LoginServlet" method=post>
<table>
<tr>
<td>Enter Name: </td>
<td><input type =text name="txtName" id=txtName></input></td>
</tr>

<tr>
<td>Enter Password: </td>
<td><input type =password name="txtPwd"></input></td>
</tr>
<tr>

<td><input type =submit onclick="callJqueryAjax()" value=Login></input></td>
<td><input type =submit value=Reset></input></td>
</tr>
</table>
</form>
</body>
</html>