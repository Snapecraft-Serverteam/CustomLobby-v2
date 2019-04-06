package net.snapecraft.customlobby.hide;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Hide {
    public static HashMap<Player, Boolean> ishidden = new HashMap<>();

    public static void hideall(Player p)
    {
        p.sendMessage(CustomLobby.prefix + "§4Alle Spieler sind nun unsichtbar!");
        for (Player f : Bukkit.getOnlinePlayers())
        {
            p.hidePlayer(f);
            if(ishidden.containsKey(p)) {
                ishidden.remove(p);
                ishidden.put(p, true);
            } else {
                ishidden.put(p, true);
            }
        }
    }

    public static void showall(Player p)
    {
        p.sendMessage(CustomLobby.prefix + "§3Alle Spieler sind nun sichtbar!");
        for (Player f : Bukkit.getOnlinePlayers())
        {
            p.showPlayer(f);
            if(ishidden.containsKey(p)) {
                ishidden.remove(p);
                ishidden.put(p, false);
            } else {
                ishidden.put(p, false);
            }
        }
    }
}
