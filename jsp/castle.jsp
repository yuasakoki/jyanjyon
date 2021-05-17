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
<body class="castle">
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
<img src="img/king.png" width=400px>
<table border = "1" style = "width:800px" bordercolor="#ffff25">
<tr><th>
<%= heroList.get(0).getName() %>よ、凶悪な魔王が世界を支配しようとしている。<br>
1000人の兵が倒しに向かったのだが、選ばれし人間しか魔王を倒すことができないようだ。<br>
そこで、占い師に頼み、選ばれし人間を探してもらった。そして、君が選ばれたのだ。<br>
さぁ、勇者＜<%= heroList.get(0).getName() %>＞よ旅に出るのだ！！！！
<br></th></tr>
</table>
<br>
<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select">
<tr><th><a href="/janjyon/JanLeave?action=reset">ここから旅に出る</a><br></th></tr>
</table>
<br>
<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select">
<tr><th><a href="/janjyon/JanLeave?action=noReset">前回の場所にワープする</a><br></th></tr>
</table>
<br><br><br>
<table border = "1" style = "width:700px" bordercolor="#ffff25" bgcolor="red" class="select">
<tr><th><a href="/janjyon/Town">出口</a><br></th></tr>
</table>
<br>


</div>
</body>
</html>