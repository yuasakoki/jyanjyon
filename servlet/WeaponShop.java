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

import dao.WeaponDao;
import model.Hero;
import model.WeaponsRecord;


@WebServlet("/WeaponShop")
public class WeaponShop extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/weapon.jsp");
		dispatcher.forward(request, response);


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("radio");
		HttpSession session = request.getSession();
		String message = "";
		//主人公のステータスを持ってくる。
		List<Hero> heroList = (List<Hero>)session.getAttribute("hero");

		//テスト用if文(テスト以外の時は消してください）
		if(heroList.get(0).getGold()==0) {
			heroList.get(0).setGold(heroList.get(0).getGold());	//テスト用⇒+10000);
		}



		if(id == null) {
		//見るを押すとデータベースから全件検索をして武器リスト表示する。
			WeaponDao dao = new WeaponDao();
			List<WeaponsRecord> weaponsRecord = dao.selectAll();
			request.setAttribute("WeaponsRecord",weaponsRecord);
			}

		if(id !=null) {
		//ラジオボタンの選択からid検索をしてその武器の攻撃力を参照してsetWeaponPowerする。
			WeaponDao dao = new WeaponDao();
			List<WeaponsRecord> weaponsRecord = dao.selectId(id);

			//勇者が持っているお金 - アイテムの金額が0出なければ
			if(heroList.get(0).getGold()-weaponsRecord.get(0).getHanbai() >=0) {
				//お金を払う
				heroList.get(0).setGold(heroList.get(0).getGold()-weaponsRecord.get(0).getHanbai());
				//武器の攻撃力をHeroのフィールドにsetWeaponPowerする。
				heroList.get(0).setWeaponPower(weaponsRecord.get(0).getAttack_power());
				message ="まいどぉ！ また来てくれ！";
			}else {
				message ="おい、お金が足りねぇぞ。";

			}
			request.setAttribute("message", message);
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/weapon.jsp");
		dispatcher.forward(request, response);
	}

}






