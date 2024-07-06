package cn.Mchaptim.Rush;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import cn.Mchaptim.Core.PluginUtils.Color;

public class ScoreBoard extends BukkitRunnable {
	Player p;
	ScoreboardManager manage = Bukkit.getScoreboardManager();
	Scoreboard sb = manage.getNewScoreboard();
	Objective ob = sb.registerNewObjective("RushLobby计分板", "dummy");
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	List<Team> tl = new ArrayList<>();

	public ScoreBoard(Player p) {
		this.p = p;
//		ob.setDisplayName(Color.To("&bMchaptim &3Rush")); Mchaptim
		ob.setDisplayName(Color.To(Config.Sb_Title));
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		p.setScoreboard(sb);

	}

	@Override
	public void run() {
		PlayerData pd = Main.PlayerCaches.get(p);
		List<String> line = new ArrayList<>();
		Iterator<String> tmp = Config.Sb_Lines.iterator();
		while (tmp.hasNext()) {
			String tmp2 = tmp.next();
			if (tmp2.indexOf("<Level>") != -1) {
				tmp2.replace("<Level>", "" + pd.Levels);
			}
			if (tmp2.indexOf("<BreakBed>") != -1) {
				tmp2.replace("<BreakBed>", "" + pd.BreakBed);
			}
			if (tmp2.indexOf("<Wins>") != -1) {
				tmp2.replace("<Wins>", "" + pd.Wins);
			}
			line.add(tmp2);
		}
//		line.add(" ");
//		line.add(Color.To("&8- &7等级 &b▸ &f" + pd.Levels));
//		line.add(Color.To("&8- &7进度 &b▸ &f编写中"));
//		line.add(" ");
//		line.add(Color.To("&8- &7准度 &b▸ &f编写中"));
//		line.add(" ");
//		line.add(Color.To("&8- &7撸床 &b▸ &f" + pd.BreakBed));
//		line.add(Color.To("&8- &7胜场 &b▸ &f" + pd.Wins));
//		line.add(" ");
//		line.add(Color.To("&7#" + sdf.format(date))); Mchaptim
//		line.add(Color.To("&bMchaptim.cn")); Mchaptim
//		line.add(Color.To("&b► play.starmc.vip")); 
		if (tl.size() > line.size()) {
			for (int i = tl.size() - 1; i > line.size() - 1; i--) {
				Team t = tl.get(i);
				StringBuilder strb = new StringBuilder(Color.To("&" + i));
				if (strb.length() > 2) {
					strb.insert(2, "§");
					strb.setCharAt(1, '9');
				}
				sb.resetScores(strb.toString());
				t.removeEntry(strb.toString());
				t.unregister();
				tl.remove(t);
			}
		}
		if (line.size() > tl.size()) {
			for (int i = tl.size(); i < line.size(); i++) {
				StringBuilder strb = new StringBuilder(Color.To("&" + i));
				if (strb.length() > 2) {
					strb.insert(2, "§");
					strb.setCharAt(1, '9');
				}
				Team t = sb.registerNewTeam(strb.toString());
				t.addEntry(strb.toString());
				ob.getScore(strb.toString()).setScore(0);
				tl.add(t);
				String tmp2 = line.get(i);
				if (tmp2.length() > 16) {
					t.setPrefix(tmp2.substring(0, 16));
					int last = tmp2.lastIndexOf("§", 17);
					t.setSuffix(tmp2.substring(last, last + 2) + tmp2.substring(16, tmp2.length()));
					continue;
				}
				t.setSuffix(tmp2);
			}
		}
		if (line.size() == tl.size()) {
			for (int i = 0; i < line.size(); i++) {
				String str = line.get(i);
				Team t = tl.get(i);
				if (str.length() > 16) {
					t.setPrefix(str.substring(0, 16));
					int last = str.lastIndexOf("§", 17);
					t.setSuffix(str.substring(last, last + 2) + str.substring(16, str.length()));
					continue;
				}
				if (t.getPrefix() != null)
					t.setPrefix("");
				t.setSuffix(str);
			}
		}

	}

}
