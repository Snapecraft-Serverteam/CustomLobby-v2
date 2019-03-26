package net.snapecraft.customlobby.gamemode;

import net.snapecraft.customlobby.utils.API;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("c")) {
            if (sender.hasPermission("CustomLobby.Gamemode.c"))
            {
                ((Player)sender).setGameMode(GameMode.CREATIVE);
                sender.sendMessage(API.getPrefix() + "§a Du bist nun im §6Kreativmodus§a!");
            }
            else
            {
                sender.sendMessage(API.getNoPermString());
            }
        }
        if (cmd.getName().equalsIgnoreCase("s")) {
            if (sender.hasPermission("CustomLobby.Gamemode.s"))
            {
                ((Player)sender).setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(API.getPrefix() + "§a Du bist nun im §6Überlebensmodus§a!");
            }
            else
            {
                sender.sendMessage(API.getNoPermString());
            }
        }
        if (cmd.getName().equalsIgnoreCase("sp")) {
            if (sender.hasPermission("CustomLobby.Gamemode.sp"))
            {
                ((Player)sender).setGameMode(GameMode.SPECTATOR);
                sender.sendMessage(API.getPrefix() + "§a Du bist nun im §6Spectatormodus§a!");
            }
            else
            {
                sender.sendMessage(API.getNoPermString());
            }
        }
        return true;
    }
}
