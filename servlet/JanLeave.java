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


@WebServlet("/JanLeave")
public class JanLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();//		セッションスコープのインスタンス呼出
			String action = request.getParameter("action");//	ゲットメソッドの属性の呼出
			if(action.equals("reset")) {			//	ゲットメソッドの属性：リセットの場合
				session.setAttribute("moveCounter1", null);		//マップ(1面)の移動集計データを消去
				session.setAttribute("moveCounter2", null);		//砦内の移動集計データを消去
				RequestDispatcher dispatcher = request.getRequestDispatcher("/janTest.jsp");
				dispatcher.forward(request, response);
			}else if(action.equals("noReset")) {	//	ゲットメソッドの属性：リセットなしの場合
				session.setAttribute("moveCounter2", null);		//砦内の移動集計データを消去
				session.removeAttribute("dungeonCode");	//古いマップ用乱数の消去
//				フォワードする前にマップの通路の有無を乱数生成して再度スコープへ保存する
				Integer ran1 = new java.util.Random().nextInt(2);
				Integer ran2 = new java.util.Random().nextInt(2);
				Integer ran3 = new java.util.Random().nextInt(2);
				List<Integer> dungeonCode = new ArrayList<Integer>();
				dungeonCode.add(ran1);
				dungeonCode.add(ran2);
				dungeonCode.add(ran3);
				session.setAttribute("dungeonCode", dungeonCode);//↑乱数ココまで
				RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap.jsp");
				dispatcher.forward(request, response);
			}

		}
}
