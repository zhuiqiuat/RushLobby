package cn.Mchaptim.Rush;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cn.Mchaptim.Core.PluginUtils.Color;

public class PlayerListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	void OnClick(InventoryClickEvent e) {
		Player p = Bukkit.getPlayer(e.getWhoClicked().getName());
		PlayerData pd = Main.PlayerCaches.get(p);

		if (e.getInventory().getTitle().equalsIgnoreCase(Color.To("&1&2&3&4&5&1&f按键盘上的 &8[ESC] &f关闭本菜单"))) {
			if (!(e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT) || e.getRawSlot() < 0) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() > 8) {
				e.setCancelled(true);
			}
			if (e.isCancelled()) {
				e.setCancelled(false);
			}
			List<ItemStack> items = new EditMenu(p).GetEditMenuItemStack();
			if (e.getCurrentItem().equals(items.get(4))) {
				int stickslot = e.getInventory().first(items.get(1));
				int pickaxeslot = e.getInventory().first(items.get(2));
				int blockslot = e.getInventory().first(items.get(3));
				if (stickslot < 0 || pickaxeslot < 0 || blockslot < 0) {
					p.sendMessage(Color.To("&c物品摆放错误"));
					Inventory inv = p.getInventory();
					int pis = inv.first(items.get(1));
					int pip = inv.first(items.get(2));
					int pib = inv.first(items.get(3));
					if (pis > -1)
						inv.clear(pis);
					if (pip > -1)
						inv.clear(pip);
					if (pib > -1)
						inv.clear(pib);
					p.closeInventory();
					return;
				}
				pd.StickSlot = stickslot;
				pd.PickaxeSlot = pickaxeslot;
				pd.BlockSlot = blockslot;
				p.closeInventory();
//				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20F, 0F); 1.12.2 Paper
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 20F, 0F);
				e.getWhoClicked().sendMessage(Color.To("&a已保存"));
				return;
			}
			if (e.getCurrentItem().equals(items.get(5))) {

				pd.StickSlot = 0;
				pd.PickaxeSlot = 1;
				pd.BlockSlot = 2;

				p.closeInventory();
//				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 20F, 0F); 1.12.2
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 20F, 0F);
				e.getWhoClicked().sendMessage(Color.To("&c已重置"));
				return;
			}
		}

		if (e.getInventory().getTitle().equalsIgnoreCase(Color.To("&1&2&3&4&5&2&f按键盘上的 &8[ESC] &f关闭本菜单"))) {
			e.setCancelled(true);
			if (e.getCurrentItem().equals(new InfoMenu(p).GetInfoMenuItemStack().get(1))) {
//				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 20F, 0F); 1.12.2 Paper
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 20F, 0F);
				EditMenu em = new EditMenu(p);
				em.OpenMenu();
				return;
			}
			return;
		}
	}

	@EventHandler
	void OnJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerData pd = new PlayerData(p);
		p.getInventory().setItem(1, new InfoMenu(p).GetInventoryItemStack());
		ScoreBoard sb = new ScoreBoard(p);
		sb.runTaskTimer(Main.m, 0L, 2L);
		pd.Scoreboard = sb;
		Main.PlayerCaches.put(p, pd);
	}

	@EventHandler
	void OnQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		ScoreBoard sb = Main.PlayerCaches.get(p).Scoreboard;
		sb.cancel();
		PlayerData pd = Main.PlayerCaches.get(p);
		pd.UpdateMySQLPlayerData();
		Main.PlayerCaches.remove(p);

	}

	@EventHandler
	void OnInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				|| e.getItem() == null) {
			return;
		}
		if (e.getItem().equals(new InfoMenu(e.getPlayer()).GetInventoryItemStack())) {
			new InfoMenu(e.getPlayer()).OpenMenu();
		}
	}

	@EventHandler
	void OnClose(InventoryCloseEvent e) {
		if (e.getInventory().getTitle().equals(Color.To("&1&2&3&4&5&1&f按键盘上的 &8[ESC] &f关闭本菜单"))) {
			Iterator<ItemStack> tmp = new EditMenu(null).GetEditMenuItemStack().iterator();
			while(tmp.hasNext()) {
				ItemStack tmp2 = tmp.next();
				if(e.getInventory().first(tmp2) >0) {
					e.getInventory().clear(e.getInventory().first(tmp2));
				}
			}
		}
	}

}
