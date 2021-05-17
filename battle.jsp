<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ステータスのインポート -->
<%@ page import = "model.Hero,model.MonsterRecord,model.Msg,java.util.List" %>
<%
//ヒーローのステータスのセット

List<Hero> heroList = (List<Hero>)session.getAttribute("hero");
//モンスターのステータスのセット
List<MonsterRecord> monsterList = (List<MonsterRecord>)session.getAttribute("monsterRecord");
//レベルアップのメッセージ
List<Msg> level =(List<Msg>)request.getAttribute("msgList");
//ヒーローのセリフ
String heroMsg = (String)request.getAttribute("heroMsg");
//仲間のセリフ
String friendMsg = (String)request.getAttribute("friendMsg");
//モンスターのセリフ
String monsterMsg = (String)request.getAttribute("monsterMsg");
//モンスターの死んだセリフ
String monsterDie = (String)request.getAttribute("monsterDie");
//仲間が死んだセリフ
String friendDie = (String)request.getAttribute("friendDie");
//ひろったゴールド
String gold = (String)request.getAttribute("gold");
//もらった経験値
String experience = (String)request.getAttribute("experience");
//奪ったfp
String fp = (String)request.getAttribute("fp");
//ボスを倒した後に城へ戻るか
String boss = (String)request.getAttribute("boss");
//仲間になるかどうか
String friend = (String)request.getAttribute("friend");

boolean f = false;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ジャンジョン</title>
<link rel="stylesheet" type="text/css" href="bac.css">
</head>
<body>
<header>
<div id="sc10" >
<div>
<div>
<% if(monsterDie == null){ %>
<form class="float" id="sc11" action="/janjyon/Battle" method="post">
<br>　敵名:<br><%= monsterList.get(0).getName() %><br>
<%-- 	HP:<%= monsterList.get(0).getHp() %>	--%>
<strong></strong><p class="down">どうする？？</p></strong>
	<select name="koudou">
	<option value="">選択してください</option>
	<option value="攻撃">攻撃</option>
	<option value="必殺技">必殺技</option>
	<option value="防御">防御</option>
	<option value="回復薬">回復薬</option>
	<option value="逃げる">逃げる</option>
	</select>
	<input type="submit" value="決定">
</form>
</div>

<div class="float" id="sc12">
<ul class="float">
<li class="float4">Lv:</li>
<li class="float2"><%=heroList.get(0).getLevel() %></li>
<li class="float4">Name:</li>
<li class="float2"><%= heroList.get(0).getName() %></li>
<li class="float4">HP：</li>
<li class="float2"><%= heroList.get(0).getHp() %>/<%= heroList.get(0).getMaxHp() %></li>
<li class="float4">MP:</li>
<li class="float2"><%= heroList.get(0).getMp() %>/<%= heroList.get(0).getMaxMp() %></li>
<li class="float4">攻撃力:</li>
<li class="float2"><%= heroList.get(0).getPower() %></li>
<li class="float4">防御力:</li>
<li class="float2"><%= heroList.get(0).getDefense() %></li>
<li class="float4">必殺力:</li>
<li class="float2"><%= heroList.get(0).getSpecial_power() %></li>
</ul></div>

<div id="sc13" class="float" >
<% if(heroList.size() != 1){%>
<ul class="float">
<li class="float4">Name:</li>
<li class="float3"><%= heroList.get(1).getName() %></li>
<li class="float4">HP：</li>
<li class="float3"><%= heroList.get(1).getHp() %>/<%= heroList.get(1).getMaxHp() %></li>
<li class="float4">攻撃力:</li>
<li class="float3"><%= heroList.get(1).getPower() %></li>
<li class="float4">防御力:</li>
<li class="float3"><%= heroList.get(1).getDefense() %></li>
<%} %></ul></div>

<div id="sc14" class="float" >
<ul class="float">
<li class="float2">所持金：</li>
<li class="float3"><%= heroList.get(0).getGold() %></li>
<li class="float2">経験値:</li>
<li class="float3"><%= heroList.get(0).getExperience() %></li>
</ul></div>
</div>
</header>
<main>
<div id="sc20" >

<p class="center" >
<img src="img/<%= monsterList.get(0).getNo() %>.png" width="300" height="300">
</p>

</div>
</main>
<footer>
<div id="sc30" >
<% if(heroMsg == null){%>
	<p><%= monsterList.get(0).getName() %>が現れた</p>
<% } %>

<%} %>

<%if(heroMsg != null){ %>
	<p><%= heroMsg %></p>
<%} %>

<%if(friendMsg != null){%>
	<p><%= friendMsg %></p>
<%} %>

<%if(monsterMsg != null){ %>
	<p><%= monsterMsg %></p>
	<%if(friendDie != null){%>
		<p><%= friendDie %></p>
	<%} %>
<%} %>

<%if(monsterDie != null){ %>
			<br><br><br><br><br><br><br><br><br><br>
	<p class="center" ><%= monsterDie %></p>
	<%if(gold != null){ %>
		<p class="center" ><%= gold %></p>
		<p class="center" ><%= experience %></p>
	<%} %>


	<% if(level != null){ %>
		<% for(Msg msg : level){ %>
			<p class="center" ><%= msg.getLevelMsg() %></p>
			<p class="center" ><%= msg.getHpMsg() %></p>
			<p class="center" ><%= msg.getMpMsg() %></p>
			<p class="center" ><%= msg.getPowerMsg() %></p>
			<p class="center" ><%= msg.getDefenseMsg() %></p>
			<p class="center" ><%= msg.getSpecialMsg() %></p>
		<% } %>
	<% } %>


	<% if(friend != null){%>
		<p class="center" ><%= friend %></p><br><br>
		<% f = true; %>
	<% } %>

	<%if(f){ %>

		<%if(monsterList.get(0).getNo() >= 100){ %>

			<p class="center" ><a href="/janjyon/Friend?hoge=boss">仲間にして、城へ</a></p><br>
		<%}else if(monsterList.get(0).getNo() >= 10 & monsterList.get(0).getNo() <= 99){%>
		<p class="center" ><a href="/janjyon/Friend?hoge=inToride">仲間にして、次へ</a></p><br>
		<%}else{%>
		<p class="center" ><a href="/janjyon/Friend?hoge=zako">仲間にして、次へ</a></p><br>
		<%}%>
	<%}%>

	<% if(monsterList.get(0).getNo() >= 100){ %>
		<p class="center" ><a href="/janjyon/Castle">城へ</a></p><br>
	<%}else if(monsterList.get(0).getNo() >= 10 & monsterList.get(0).getNo() <= 99){%>
		<p class="center" ><a href="/janjyon/Battle?action=inToride">次へ</a></p><br>
	<%}else{%>
		<p class="center" ><a href="/janjyon/Battle?action=else">次へ</a></p>
	<%} %>

<%} %>
</div>
</footer>
</body>
</html>