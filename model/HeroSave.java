package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HeroSave {
//	private static final String FILE_PATH = "c:\\config\\game.properties";

	String name = "";
	int hp = 0;
	int mp = 0;
	int power = 0;
	int defense = 0;
	int special_power = 0;



	public HeroSave(List<Hero> hero) {

		File file = new File("C:\\config\\gameSave.properties");
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(file));


			bw.write("hero_name="+ hero.get(0).getName());
			bw.newLine();
			bw.write("hero_level="+hero.get(0).getLevel());
			bw.newLine();
			bw.write("hero_max_hp="+hero.get(0).getMaxHp());
			bw.newLine();
			bw.write("hero_hp="+hero.get(0).getHp());
			bw.newLine();
			bw.write("hero_max_mp="+hero.get(0).getMaxMp());
			bw.newLine();
			bw.write("hero_mp="+hero.get(0).getMp());
			bw.newLine();
			bw.write("hero_power="+hero.get(0).getPower());
			bw.newLine();
			bw.write("hero_defense="+hero.get(0).getDefense());
			bw.newLine();
			bw.write("hero_special_power="+hero.get(0).getSpecial_power());
			bw.newLine();
			bw.write("hero_fp="+hero.get(0).getFp());
			bw.newLine();
			bw.write("hero_gold="+hero.get(0).getGold());
			bw.newLine();
			bw.write("hero_exp="+hero.get(0).getExp());
			bw.newLine();
			bw.write("hero_experience="+hero.get(0).getExperience());
			bw.newLine();
			bw.write("hero_weaponPower="+hero.get(0).getWeaponPower());
			bw.newLine();
			bw.write("hero_herb="+hero.get(0).getHerb());
			bw.newLine();



		} catch (Exception e) {
			System.out.println("プロパティ情報の登録に失敗しました");
			e.printStackTrace();
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {}
			}
		}
	}

}
