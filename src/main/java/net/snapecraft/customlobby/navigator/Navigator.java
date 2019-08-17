package net.snapecraft.customlobby.navigator;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.ItemAPI;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.dytanic.cloudnet.api.CloudAPI.getInstance;

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
                if(obj.getString("action") != null && obj.getString("action").startsWith("connserver")) {
                    String server = obj.getString("action").replaceAll("connserver ", "");
                    List<String> lore = new ArrayList<>();
                    System.out.println(obj.getString("action")+  "   "+ server);

                    Collection<ServerInfo> servers = CloudAPI.getInstance().getCloudServers();
                    List<ServerInfo> match = new ArrayList<>();
                    int count = 1;
                    for (int y = 0; y < servers.size(); i++) {
                        for (ServerInfo s : servers) {
                            System.out.println(s.getServiceId().getServerId() +" == "+ server.replaceAll("\\*", Integer.toString(count)));
                            if(s.getServiceId().getServerId().equals(server.replaceAll("\\*", Integer.toString(count)))) {
                                match.add(s);
                                count++;
                            }
                        }
                    }

                    if(match.size() > 0) {
                        for(ServerInfo info : match) {
                            lore.add("&6" + info.getServiceId().getServerId() + "&7: &4(&a" + info.getOnlineCount() + " &7/ &a" + info.getMaxPlayers() + "&4)");
                        }


                        System.out.println(obj.getString("material") + (byte) obj.getInt("meta"));
                        inv.setItem(slot,
                                ItemAPI.createItemWithLore(
                                        Material.getMaterial(obj.getString(obj.getString("material"))),
                                        ChatColor.translateAlternateColorCodes('&', obj.getString("name")),
                                        (byte) obj.getInt("meta"),
                                        1,
                                        lore
                                )
                        );
                    }

                } else {
                    inv.setItem(slot, ItemAPI.createItem(Material.getMaterial(obj.getString("material")), ChatColor.translateAlternateColorCodes('&', obj.getString("name")), (byte) obj.getInt("meta"), 1));
                }
                slot++;
            }
        }
        p.openInventory(inv);
    }
}
