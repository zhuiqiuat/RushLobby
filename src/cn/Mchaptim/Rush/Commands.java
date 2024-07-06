package cn.Mchaptim.Rush;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.Mchaptim.Core.PluginUtils.Color;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("rl.admin")) {
			Config.LoadConfig();
			sender.sendMessage(Color.To("&a已重载配置文件"));
			return true;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(Color.To("&c你不是一个玩家"));
			return true;
		}
		if (sender.hasPermission("rl.admin")) {
			sender.sendMessage(Color.To("&7RushLobby &f命令帮助"));
			sender.sendMessage(Color.To("&7/RushLobby reload &f重载配置"));
		}
		return true;
	}

}
