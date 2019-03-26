package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StartItems {
    public static void setStarterItems(Player p)
    {
        ItemStack navigator = ItemAPI.createItem(Material.COMPASS, "§l§bNavigator", (byte)0, 1);
        ItemStack playerhider = ItemAPI.createItem(Material.BLAZE_ROD, "§6Spieler verstecken", (byte)0, 1);
        ItemStack noGadget = ItemAPI.createItem(Material.BARRIER, "§cKein Gadget ausgewählt", (byte)0, 1);
        ItemStack gadgets = ItemAPI.createItem(Material.CHEST, "§5Gadgets", (byte)0, 1);
        ItemStack profile = ItemAPI.createSkull(p.getName(), "§9Profil, Freunde und Parties", 1);

        p.getInventory().clear();
        p.getInventory().setItem(0, navigator);
        p.getInventory().setItem(2, playerhider);
        p.getInventory().setItem(4, noGadget);
        p.getInventory().setItem(6, gadgets);
        p.getInventory().setItem(8, profile);
    }

    public static void setStarterItemsHidden(Player p)
    {
        ItemStack navigator = ItemAPI.createItem(Material.COMPASS, "§l§bNavigator", (byte)0, 1);
        ItemStack playerhider = ItemAPI.createItem(Material.STICK, "§6Spieler anzeigen", (byte)0, 1);
        ItemStack noGadget = ItemAPI.createItem(Material.BARRIER, "§cKein Gadget ausgewählt", (byte)0, 1);
        ItemStack gadgets = ItemAPI.createItem(Material.CHEST, "§5Gadgets", (byte)0, 1);
        ItemStack profile = ItemAPI.createSkull(p.getName(), "§9Profil, Freunde und Parties", 1);

        p.getInventory().clear();
        p.getInventory().setItem(0, navigator);
        p.getInventory().setItem(2, playerhider);
        p.getInventory().setItem(4, noGadget);
        p.getInventory().setItem(6, gadgets);
        p.getInventory().setItem(8, profile);
    }

    public static void setBuildmodeItems(Player p) {
        ItemStack buildBlock = ItemAPI.createItem(Material.BRICK, "§aBaumodus", (byte)0, 1);
        p.getInventory().clear();
        p.getInventory().setItem(2, buildBlock);
    }
}
