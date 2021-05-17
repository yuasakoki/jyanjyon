package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Hero;
import model.MonsterRecord;
import model.Msg;

@WebServlet("/Battle")
public class Battle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッションスコープのインスタンス
		HttpSession session = request.getSession();
		session.removeAttribute("dungeonCode");	//古いマップ用乱数の消去
//		フォワードする前にマップの通路の有無を乱数生成して再度スコープへ保存する
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


		String action = request.getParameter("action");
		if(action.equals("inToride")) {		//砦内(敵№11～20～99)の戦闘後のフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tMap.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("else")) {	//以外(敵№1～10)の戦闘後のフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/janmap.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		//選択された行動を代入
		String koudou = request.getParameter("koudou");
		//ヒーローのメッセージを送信
		String heroMsg = "";
		//仲間のメッセージ
		String friendMsg = "";
		//モンスターのメッセージを送信
		String monsterMsg = "";
		//死亡判定のメッセージ
		String dieMsg = "";
		//モンスターの死亡メッセージ
		String monsterDie = "";
		//仲間の死亡メッセージ
		String friendDie = "";
		//各々の取得メッセージ
		String gold = "";
		String experience= "";
		//LevelUpのメッセージ
		String levelMsg ="";
		String hpMsg ="";
		String mpMsg ="";
		String powerMsg ="";
		String defenseMsg ="";
		String specialMsg ="";
		String friend = "";
		//ダメージの計算用
		int giveDamage = 0;
		int giveDamage2 = 0;
		int takeDamage = 0;
		//モンスターのターン判定
		boolean monsterTurn = true;
		//仲間のターン
		boolean friendTurn = true;
		//仲間の変数の生成
		String friendName ="";
		int friendHp = 0;
		int friendMaxHp = 0;
		int friendPower = 0;
		int friendDefense = 0;
		//ヒーローのインスタンス
		List<Hero> hero = (List<Hero>)session.getAttribute("hero");
		//ヒーローの生成
		String heroName = hero.get(0).getName();
		int lv 		= hero.get(0).getLevel();
		int heroHp 	= hero.get(0).getHp();
		int heroMp 	= hero.get(0).getMp();
		int maxHp = hero.get(0).getMaxHp();
		int maxMp = hero.get(0).getMaxMp();
		int heroPower = hero.get(0).getPower();
		int heroDefense = hero.get(0).getDefense();
		int heroSpecialPower = hero.get(0).getSpecial_power();
		int heroGold = hero.get(0).getGold();
		int exp = hero.get(0).getExp();
		int heroExperience = hero.get(0).getExperience();
		int heroHerb = hero.get(0).getHerb();
		//モンスターのインスタンス
		List<MonsterRecord> m = (List<MonsterRecord>)session.getAttribute("monsterRecord");
		int no = m.get(0).getNo();
		String monsterName =  m.get(0).getName();
		int monsterHp = m.get(0).getHp();
//		int monsterMp = m.get(0).getMp();
		int monsterPower = m.get(0).getPower();
		int monsterDefense = m.get(0).getDefense();
		int monsterFp = m.get(0).getFp();
		int monsterGold = m.get(0).getGold();
		int monsterExperience = m.get(0).getExperience();


		RequestDispatcher dispatcher = null;


//		行動のスイッチ文
		switch(koudou) {
//		何も選択しなかった場合
		case "":

//			何も選択がなかった場合敵は攻撃してこない
			monsterTurn = false;
			friendTurn = false;

//			ヒーローのセリフ
			heroMsg = "何かを選択してくれ！";
			request.setAttribute("heroMsg", heroMsg);
			break;

//		攻撃を選択
		case "攻撃":
			giveDamage = heroPower - monsterDefense;
			if(giveDamage <= 0) {
				giveDamage = 0;
			}

//		モンスターのHPを計算
			monsterHp -= giveDamage;

//			ヒーローのセリフ
			heroMsg = heroName + "の攻撃！"+ giveDamage +"のダメージをあたえた。";
			request.setAttribute("heroMsg", heroMsg);
			break;

//		必殺技を選択
		case "必殺技":

//			ｍｐ  ある  場合
			if(heroMp > 0) {
				giveDamage = heroSpecialPower;
				heroMp--;

//				もらうダメージが0以上でないといけない
				if(giveDamage <= 0) {
					giveDamage = 0;
				}

//				モンスターのHPを計算
				monsterHp -= giveDamage;

//				ヒーローのセリフ
				heroMsg = heroName + "の必殺技！"+ giveDamage +"のダメージをあたえた。";
				request.setAttribute("heroMsg", heroMsg);

//			ｍｐが  ない  場合
			}else {
//				ヒーローのセリフ
				heroMsg = "MPが足りない。。。";
				request.setAttribute("heroMsg", heroMsg);

			}
			break;


//		防御を選択
		case "防御":

//			ヒーローのセリフ
			heroMsg = heroName + "は防御した。" + monsterName + "はダメージをあたえれない。";
			request.setAttribute("heroMsg", heroMsg);
			friendTurn = false;
			monsterTurn = false;
			break;


//		回復薬を選択
		case "回復薬":
//			回復薬がある場合
			if(heroHerb > 0) {
				heroHp += 15;

//				最大HPを超えない処理
				if(heroHp > maxHp) {
					heroHp = maxHp;
				}

//				回復薬のカウント
				hero.get(0).setHerb(heroHerb-1);

//				ヒーローのセリフ
				heroMsg = heroName + "は薬草を使った" + heroName + "はHPを15回復した";
				request.setAttribute("heroMsg", heroMsg);

				if(hero.size() != 1) {
					friendHp += 15;
					if(friendHp > friendMaxHp) {
						friendHp = friendMaxHp;
					}
					heroMsg = heroName + "は薬草を使った" + heroName + "と"+ friendName +"はHPを15回復した";
					request.setAttribute("heroMsg", heroMsg);

				}

//			回復薬がない場合
			}else {

//				ヒーローのセリフ
				heroMsg = "回復薬を持っていない。。。";
				request.setAttribute("heroMsg", heroMsg);
				monsterTurn = false;
			}
			break;

		case "逃げる":
			if(no >= 100) {
				heroMsg = "逃げることができない。。。";
				request.setAttribute("heroMsg", heroMsg);
				monsterTurn = false;
				friendTurn = false;

			}else {
				int runR = new Random().nextInt(10);
				if(runR < 8) {
					heroMsg = heroName+"は逃げ出した。。そんな自分が嫌いだとをわかっている。こころの中はとても悲しい気持ちでいっぱいだった。せつなく、やるせなかった。背中で泣きながら去っていった。";
					request.setAttribute("heroMsg", heroMsg);
					monsterDie = "";
					request.setAttribute("monsterDie", monsterDie);
					monsterTurn = false;
					friendTurn = false;

				}else {
					heroMsg = "逃げられなかった。";
					request.setAttribute("heroMsg", heroMsg);
					friendTurn = false;
				}
			}

			break;

		}

		//仲間の攻撃（）
		if(hero.size() != 1 && (hero.get(1).getHp() > 0) && friendTurn) {
			//モンスターの生成
			friendName = hero.get(1).getName();
			friendHp 	= hero.get(1).getHp();
			friendMaxHp = hero.get(1).getMaxHp();
			friendPower = hero.get(1).getPower();
			friendDefense = hero.get(1).getDefense();

			//仲間の攻撃確立
			int friendR = new Random().nextInt(10);
			//8０％の確率で攻撃
			if(friendR < 7) {
				giveDamage2 = friendPower - monsterDefense;
				if(giveDamage2 <= 0) {
					giveDamage2 = 0;
				}

				monsterHp -= giveDamage2;

				friendMsg = friendName + "の攻撃！"+ giveDamage2 +"のダメージをあたえた！";
				request.setAttribute("friendMsg", friendMsg);
			//20％の確率でミス
			}else {
				friendMsg = friendName + "の攻撃！ ミスをしてしまった。";
				request.setAttribute("friendMsg", friendMsg);
			}

		}

		//モンスターを倒した判定
		if(monsterHp <= 0) {
			//マイナス表記をなくす
			monsterHp = 0;

//			倒したか、取得ゴールド、取得経験値の表記
			monsterDie = monsterName + "を倒した!";
			request.setAttribute("monsterDie", monsterDie);
			heroGold += monsterGold;
			gold = monsterGold + "Gを拾った！";
			request.setAttribute("gold", gold);
			heroExperience += monsterExperience;
			experience = monsterExperience + "の経験値を得た！";
			request.setAttribute("experience", experience);



			//仲間になるか
			int r = new Random().nextInt(100);
			if(r >= monsterFp){
				friend = monsterName + "が仲間になりたそうにこっちを見ている。";
				request.setAttribute("friend", friend);
			}



//			レベルアップの処理
			List<Msg> msgList = new ArrayList<Msg>();
			for(;heroExperience > exp ; exp += 200) {
				lv++;
//				ＨＰは1レベルごとに１５アップ
				levelMsg = heroName +"はレベル" + lv + "になった！!";
				hpMsg = "HPが全回復した！  HPが 15 上がった！";
				maxHp += 15;
				heroHp = maxHp;

//				ＭＰは1レベルごとに１アップ
				mpMsg = "MPが全回復した！  MPが 1 上がった！";
				maxMp += 1;
				heroMp = maxMp;

				hero.get(0).setMaxHp(maxHp);
				hero.get(0).setMaxMp(maxMp);


//				レベルごとに攻撃力、守備力、必殺技をアップ
				int powerR = 3;
				int defenseR = 3;
				int special_powerR = 1;
				powerMsg = "攻撃力が" + powerR + "上がった！";
				heroPower += powerR;
				defenseMsg = "防御力が" + defenseR + "上がった！";
				heroDefense += defenseR;
				specialMsg = "必殺技が" + special_powerR + "上がった！";
				heroSpecialPower += special_powerR;

				//パワーアップしたステータスのセット
				hero.get(0).setPower(heroPower);
				hero.get(0).setDefense(heroDefense);
				hero.get(0).setSpecial_power(heroSpecialPower);

				//レベルアップメッセージのビーンズおよびリストへの追加
				Msg msg = new Msg(levelMsg,hpMsg,mpMsg,powerMsg,defenseMsg,specialMsg);
				msgList.add(msg);
				request.setAttribute("msgList", msgList);
			}

			hero.get(0).setExp(exp);
			monsterTurn = false;
		}



//		敵の攻撃
		if(monsterTurn) {
			//モンスターの攻撃確率80%
			int r = new Random().nextInt(100);
			if(r < 70) {
//				仲間がいるかの確認
				if(hero.size() != 1) {

					int r2 = new Random().nextInt(1);
//					どちらを攻撃するかるか
					if(r2 == 1) {
						//ヒーローへの攻撃
						takeDamage = monsterPower - heroDefense;
						if(takeDamage <= 0) {
							takeDamage = 0;
						}
						heroHp -= takeDamage;

						monsterMsg = monsterName + "の攻撃！！" + heroName +"が" + takeDamage + "をくらった";
						request.setAttribute("monsterMsg", monsterMsg);

					}else {
						//仲間への攻撃
						takeDamage = monsterPower - friendDefense;
						if(takeDamage <= 0) {
							takeDamage = 0;
						}
						friendHp -= takeDamage;

						monsterMsg = monsterName + "の攻撃！！" + friendName +"が" + takeDamage + "をくらった";
						request.setAttribute("monsterMsg", monsterMsg);

					}
				//仲間がいない場合ヒーローのみ攻撃
				}else {
					takeDamage = monsterPower - heroDefense;
					if(takeDamage <= 0) {
						takeDamage = 0;
					}
					heroHp -= takeDamage;

					monsterMsg = monsterName + "の攻撃！！" + takeDamage + "をくらった";
					request.setAttribute("monsterMsg", monsterMsg);

				}

//			必殺技の確率１０％
			}else if(r < 80) {
//				仲間がいるかの確認
				if(hero.size() != 1) {

					int r2 = new Random().nextInt(1);
//					どち攻撃するかるか
					if(r2 == 1) {
						//ヒーローへの攻撃
						takeDamage = (int)(monsterPower * 1.5) - heroDefense;						if(takeDamage <= 0) {
							takeDamage = 0;
						}
						heroHp -= takeDamage;

						monsterMsg = monsterName + "の必殺技！！" + heroName +"が" + takeDamage + "をくらった";
						request.setAttribute("monsterMsg", monsterMsg);

					}else {
						//仲間への攻撃
						takeDamage = (int)(monsterPower * 1.5) - friendDefense;						if(takeDamage <= 0) {
							takeDamage = 0;
						}
						friendHp -= takeDamage;

						monsterMsg = monsterName + "の必殺技！！" + friendName +"が" + takeDamage + "をくらった";
						request.setAttribute("monsterMsg", monsterMsg);

					}

				//仲間がいない場合ヒーローのみ攻撃
				}else {
					takeDamage = (int)(monsterPower * 1.5) - heroDefense;					if(takeDamage <= 0) {
						takeDamage = 0;
					}
					heroHp -= takeDamage;

					monsterMsg = monsterName + "の必殺技！！" + takeDamage + "をくらった";
					request.setAttribute("monsterMsg", monsterMsg);

				}
//			何もしない確率２０％
			}else {
				monsterMsg = monsterName + "は何もしなかった。";
				request.setAttribute("monsterMsg", monsterMsg);

			}
		}

		//仲間の死亡メッセージ
		if((hero.size() != 1) && friendHp <= 0) {
			friendDie = friendName + "は死んだ。";
			request.setAttribute("friendDie", friendDie);

			//仲間のリストの削除
			hero.remove(1);
			//仲間を削除したリストを再登録
			session.setAttribute("hero", hero);
		}


//		生存判定
		if(heroHp <= 0) {
			dieMsg = heroName + "は死んだ";
			request.setAttribute("dieMsg", dieMsg);
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/gameOver.jsp");
//			dispatcher.forward(request, response);
		}



//		ヒーローの情報を変更
		hero.get(0).setLevel(lv);
		hero.get(0).setHp(heroHp);
		hero.get(0).setMp(heroMp);
		hero.get(0).setGold(heroGold);
		hero.get(0).setExp(exp);
		hero.get(0).setExperience(heroExperience);

		//仲間の情報を更新
		if(hero.size() != 1) {
			hero.get(1).setHp(friendHp);
		}

//		モンスターの情報を変更
		m.get(0).setHp(monsterHp);

		//ヒーローの情報を毎回セッションに登録する
		session.setAttribute("hero", hero);

		//戦闘シーンへ
		if(dispatcher == null) {
			dispatcher = request.getRequestDispatcher("/battle.jsp");
		}
		dispatcher.forward(request, response);


	}

}
