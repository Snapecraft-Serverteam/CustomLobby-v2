package net.snapecraft.customlobby.navigator;

import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.omg.CORBA.CustomMarshal;

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
//        inv.setItem(10, ItemAPI.createItem(Material.GRASS, "§aSky§7Block", (byte)0, 1));
//        inv.setItem(12, ItemAPI.createItem(Material.BRICK, "§4City§bBuild", (byte)0, 1));
//        inv.setItem(14, ItemAPI.createItem(Material.CHEST, "§3Sky§7Wars", (byte)0, 1));
//        inv.setItem(16, ItemAPI.createItem(Material.IRON_SWORD, "§cPVP", (byte)0, 1));
//        inv.setItem(20, ItemAPI.createItem(Material.FIREBALL, "§eNuke", (byte)0, 1));
//        inv.setItem(24, ItemAPI.createSkull(p.getName(), "§4L§co§6b§eb§2y§as§bp§3i§1e§9l§de", 1));

        for(String warp : CustomLobby.getInstance().getConfig().getConfigurationSection("warp").getKeys(true)) {
            ConfigurationSection cs = CustomLobby.getInstance().getConfig().getConfigurationSection(warp);
            System.out.println(cs.getInt("slot"));
            System.out.println(cs.getString("item"));
            inv.setItem(cs.getInt("slot"), ItemAPI.createItem(Material.getMaterial(cs.getString("item")), warp, (byte) 0, 1));
        }
        p.openInventory(inv);
    }
}
