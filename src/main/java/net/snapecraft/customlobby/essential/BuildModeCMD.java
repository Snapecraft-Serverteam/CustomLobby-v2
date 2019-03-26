package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildModeCMD implements CommandExecutor {
    public static List<String> buildmodeplayers = new ArrayList();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player p = (Player)sender;
        if (p.hasPermission("CustomLobby.BuildModeCMD"))
        {
            if (!buildmodeplayers.contains(p.getName()))
            {
                buildmodeplayers.add(p.getName());
                p.sendMessage(CustomLobby.prefix + "§aDu bist nun im Baumodus!");
                p.setGameMode(GameMode.CREATIVE);
            }
            else
            {
                buildmodeplayers.remove(p.getName());
                p.sendMessage(CustomLobby.prefix + "§aDu bist §cnicht §amehr im Baumodus!");
                p.setGameMode(GameMode.SURVIVAL);
            }
        }
        else {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
