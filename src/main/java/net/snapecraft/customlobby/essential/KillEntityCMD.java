package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class KillEntityCMD implements CommandExecutor {
    public static boolean allowKills = false;

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player p = (Player)sender;
        if (p.hasPermission("CustomLobby.killAllEntities"))
        {
            for (int i = 0; i < p.getWorld().getEntities().size(); i++)
            {
                Entity e = p.getWorld().getEntities().get(i);
                if (e.getType() != EntityType.PLAYER)
                {
                    allowKills = true;
                    e.remove();
                    allowKills = false;
                }
            }
            p.sendMessage(CustomLobby.prefix + "§aAlle Entitys wurden beseitigt.");
        }
        else
        {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
