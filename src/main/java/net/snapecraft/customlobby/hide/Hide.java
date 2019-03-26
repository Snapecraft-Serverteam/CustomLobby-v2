package net.snapecraft.customlobby.hide;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Hide {
    public static boolean ishidden = false;

    public static void hideall(Player p)
    {
        p.sendMessage(CustomLobby.prefix + "§4Alle Spieler sind nun unsichtbar!");
        for (Player f : Bukkit.getOnlinePlayers())
        {
            p.hidePlayer(f);
            ishidden = true;
        }
    }

    public static void showall(Player p)
    {
        p.sendMessage(CustomLobby.prefix + "§3Alle Spieler sind nun sichtbar!");
        for (Player f : Bukkit.getOnlinePlayers())
        {
            p.showPlayer(f);
            ishidden = false;
        }
    }
}
