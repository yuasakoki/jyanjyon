<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ステータスのインポート -->
<%@ page import = "model.Hero,java.util.List" %>
<%
//ステータスのセット
List<Hero> heroList = (List<Hero>)session.getAttribute("hero");

//リクエストスコープに保存されたメッセージを取得
String message = (String)request.getAttribute("message");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ジャンジョン</title>
<link rel="stylesheet" type="text/css" href="all.css">
</head>

<body class="inn">
<div align="center">
<!-- ヒーローと仲間のステータス表示 -->
<div align="right">
	<table border = "1" style = "width:380px" class="status" bordercolor="#ffff25" bgcolor="red">
		<tr>
			<th>
				<h1>
				Lv:<%=heroList.get(0).getLevel() %> <%= heroList.get(0).getName() %> HP：<%= heroList.get(0).getHp() %>/<%= heroList.get(0).getMaxHp() %>
				MP:<%= heroList.get(0).getMp() %>/<%= heroList.get(0).getMaxMp() %><br>
				攻撃力:<%= heroList.get(0).getPower() %> 防御力:<%= heroList.get(0).getDefense() %> 必殺技力:<%= heroList.get(0).getSpecial_power() %><br>
				所持金：<%= heroList.get(0).getGold() %>  経験値:<%= heroList.get(0).getExperience() %>
				</h1>
			</th>
		</tr>
		<tr>
			<%if(heroList.size() != 1){ %>
				<th>
				<h1>
					<%= heroList.get(1).getName() %> HP：<%= heroList.get(1).getHp() %>/<%= heroList.get(1).getMaxHp() %><br>
					攻撃力:<%= heroList.get(1).getPower() %> 防御力:<%= heroList.get(1).getDefense() %>
				</h1>
				</th>
			<%} %>
		</tr>
	</table>
</div>
<img src="img/inn.png" width=300px>

<% if(message == null) { %>
<table border = "1" style = "width:700px" bordercolor="#ffff25"  class="select">
<tr><th>おかえりなさい♥<br>
１００Gで宿泊（セーブ）しますか？？<br>
</th></tr>
</table>
<form action="/janjyon/InnShop" method="post">
<input type="submit" value="宿泊する">
</form>
<% } %>


<% if(message != null) { %>
<table border = "1" style = "width:700px" bordercolor="#ffff25"  class="select">
<tr><th>
<p><%= message %></p>
</th></tr>
</table>
<% } %>
<br>
<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select">
<tr><th><a href="/janjyon/Town">出口</a><br></th></tr>
</table>
</div>

</body>
</html>