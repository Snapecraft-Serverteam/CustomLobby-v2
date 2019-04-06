package net.snapecraft.customlobby.settings;

import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsMainGUI {
    public static Inventory createMainGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§aDeine Einstellungen");
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, " ", (byte)3, 1));
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, " ", (byte)3, 1));
        }

        boolean soundState = Settings.hasSoundsEnabled(p);

        if(soundState) {
            inv.setItem(9, ItemAPI.createItem(Material.getMaterial(351), "§aSounds Aktiviert", (byte)10, 1));
        } else {
            inv.setItem(9, ItemAPI.createItem(Material.getMaterial(351), "§7Sounds Deaktiviert", (byte)8, 1));
        }

        return inv;
    }
}
