package cn.Mchaptim.Rush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import cn.Mchaptim.Core.PluginUtils.Color;

public class InfoMenu {
	Player p;
	Inventory im = Bukkit.createInventory(null, 36, Color.To("&1&2&3&4&5&2&f按键盘上的 &8[ESC] &f关闭本菜单"));

	public InfoMenu(Player p) {
		this.p = p;
	}

	public void OpenMenu() {
		List<ItemStack> items = GetInfoMenuItemStack();
		p.playSound(p.getLocation(), Sound.NOTE_PLING, 20F, 0F);
//		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_FLUTE, 20F, 0F); 1.12.2 Paper

		im.setItem(4, items.get(0));
		im.setItem(20, items.get(1));
		im.setItem(22, items.get(2));
		im.setItem(24, items.get(3));

		p.openInventory(im);

	}

	public ItemStack GetInventoryItemStack() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&7个人Rush设置"));
		item.setItemMeta(meta);
		return item;
	}

	public List<ItemStack> GetInfoMenuItemStack() {
		PlayerData pd = Main.PlayerCaches.get(p);
		List<ItemStack> items = new ArrayList<>();

		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(Color.To("&f你的&bRush&f统计信息"));
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Color.To("&7等级: &f" + pd.Levels));
		lore.add(Color.To("&7撸床: &f" + pd.BreakBed));
		lore.add(Color.To("&7胜利: &f" + pd.Wins));
		lore.add(" ");
		meta.setLore(lore);
		meta.setOwner(p.getName());
//		meta.setOwningPlayer(p); 1.12.2 Paper
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.BOOK);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(Color.To("&f设置物品位置"));
		item.setItemMeta(itemmeta);
		items.add(item);

		item = new ItemStack(Material.SANDSTONE, 1, (short) 2);
		itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(Color.To("&f方块皮肤 &c|编写中|"));
		item.setItemMeta(itemmeta);
		items.add(item);

		item = new ItemStack(Material.BLAZE_ROD);
		itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(Color.To("&f击退棒皮肤 &c|编写中|"));
		item.setItemMeta(itemmeta);
		items.add(item);

		return items;
	}
}
