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
<h1>さあ、目的地へ向かいましょう ‥</h1>
<p>進行方向を選択してください　
<strong><a href="/janjyon/MoveMap1?action=upMov">上へ進む</a></strong>

</p>
</header>
<main><div id="sc0" >
<div id="sc1" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc2" ><a href="/janjyon/MoveMap1?action=upMov"><img src="img/bac1_2.jpg" width="320" height="180"></a></div>
<div id="sc3" ><img src="img/bac1.jpg" width="320" height="180"></div>

<div id="sc4" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc5" ><img src="img/bac1_2.jpg" width="320" height="180"></div>
<div id="sc6" ><img src="img/bac1.jpg" width="320" height="180"></div>

<div id="sc7" ><img src="img/bac1.jpg" width="320" height="180"></div>
<div id="sc8" ><img src="img/bac1_2.jpg" width="320" height="180"></div>
<div id="sc9" ><img src="img/bac1.jpg" width="320" height="180"></div>
</div></main>
<footer></footer>
</body>
</html>