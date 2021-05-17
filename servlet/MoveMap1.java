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
import model.MoveCounter1;


@WebServlet("/MoveMap1")
public class MoveMap1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッションスコープのインスタンス
		HttpSession session = request.getSession();
		MoveCounter1 moveCounter1 = (MoveCounter1) session.getAttribute("moveCounter1");
//		移動値(回数)の初期化
		if(moveCounter1 == null) {	moveCounter1 = new MoveCounter1();	}
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String msg = "さあ、目的地へ向かいましょう‥";	//通常メッセージ(<h1>内)に表示
		request.setAttribute("msg", msg);				//通常メッセージの保存(ﾘｸｴｽﾄｽｺｰﾌﾟ)
		String action = request.getParameter("action");//ゲットメソッドのアクションフィールド
//ボスとの戦闘要求時
		MonsterDAO monsterDAO = new MonsterDAO();	//モンスターDB接続用DAOｸﾗｽ呼出
		if(action.equals("boss")) {		//『中に入る』選択でボス(親父)との戦闘へ
			List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(100);
			session.setAttribute("monsterRecord", monsterRecord);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
			dispatcher.forward(request, response);
		} else {
//集計モデル呼出(28行目)：ここで移動方向を集計している(カウント増やすだけ故、ﾛｼﾞｯｸｸﾗｽを設けなかった)
			if(action.equals("upMov")) {
				moveCounter1.setUpMove(moveCounter1.getUpMove() + 1);	//ゲッターから取り出してセッターに加算
			}else if(action.equals("leftMov")){
				moveCounter1.setLeftMove(moveCounter1.getLeftMove() + 1);	//ゲッターから取り出してセッターに加算
			}else if(action.equals("rightMov")){
				moveCounter1.setRightMove(moveCounter1.getRightMove() + 1);	//ゲッターから取り出してセッターに加算
			}else if(action.equals("downMov")){
				moveCounter1.setDownMove(moveCounter1.getDownMove() + 1);	//ゲッターから取り出してセッターに加算
			}else if(action.equals("back")) {
				moveCounter1.setMove(moveCounter1.getMove() - 2);	//ゲッターから取り出してセッターに加算
			}
			moveCounter1.setMove(moveCounter1.getMove() + 1);
			session.setAttribute("moveCounter1", moveCounter1);	//セッションスコープに保存
	//	移動回数合計（ボス(一応100番)等のイベント出現条件）のフィールド
			int mov = moveCounter1.getMove();
			int upM = moveCounter1.getUpMove();
			int leftM = moveCounter1.getLeftMove();
			int rightM = moveCounter1.getRightMove();
			int downM = moveCounter1.getDownMove();
	//	100までの乱数でダンジョンマップの行動結果が選択される。
			int param = new java.util.Random().nextInt(99) + 1;
	//		System.out.println("★合計歩数：" + mov);	//マップ移動数をチェックしたい場合はコメントアウトを外してください
			//イベント(ボス対戦や次のマップへ進行等)発生の条件
			if((upM >= 30 || leftM >= 30 || rightM >= 30 || downM >= 30) && mov % 4 == 0) {//条件を満たすと新しい入口へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/toride.jsp");
				dispatcher.forward(request, response);
			}else if(mov > 20 && mov % 6 == 0) {//条件を満たすと、5マップ移動ごとにボスの居場所へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/kaidan1.jsp");
				dispatcher.forward(request, response);
		//雑魚モンスターが出てくる
			}else if(param <= 20){
		//10までの乱数と同じNOのモンスターの値をリストに入れる。
	//		int param =1;//※テストモードでモンスターidを指定する場合(左表示の1行と差し替える)
				int choice = new java.util.Random().nextInt(9) + 1;	//↓DAO(DB)から敵の選出
				List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(choice);
				session.setAttribute("monsterRecord", monsterRecord);
		//戦闘画面にフォワード（jspは必要ならば書き換えてください)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
				dispatcher.forward(request, response);
			}else if(param >= 21 && param <= 22) {					//↓DAO(DB)から敵の選出
				int choice = 101;
				List<MonsterRecord> monsterRecord = monsterDAO.monsterSelect(choice);
				session.setAttribute("monsterRecord", monsterRecord);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/battle.jsp");
				dispatcher.forward(request, response);
			}else{	//本当はここでエンカウントか宿屋街(96～100)か等にさらに振り分けたい⇒else if(param >= 23 && param <= 95) {
				session.removeAttribute("dungeonCode");			//前のマップ(道生成)乱数の消去
				Integer ran1 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
				Integer ran2 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
				Integer ran3 = new java.util.Random().nextInt(2);	//乱数生成(次のフォワード先用)
				List<Integer> dungeonCode = new ArrayList<Integer>();	//乱数をリストへ格納
				dungeonCode.add(ran1);
				dungeonCode.add(ran2);	//	⇕	乱数をリストへ格納
				dungeonCode.add(ran3);
				session.setAttribute("dungeonCode", dungeonCode);	//セッションスコープへ保存
				if(action.equals("upMov")) {	//上に移動がセットメソッドされた場合
					RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap0.jsp");
					dispatcher.forward(request, response);
				}else if(action.equals("leftMov")){//左に移動がセットメソッドされた場合
					RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap1.jsp");
					dispatcher.forward(request, response);
				}else if(action.equals("rightMov")){//右に移動がセットメソッドされた場合
					RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap2.jsp");
					dispatcher.forward(request, response);
				}else if(action.equals("downMov")){//下に移動がセットメソッドされた場合
					RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap3.jsp");
					dispatcher.forward(request, response);
				}else if(action.equals("back")){//戻るがセットメソッドされた場合
					RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap.jsp");
					dispatcher.forward(request, response);
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
