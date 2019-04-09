package net.snapecraft.customlobby.gadgets;

import net.snapecraft.customlobby.utils.API;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BootsInventory {
    public static Inventory getInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§aDeine Schuhe");

        List<Material> boots = API.getBoots();
        for (int i = 0; i < boots.size(); i++) {
            inv.setItem(i + 9, new ItemStack(boots.get(i)));
        }

        // Reset Boots Button
        List<String> lore = new ArrayList<>();
        lore.add("§cDies entfernt die aktuellen Schuhe!");
        inv.setItem(26, ItemAPI.createItemWithLore(Material.BARRIER, "§cKopf entfernen", (byte) 0, 1, lore));
        return inv;
    }
}