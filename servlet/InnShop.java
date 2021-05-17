package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hero;
import model.HeroSave;

@WebServlet("/InnShop")
public class InnShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/inn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Hero> hero =(List<Hero>)session.getAttribute("hero");
		String message = "";
		//勇者が持っているお金 - 宿代（100固定）が0以上ならば
		if(hero.get(0).getGold()-100 >=0) {
			//100払う
			hero.get(0).setGold(hero.get(0).getGold()-100);
			int maxHp = hero.get(0).getMaxHp();
			int maxMp = hero.get(0).getMaxMp();

//			int heroHp = h.get(0).getHp();
//			int heroMp = h.get(0).getMp();
//			heroHp = maxHp;
//			heroMp = maxMp;

			hero.get(0).setHp(maxHp);
			hero.get(0).setMp(maxMp);

			HeroSave heroSave = new HeroSave(hero);
			message ="ありがとうございました。いってらっしゃい、すぐ帰ってきてね♥  ウフフ";
		}else {
			message ="あなた、お金が足りないわよ。。。";
		}
		request.setAttribute("message", message);





		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/inn.jsp");
		dispatcher.forward(request, response);

	}

}
