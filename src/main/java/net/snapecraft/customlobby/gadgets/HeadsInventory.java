package net.snapecraft.customlobby.gadgets;

import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class HeadsInventory {
    public static Inventory getInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "§aDeine Köpfe");

        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        List<String> heads = CustomLobby.getInstance().getConfig().getStringList("headlist");
        if(heads.size() > 35) {
            for (int i = 0; i < heads.size(); i++) {
                if(i > 35) {
                    heads.remove(i);
                }
            }
        }
        for (int i = 0; i < heads.size(); i++) {
            inv.addItem(ItemAPI.createSkull(heads.get(i), "§a" + heads.get(i), 1));
        }
        // Reset Heads Button
        List<String> lore = new ArrayList<>();
        lore.add("§cDies entfernt den aktuellen Kopf!");
        inv.setItem(35, ItemAPI.createItemWithLore(Material.BARRIER, "§cKopf entfernen", (byte)0, 1, lore));
        return inv;
    }
}
