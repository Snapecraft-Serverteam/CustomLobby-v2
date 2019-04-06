package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(strings.length == 0) {
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
        } else if(strings.length == 1) {
            Player target = Bukkit.getPlayer(strings[0]);
            if(commandSender.hasPermission("CustomLobby.FlyOthers")) {
                if(target != null) {

                    if (!target.getAllowFlight())
                    {
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        target.sendMessage(CustomLobby.prefix + "§aDu kannst nun fliegen.");
                    }
                    else
                    {
                        target.setFlying(false);
                        target.setAllowFlight(false);
                        target.sendMessage(CustomLobby.prefix + "§aDu kannst nun §6nicht mehr fliegen.");
                    }


                } else {
                    commandSender.sendMessage(API.getPrefix() + " §cSpieler nicht gefunden!");
                }
            }
        } else {
            commandSender.sendMessage(API.getPrefix() + " §cWrong Usage: /fly oder /fly <Name>");
        }

        return true;
    }
}
