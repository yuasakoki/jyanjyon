<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.WeaponsRecord"%>
<%@page import="java.util.List"%>

<!-- ステータスのインポート -->
<%@ page import = "model.Hero" %>
<%
//全検索の武器リストを取得
List<WeaponsRecord> WeaponList =
(List<WeaponsRecord>)request.getAttribute("WeaponsRecord");

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
<body class="weaponShop">
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
<div align="center">
<img src="img/bukiya.png" width=400px><br>
<% if(WeaponList == null&&message == null) { %>
<table border = "1" style = "width:700px" bordercolor="#ffff25"  class="select">
<tr><th>
へいっらっしゃい！！！ 最高の武器がそろってるぜぇっ！！
</th></tr>
</table>
<br>
<form action="/janjyon/WeaponShop"method="post">
<input type="submit" value="見る"><br>
<% } %>
</form>


<% if(WeaponList != null) { %>
<form action="/janjyon/WeaponShop"method="post">
	<table border = "1" style = "width:700px" bordercolor="#ffff25">
			<tr>
				<th>選択</th>
				<th>商品ID</th>
				<th>商品名</th>
				<th>販売単価</th>
				<th>攻撃力</th>
			</tr>
			<% for(WeaponsRecord WeaponsRecord : WeaponList) { %>
			<tr>
				<td><input type="radio" name="radio" value="<%= WeaponsRecord.getWeaponId() %>"></td>
				<td><%= WeaponsRecord.getWeaponId() %></td>
				<td><%= WeaponsRecord.getWeaponMei() %></td>
				<td><%= WeaponsRecord.getHanbai() %></td>
				<td><%= WeaponsRecord.getAttack_power() %></td>

			</tr>
						<% } %>
	</table>
	<br>
	<br>
<!-- 	<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select"> -->
	<input type="submit" value="買う">
<!-- 	</table> -->
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
<br>
<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select">
<tr><th><a href="/janjyon/Town">出口</a><br></th></tr>
</table>
</div>
</body>
</html>