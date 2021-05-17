package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MonsterRecord;

public class MonsterDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/janjon";
	private final String USER = "postgres";
	private final String PASSWORD = "test";
	  /**
     * 「boss」テーブルからbossIDでデータを検索する。
     * （PreparedStatement方式）
     */
    public List<MonsterRecord> monsterSelect(int id) {

        /* PostgreSQLへの接続情報 */
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<MonsterRecord> mRecords = new ArrayList<>();

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * FROM monster WHERE no =?";

	         st = con.prepareStatement(sql);
	         st.setInt(1, id);

	         //SELECT文の実行
	         rs = st.executeQuery();

	         mRecords = makeResultData(rs);



		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		} return mRecords;
    }
    /**
     * 検索結果をリストで返します。
     */
    public List<MonsterRecord> makeResultData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	List<MonsterRecord> mRecords = new ArrayList<MonsterRecord>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		int no = rs.getInt("no");
    		String name = rs.getString("name");
    		int maxHp = rs.getInt("max_hp");
    		int hp = rs.getInt("hp");
    		int mp = rs.getInt("mp");
    		int power = rs.getInt("power");
    		int defense = rs.getInt("defense");
    		int fp = rs.getInt("fp");
    		int gold = rs.getInt("gold");
    		int experience = rs.getInt("experience");



    		// 1行分のデータを格納するインスタンス
    		MonsterRecord mRecord = new MonsterRecord(no,
    											name,maxHp,hp,mp,power,
    											defense,fp,gold,experience
    											);

            // リストに1行分のデータを追加する
    		mRecords.add(mRecord);
    	}
    	return mRecords;
    }
}

