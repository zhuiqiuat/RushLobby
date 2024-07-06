package cn.Mchaptim.Rush;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cn.Mchaptim.Core.PluginUtils.Color;

public class EditMenu {
	Player p;
	Inventory inv = Bukkit.createInventory(null, 18, Color.To("&1&2&3&4&5&1&f按键盘上的 &8[ESC] &f关闭本菜单"));

	public EditMenu(Player p) {
		this.p = p;
	}

	public void OpenMenu() {
		PlayerData pd = Main.PlayerCaches.get(p);
		ItemStack glass = GetEditMenuItemStack().get(0);

		inv.setItem(9, glass);
		inv.setItem(10, glass);
		inv.setItem(11, glass);
		inv.setItem(13, glass);
		inv.setItem(15, glass);
		inv.setItem(16, glass);
		inv.setItem(17, glass);
		inv.setItem(pd.StickSlot, GetEditMenuItemStack().get(1));
		inv.setItem(pd.PickaxeSlot, GetEditMenuItemStack().get(2));
		inv.setItem(pd.BlockSlot, GetEditMenuItemStack().get(3));
		inv.setItem(12, GetEditMenuItemStack().get(4));
		inv.setItem(14, GetEditMenuItemStack().get(5));

		p.openInventory(inv);
	}

	public List<ItemStack> GetEditMenuItemStack() {
		List<ItemStack> items = new ArrayList<>();

		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.STICK);
		meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&f击退棒"));
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.DIAMOND_PICKAXE);
		meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&f稿子"));
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.SANDSTONE, 1, (short) 2);
		meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&f方块"));
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.CHEST);
		meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&a保存"));
		item.setItemMeta(meta);
		items.add(item);

		item = new ItemStack(Material.ENDER_CHEST);
		meta = item.getItemMeta();
		meta.setDisplayName(Color.To("&c重置"));
		item.setItemMeta(meta);
		items.add(item);

		return items;
	}
}
