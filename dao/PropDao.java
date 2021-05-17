package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PropRecord;

public class PropDao {
	/**
	 * 「武器」テーブルへのアクセスを担当する
	 * DAOクラスです。
	 */
	private final String URL = "jdbc:postgresql://localhost:5432/janjon";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	/**
	 * 「武器」テーブルから全てのデータを検索し
	 * 検索結果を返します。
	 */
	    public List<PropRecord> selectAll() {
	    	List<PropRecord> pRecords = null;

	    	Connection con = null;
	    	PreparedStatement st = null;
	    	ResultSet rs = null;



			try {
		         /* JDBCドライバの定義 */
		         Class.forName("org.postgresql.Driver");

		         /* PostgreSQLへの接続 */
		         con = DriverManager.getConnection(URL, USER, PASSWORD);

		         /* SELECT文の準備 */
		         String sql = "SELECT * FROM prop; ";
		         st = con.prepareStatement(sql);

		         /* SELECT文の実行 */
		         rs = st.executeQuery();

		         /* 結果をリストに移し替える */
		         pRecords = makeResultData(rs);

			} catch(Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
		         /* PostgreSQLとの接続を切断 */
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {}
				}

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
			}
	        return pRecords;
	    }


	    public List<PropRecord> selectId(String id){
	    	List<PropRecord> pRecords = null;

	    	Connection con = null;
	    	PreparedStatement st = null;
	    	ResultSet rs = null;



			try {
		         /* JDBCドライバの定義 */
		         Class.forName("org.postgresql.Driver");

		         /* PostgreSQLへの接続 */
		         con = DriverManager.getConnection(URL, USER, PASSWORD);

		         /* SELECT文の準備 */
		         String sql = "SELECT * FROM prop ";
		         sql +="Where prop_id = ?;";

		         st = con.prepareStatement(sql);
		         st.setInt(1, Integer.parseInt(id));
		         /* SELECT文の実行 */
		         rs = st.executeQuery();

		         /* 結果をリストに移し替える */
		         pRecords = makeResultData(rs);

			} catch(Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
		         /* PostgreSQLとの接続を切断 */
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {}
				}

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
			}
	        return pRecords;
	    }




	  /**
     * 検索結果をリストで返します。
     */
    public List<PropRecord> makeResultData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	List<PropRecord> pRecords = new ArrayList<PropRecord>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String propId = rs.getString("Prop_id");
    		String propMei = rs.getString("name");
    		int hanbai = rs.getInt("hanbai");
    		int recovery = rs.getInt("recovery");


    		// 1行分のデータを格納するインスタンス
    		PropRecord pRecord = new PropRecord(propId,
    											 propMei,
    											 hanbai,
    											 recovery);

            // リストに1行分のデータを追加する
    		pRecords.add(pRecord);
    	}
    	return pRecords;
    }
}
//
//
//
//
//
//
//
