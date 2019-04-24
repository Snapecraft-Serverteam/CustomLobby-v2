package net.snapecraft.customlobby.gadgets;

import net.snapecraft.customlobby.utils.API;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GadgetsInventory {
    @SuppressWarnings("Duplicates")
    public static Inventory getInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§aDeine Gadgets");

        // Obere und Untere Reihe Glas Panes
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, " ", (byte)3, 1));
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, " ", (byte)3, 1));
        }

        // Head Item
        inv.setItem(9, ItemAPI.createSkull(API.randomSkull(), "§2Köpfe", 1));

        // Schuhe Item
        inv.setItem(10, ItemAPI.createItem(API.randomBoot(), "§2Schuhe", (byte)0, 1));


        return inv;
    }
}
