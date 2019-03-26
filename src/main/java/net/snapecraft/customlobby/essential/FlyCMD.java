package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        Player p = (Player)commandSender;
        if (p.hasPermission("CustomLobby.Fly"))
        {
            if (!p.getAllowFlight())
            {
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(CustomLobby.prefix + "§aDu kannst nun fliegen.");
            }
            else
            {
                p.setFlying(false);
                p.setAllowFlight(false);
                p.sendMessage(CustomLobby.prefix + "§aDu kannst nun §6nicht mehr fliegen.");
            }
        }
        else {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
