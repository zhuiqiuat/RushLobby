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
			sender.sendMessage(Color.To("&a�����������ļ�"));
			return true;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(Color.To("&c�㲻��һ�����"));
			return true;
		}
		if (sender.hasPermission("rl.admin")) {
			sender.sendMessage(Color.To("&7RushLobby &f�������"));
			sender.sendMessage(Color.To("&7/RushLobby reload &f��������"));
		}
		return true;
	}

}
