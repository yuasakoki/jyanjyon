<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- ステータスのインポート -->
<%@ page import = "model.Hero,java.util.List" %>
<%
//ステータスのセット
List<Hero> heroList = (List<Hero>)session.getAttribute("hero");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ジャンジョン</title>
<link rel="stylesheet" type="text/css" href="all.css">
</head>
<body class="town">
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
<br>
<br>
<div align="center">
	<table border = "10" style = "width:300px" bgcolor="red" bordercolor="#ffff25" class="select">
		<tr>
			<th align="center">
				城		<a href="/janjyon/Castle">入口</a>
			</th>
		</tr>
	</table>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<div align="center" class="shop">
	<table border = "3" style = "width:800px" bgcolor="red" bordercolor="#ffff25" class="select">
			<tr>
				<th align="center">
					武器店	<a href="/janjyon/WeaponShop">入口</a><br>
				</th>
				<th align="center">
					道具店	<a href="/janjyon/ItemShop">入口</a><br>
				</th>
				<th align="center">
					酒店		<a href="/janjyon/InnShop">入口</a><br>
				</th>
			</tr>
	</table>
</div>
</body>
</html>