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

import dao.MonsterDAO;
import model.Hero;
import model.MonsterRecord;
import model.MoveCounter2;


@WebServlet("/MoveMap2")
public class MoveMap2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッションスコープのインスタンス
		HttpSession session = request.getSession();
		MoveCounter2 moveCounter2 = (MoveCounter2) session.getAttribute("moveCounter2");
//		移動値(回数)の初期化
		if(moveCounter2 == null) {	moveCounter2 = new MoveCounter2();	}
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		MonsterDAO monsterDAO = new MonsterDAO();	//モンスターDB接続用DAOｸﾗｽ呼出
		String action = request.getParameter("action");//ゲットパラメータ(ゲットメソッド値取得)
		//ボスとの戦闘要求時
		if(action.equals("lastFight")) {
			List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(102);
			session.setAttribute("monsterRecord", monsterRecord);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
			dispatcher.forward(request, response);
		}else {
			session.removeAttribute("dungeonCode");			//前のマップ(道生成)乱数の消去
			Integer ran1 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
			Integer ran2 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
			Integer ran3 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
			List<Integer> dungeonCode = new ArrayList<Integer>();	//乱数をリストへ格納
			dungeonCode.add(ran1);
			dungeonCode.add(ran2);	//	⇕	乱数をリストへ格納
			dungeonCode.add(ran3);
			session.setAttribute("dungeonCode", dungeonCode);	//セッションスコープへ保存
			if(action.equals("upFloor")) {
				moveCounter2.setFloor(moveCounter2.getFloor() + 1);	//ゲッターから取り出してセッターに加算
				session.setAttribute("moveCounter2", moveCounter2);	//セッションスコープに保存
				String msg = "『ここは" + (moveCounter2.getFloor()) + "階だ!』魔王を探し出し、倒さなければ!!";	//更新メッセージ(<h1>内)に表示
				request.setAttribute("msg", msg);					//セッションスコープに保存
				RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap0.jsp");
				dispatcher.forward(request, response);
			 }else {
					if(action.equals("back")) {	moveCounter2.setMove(moveCounter2.getMove() - 2);}	//ゲッターから取り出してセッターに加算

//		移動情報を集計している(カウント増やすだけ故、ﾛｼﾞｯｸｸﾗｽは作らず)
					int floor = moveCounter2.getFloor();	//砦の何階かを示す変数
					String msg = "『ここは" + floor + "階だ!』魔王を探し出し、倒さなければ!!";	//通常メッセージ(<h1>内)に表示
					request.setAttribute("msg", msg);				//通常メッセージの保存(ﾘｸｴｽﾄｽｺｰﾌﾟ)
					moveCounter2.setMove(moveCounter2.getMove() + 1);
					session.setAttribute("moveCounter2", moveCounter2);	//セッションスコープに保存
			//	移動回数合計（階段等のイベント出現条件）のフィールド
					int mov = moveCounter2.getMove();
			//	100までの乱数でダンジョンマップの行動が選択される。
					int param = new java.util.Random().nextInt(99) + 1;
			//	int param =1;//テストモードでモンスターidを指定する場合(上の1行と差し替える)
					if(floor >= 3 && mov >= 50 && mov % 5 == 0) {//条件を満たすと最終イベントへフォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/lastFight.jsp");
						dispatcher.forward(request, response);
					}else if(mov >= 20 && mov % 10 == 0) {//条件を満たすと、10マップ移動ごとに階段へフォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/kaidan2.jsp");
						dispatcher.forward(request, response);
				//雑魚モンスターが出てくる
					}else if(param <= 20){
				//10までの乱数と同じNOのモンスターの値をリストに入れる。
						int choice = new java.util.Random().nextInt(9) + 11;	//↓DAO(DB)から敵の選出
						List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(choice);
						session.setAttribute("monsterRecord", monsterRecord);
				//戦闘画面にフォワード（jspは必要ならば書き換えてください)
						RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
						dispatcher.forward(request, response);
					}else if(param >= 21 && param <= 22) {					//↓DAO(DB)から敵の選出
						int choice = 99;		//←この99番は国士無双ボスをノーマル強敵として出す予定です
						List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(choice);
						session.setAttribute("monsterRecord", monsterRecord);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
						dispatcher.forward(request, response);
					}else{	//移動方向別に次のマップjsp表示＝フォワード先が変わる
						if(action.equals("upMov")) {
							RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap0.jsp");
							dispatcher.forward(request, response);
						}else if(action.equals("leftMov")){
							RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap1.jsp");
							dispatcher.forward(request, response);
						}else if(action.equals("rightMov")){
							RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap2.jsp");
							dispatcher.forward(request, response);
						}else if(action.equals("downMov")){
							RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap3.jsp");
							dispatcher.forward(request, response);
						}else if(action.equals("back")){
							RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap.jsp");
							dispatcher.forward(request, response);
						}
				}
			 }
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッションスコープのインスタンス
		HttpSession session = request.getSession();
		//ヒーローのインスタンス
		List<Hero> hero = (List<Hero>)session.getAttribute("hero");
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		//ダンジョンマップ内で薬草を使うと
		//使ったダンジョンマップのJSPを値として持ってきます。
		String jspMap = request.getParameter("jspMap");

		//ヒーローの生成
		String heroName = hero.get(0).getName();
		int heroHp 	= hero.get(0).getHp();
		int maxHp = hero.get(0).getMaxHp();
		int heroHerb = hero.get(0).getHerb();
		String msg ="";

		//ダンジョン内でアイテムを使うと送られる値がnull出ないとき、
		if(jspMap != null) {
			//薬草を使う処理
			if(heroHerb > 0) {
				msg = heroName + "は薬草を使った。" + heroName + "HPを15回復した";
				heroHerb -= 1;
				heroHp += 15;
				if(heroHp > maxHp) {
					heroHp = maxHp;
				}
			//体力が満タンの時回復できずアイテムも減らない処理
			}else if(heroHp == maxHp) {
				msg = heroName + "体力は満タンだ。";
			//薬草を持っていないときの処理
			}else {
				msg = heroName + "は、薬草を持っていなかった。";
			}
		}
		request.setAttribute("msg", msg);

//			ヒーローの情報を変更
			hero.get(0).setHp(heroHp);
			hero.get(0).setHerb(heroHerb);

			//アイテムを使ったところにフォワードします。
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspMap);
			dispatcher.forward(request, response);

	}
}
