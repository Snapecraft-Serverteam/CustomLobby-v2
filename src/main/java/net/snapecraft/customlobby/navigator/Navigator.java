package net.snapecraft.customlobby.navigator;

import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigator {
    public static void createNavigatorGUI(Player p)
    {
        int i = -1;

        Inventory inv = Bukkit.createInventory(null, 36, "§aNavigator");
        while (i < 35)
        {
            i = i + 1;
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.LIGHT_BLUE.getData());
            ItemMeta panemeta = pane.getItemMeta();
            panemeta.setDisplayName(" ");
            pane.setItemMeta(panemeta);
            inv.setItem(i, pane);
        }

        ConfigurationSection navSection = CustomLobby.getInstance().getConfig().getConfigurationSection("navigator.row");
        int slot = 0;
        for (int j = 0; j < 4; j++) {
            ConfigurationSection row = navSection.getConfigurationSection(Integer.toString(j));
            for (int k = 0; k < 9; k++) {
                ConfigurationSection obj = row.getConfigurationSection(Integer.toString(k));
                inv.setItem(slot, ItemAPI.createItem(Material.getMaterial(obj.getString("material")), ChatColor.translateAlternateColorCodes('&', obj.getString("name")), (byte)obj.getInt("meta"), 1));
                slot++;
            }
        }
        p.openInventory(inv);
    }
}
