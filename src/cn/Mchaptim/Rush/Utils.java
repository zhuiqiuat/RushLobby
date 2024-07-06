package cn.Mchaptim.Rush;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
	public static Connection con = null;

	public static void MySQLUtils(String url, int port, String database, String username, String password) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + database + "?useSSL=false",
					username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void CreateTables() {
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute(
					"create table if not exists `RushPlayerData`(`UUID` text,`BreakBed` int,`Wins` int,`Xps` int,"
							+ "`Levels` int," + "`StickSlot` int," + "`PickaxeSlot` int," + "`BlockSlot` int,"
							+ "`BlockSkin` int," + "`StickSkin` int" + ");");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
