package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAllCMD implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        Player p = (Player)commandSender;
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 1.0F);
        if (p.hasPermission("CustomLobby.tpall")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all != p)
                {
                    all.teleport(p);
                    all.sendMessage(CustomLobby.prefix + "§aAlle Spieler wurden zu §6" + p.getName() + " teleportiert!");
                    p.sendMessage(CustomLobby.prefix + "§aAlle Spieler wurden zu §6dir §ateleportiert!");
                }
            }
        } else {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
