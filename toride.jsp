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
<style>
        body {
            background-color: #556b2f
        }
</style>
</head>
<body>
<header>
<div style=" background-color: #ffffff;">
<h1>ここが、王様が仰っていた 悪党のアジトか!?‥</h1>
<p>アジト(砦)に突入しますか？？　
<strong><a href="/janjyon/MoveMap2?action=back">アジトに入る　　</a></strong>
<strong><a href="/janjyon/MoveMap1?action=back">引き返す　　</a></strong>
</div>
</header>
<main><div id="sc0" >
<div id="sc1" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc2" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc3" ><img src="img/bac1.jpg" width="320" height="180"></div>

<div id="sc4" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc5" ><img src="img/toride.png" width="320" height="180"></div>
<div id="sc6" ><img src="img/bac1.jpg" width="320" height="180"></div>

<div id="sc7" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc8" ><img src="img/bac2p.png" width="320" height="180"></div>
<div id="sc9" ><img src="img/bac1.jpg" width="320" height="180"></div>
</div></main>
<footer></footer>
</body>
</html>