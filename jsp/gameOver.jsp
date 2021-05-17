<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String dieMsg = (String)request.getAttribute("dieMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ジャンジョン</title>
<link rel="stylesheet" type="text/css" href="all.css">
</head>
<body class="gameover">
<div  align="center">
<%if(dieMsg != null){ %>
<table border = "1" style = "width:700px" bordercolor="#ffff25"  class="select">
<tr><th>
<h1 class="center"><%= dieMsg %></h1>
</table>

<%} %>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<p class="center"><strong><a href="index.jsp">タイトルへ⇒</a></strong></p>
</div>
</body>
</html>