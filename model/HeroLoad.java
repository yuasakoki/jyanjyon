package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HeroLoad {
//	private static final String FILE_PATH = "C:\\config\\game.properties";

	String name = "";
	int level = 0;
	int hp = 0;
	int maxHp = 0;
	int mp = 0;
	int maxMp = 0;
	int power = 0;
	int defense = 0;
	int special_power = 0;
	int fp = 0;
	int gold = 0;
	int exp = 0;
	int experience = 0;
	int weaponPower=0;
	int herb=0;
	int superherb=0;
	int ultrasuperherb=0;


	public List<Hero> heroLoad(String file_path) {

		List<Hero> hero = new ArrayList<Hero>();
		FileReader fr = null;

		try {
			fr = new FileReader(file_path);
			Properties p = new Properties();

			p.load(fr);
			name = p.getProperty("hero_name");
			level = Integer.parseInt(p.getProperty("hero_level"));
			hp = Integer.parseInt(p.getProperty("hero_hp"));
			maxHp = Integer.parseInt(p.getProperty("hero_max_hp"));
			mp = Integer.parseInt(p.getProperty("hero_mp"));
			maxMp = Integer.parseInt(p.getProperty("hero_max_mp"));
			power = Integer.parseInt(p.getProperty("hero_power"));
			defense = Integer.parseInt(p.getProperty("hero_defense"));
			special_power = Integer.parseInt(p.getProperty("hero_special_power"));
			fp = Integer.parseInt(p.getProperty("hero_fp"));
			gold = Integer.parseInt(p.getProperty("hero_gold"));
			exp = Integer.parseInt(p.getProperty("hero_exp"));
			experience = Integer.parseInt(p.getProperty("hero_experience"));
			weaponPower = Integer.parseInt(p.getProperty("hero_weaponPower"));
			herb = Integer.parseInt(p.getProperty("hero_herb"));
			superherb = Integer.parseInt(p.getProperty("hero_superherb"));
			ultrasuperherb = Integer.parseInt(p.getProperty("hero_ultrasuperherb"));


		} catch (Exception e) {
			System.out.println("プロパティ情報の取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {}
			}
		}

		Hero heroRecords = new Hero(name,level,hp,maxHp,mp,maxMp,power,defense,
				special_power,fp,gold,exp,experience,weaponPower,herb,superherb,ultrasuperherb);
		hero.add(heroRecords);

		return hero;
	}


}
