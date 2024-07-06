package cn.Mchaptim.Rush;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main m;
	public static Map<Player, PlayerData> PlayerCaches = new HashMap<>();

	@Override
	public void onEnable() {
		m = this;
		saveDefaultConfig();
		Config.LoadConfig();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		this.getCommand("rushlobby").setExecutor(new Commands());
	}

	@Override
	public void onDisable() {
		Iterator<? extends Player> it = Bukkit.getOnlinePlayers().iterator();
		while (it.hasNext()) {
			Player p = it.next();
			PlayerCaches.get(p).UpdateMySQLPlayerData();
		}
		if (Utils.con != null) {
			try {
				Utils.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
