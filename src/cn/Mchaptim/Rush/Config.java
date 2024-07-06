package cn.Mchaptim.Rush;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Config {
	static String url;
	static int port;
	static String database;
	static String username;
	static String password;
	public static String Sb_Title;
	public static List<String> Sb_Lines;

	public static void LoadConfig() {
		Main.m.reloadConfig();
		FileConfiguration conf = Main.m.getConfig();
		url = conf.getString("MySQL.url");
		port = conf.getInt("MySQL.port");
		database = conf.getString("MySQL.database");
		username = conf.getString("MySQL.username");
		password = conf.getString("MySQL.password");
		Sb_Title = conf.getString("ScoreBoard.Title");
		Sb_Lines = conf.getStringList("ScoreBoard.Lines");
		Utils.MySQLUtils(url, port, database, username, password);
		Utils.CreateTables();
		new BukkitRunnable() {
			@Override
			public void run() {
				Utils.MySQLUtils(url, port, database, username, password);
			}
		}.runTaskTimer(Main.m, 72000L, 72000L);
	}
}
