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

import dao.PropDao;
import model.Hero;
import model.PropRecord;

@WebServlet("/ItemShop")
public class ItemShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/item.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("radio");
		PropDao dao = new PropDao();
		HttpSession session = request.getSession();
		String message = "";

		if(id == null) {
			//見るを押すとデータベースから全件検索をしてアイテムリスト表示する。

			List<PropRecord> PropRecord = dao.selectAll();
			request.setAttribute("PropRecord",PropRecord);

		}if(id !=null) {
			//ラジオボタンの選択からidをHeroのフィールドに入れる。

			//主人公のステータスを持ってくる。
			List<Hero> heroList = (List<Hero>)session.getAttribute("hero");
			//DAOから買ったアイテムを持ってくる。
			List<PropRecord> PropRecord = dao.selectId(id);


			//勇者が持っているお金 - アイテムの金額が0出なければ
			if(heroList.get(0).getGold()-PropRecord.get(0).getHanbai() >=0) {
			//勇者が持っているお金-アイテムの金額する
				heroList.get(0).setGold(heroList.get(0).getGold()-PropRecord.get(0).getHanbai());
				message ="まいどぉ。 いいセンスしてるね。。  へへっ。";


			//idが1の時はHeroのフィールドのherb（薬草)＋1する。
				if(Integer.parseInt(id)==1) {
					heroList.get(0).setHerb(heroList.get(0).getHerb()+1);
			//idが2の時はHeroのフィールドのsuperherb（良質な薬草)＋1する。
				}else if(Integer.parseInt(id)==2) {
					heroList.get(0).setSuperherb(heroList.get(0).getSuperherb()+1);
			//idが3の時はHeroのフィールドのultrasuperherb（超回復できる薬草)＋1する。
				}else if(Integer.parseInt(id)==3) {
					heroList.get(0).setUltrasuperherb(heroList.get(0).getUltrasuperherb()+1);
				}
			}else {
			message ="お金がたりねーよ！";
			}
			request.setAttribute("message", message);
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/item.jsp");
			dispatcher.forward(request, response);
	}

}








