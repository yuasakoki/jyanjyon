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
import model.HeroLoad;

@WebServlet("/Town")
public class Town extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hoge = request.getParameter("hoge");

		if(hoge == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/town.jsp");
			dispatcher.forward(request, response);

		}else if(hoge.equals("newGame")) {
			HeroLoad heroLoad = new HeroLoad();
			List<Hero> hero = heroLoad.heroLoad("C:\\config\\game.properties");

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			session.setAttribute("hero", hero);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/town.jsp");
			dispatcher.forward(request, response);
		}else if(hoge.equals("loadGame")) {
			HeroLoad heroLoad = new HeroLoad();
			List<Hero> hero = heroLoad.heroLoad("C:\\config\\gameSave.properties");

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			session.setAttribute("hero", hero);

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/town.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
