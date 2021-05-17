package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hero;
import model.MonsterRecord;

@WebServlet("/Friend")
public class Friend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String hoge = request.getParameter("hoge");
		List<Hero> heroList = (List<Hero>)session.getAttribute("hero");
		List<MonsterRecord> m = (List<MonsterRecord>)session.getAttribute("monsterRecord");
		String name = m.get(0).getName();
		int maxHp = m.get(0).getMaxHp();
		int hp = maxHp;
		int power = m.get(0).getPower();
		int defense = m.get(0).getDefense();
		Hero hero2 = new Hero(name,hp,maxHp,power,defense);
		if(heroList.size() != 1) {
			heroList.remove(1);
		}
		heroList.add(hero2);
		session.setAttribute("hero", heroList);
		System.out.println(heroList.get(1).getName());

//		フォワードする前にマップの通路の有無を乱数生成して再度スコープへ保存する
		session.removeAttribute("dungeonCode");	//古いマップ用乱数の消去
		Integer ran1 = new java.util.Random().nextInt(2);
		Integer ran2 = new java.util.Random().nextInt(2);
		Integer ran3 = new java.util.Random().nextInt(2);
		List<Integer> dungeonCode = new ArrayList<Integer>();
		dungeonCode.add(ran1);
		dungeonCode.add(ran2);
		dungeonCode.add(ran3);
		session.setAttribute("dungeonCode", dungeonCode);//↑乱数ココまで

//敵を倒したときのメッセージ(<h1>内)に表示
		String msg = "敵を倒した、目的地へ向かいましょう‥";
		request.setAttribute("msg", msg);

//↑フォワード前にマップ用乱数の作成↑
		if(hoge.equals("boss")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/castle.jsp");
			dispatcher.forward(request, response);

		} else if(hoge.equals("inToride")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("tMap.jsp");
			dispatcher.forward(request, response);

		} else if(hoge.equals("zako")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("janmap.jsp");
			dispatcher.forward(request, response);
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
