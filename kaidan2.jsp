<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
if(msg == null) {	msg = "";	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>== 冒険中 ==</title>
<link rel="stylesheet" type="text/css" href="back_img.css">
</head>
<body>
<header>
<h1><%= msg %></h1>
<%
int ran1 = new java.util.Random().nextInt(2);
int ran2 = new java.util.Random().nextInt(2);
int ran3 = new java.util.Random().nextInt(2);
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
if(ran1 != 1){	vi1 = "_1";	v1 = "<a href=\"/janjyon/MoveMap2?action=leftMov\">";	 v2 = "</a>";
			vision1 = "<a href=" + "\"/janjyon/MoveMap2?action=leftMov\"" + ">左へ進む　　</a>";}
if(ran2 != 1){	vi2 = "_2";	v3 = "<a href=\"/janjyon/MoveMap2?action=upMov\">";	 v4 = "</a>";
			vision2 = "<a href=" + "\"/janjyon/MoveMap2?action=upMov\"" + " >上へ進む　　</a>";}
if(ran3 != 1){	vi3 = "_1";	v5 = "<a href=\"/janjyon/MoveMap2?action=rightMov\">";	 v6 = "</a>";
			vision3 = "<a href=" + "\"/janjyon/MoveMap2?action=rightMov\"" + ">右へ進む　　</a>";}
%>
<p>　階段を上りますか？　　<strong><a href="/janjyon/MoveMap2?action=upFloor">階段を上がる　　</a></strong>
<strong><%= vision1 %></strong>
<strong><%= vision2 %></strong>
<strong><%= vision3 %></strong>
<strong><a href="/janjyon/MoveMap2?action=back">戻る(後退する)　　</a></strong>
</p>
</header>
<main>
<div id="sc1" ><img src="img/bac2.png" width="320" height="180"></div>
<div id="sc2" ><%= v3 %><img src="img/bac2<%= vi2 %>.png" width="320" height="180"><%= v4 %></div>
<div id="sc3" ><img src="img/bac2.png" width="320" height="180"></div>

<div id="sc4" ><%= v1 %><img src="img/bac2<%= vi1 %>.png" width="320" height="180"><%= v2 %></div>
<div id="sc5" ><img src="img/kaidan_p.png" width="320" height="180"></div>
<div id="sc6" ><%= v5 %><img src="img/bac2<%= vi3 %>.png" width="320" height="180"><%= v6 %></div>

<div id="sc7" ><img src="img/bac2.png" width="320" height="180"></div>
<div id="sc8" ><img src="img/bac2_2.png" width="320" height="180"></div>
<div id="sc9" ><img src="img/bac2.png" width="320" height="180"></div>
</main>
</body>
</html>