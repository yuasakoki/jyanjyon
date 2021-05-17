<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
if(msg == null) {	msg = "";	}
//３つの乱数の値をリストに入れているものを受け取る
List<Integer> dungeonCode = (List<Integer>)session.getAttribute("dungeonCode");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> == 冒険中 == </title>
<link rel="stylesheet" type="text/css" href="back_img.css">
<style>
        body {
            background-color: #556b2f
        }
</style>
</head>
<body>
<header>
<div style=" background-color: #ffffff;">
<h1><%= msg %></h1>
<form action="/janjyon/MoveMap1" method="post">
薬草<button  name ="jspMap" value="janmap2.jsp">使用</button>
</form>

<%
String vision1 = "";
String vision2 = "";
String vision3 = "";
String vi1 = "";
String vi2 = "";
String vi3 = "";
String v1 = "";
String v2 = "";
String v3 = "";
String v4 = "";
String v5 = "";
String v6 = "";
//Integerをintに変換する。
int ran1 = dungeonCode.get(0);
int ran2 = dungeonCode.get(1);
int ran3 = dungeonCode.get(2);
if(ran1 != 1){	vi1 = "_1";  v1 = "<a href=\"/janjyon/MoveMap1?action=rightMov\">";	 v2 = "</a>";
			vision1 = "<a href=" + "\"/janjyon/MoveMap1?action=rightMov\"" + ">右へ進む　　</a>";}
if(ran2 != 1){	vi2 = "_2";	v3 = "<a href=\"/janjyon/MoveMap1?action=upMov\">";	 v4 = "</a>";
			vision2 = "<a href=" + "\"/janjyon/MoveMap1?action=upMov\"" + " >上へ進む　　</a>";}
if(ran3 != 1){	vi3 = "_2";	v5 = "<a href=\"/janjyon/MoveMap1?action=downMov\">";	 v6 = "</a>";
			vision3 = "<a href=" + "\"/janjyon/MoveMap1?action=downMov\"" + ">下へ進む　　</a>";}
%>
<p>進行方向を選択してください　
<strong><%= vision1 %></strong>
<strong><%= vision2 %></strong>
<strong><%= vision3 %></strong>
<strong><a href="/janjyon/MoveMap1?action=back">戻る(後退する)　　</a></strong>
</p>
</div>
</header>
<main>
<div id="sc1" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc2" ><%= v3 %><img src="img/bac1<%= vi2 %>.jpg" width="320" height="180"><%= v4 %></div>
<div id="sc3" ><img src="img/bac1.jpg" width="320" height="180"></div>

<div id="sc4" ><img src="img/bac1_1p.jpg" width="320" height="180"></div>
<div id="sc5" ><img src="img/bac1_10.jpg" width="320" height="180"></div>
<div id="sc6" ><%= v1 %><img src="img/bac1<%= vi1 %>.jpg" width="320" height="180"><%= v2%></div>

<div id="sc7" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc8" ><%= v5 %><img src="img/bac1<%= vi3 %>.jpg" width="320" height="180"><%= v6 %></div>
<div id="sc9" ><img src="img/bac1.jpg" width="320" height="180"></div>
</main>
</body>
</html>