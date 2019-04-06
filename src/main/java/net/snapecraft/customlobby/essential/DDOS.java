package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DDOS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("CustomLobby.DDOS")) {
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    target.kickPlayer("Timed out!");
                    sender.sendMessage(API.getPrefix() + " §aDer Spieler wird jetzt geddost!");
                } else {
                    sender.sendMessage(API.getPrefix() + " §cSpieler nicht gefunden!");
                }
            } else {
                sender.sendMessage(API.getPrefix() + " §cWrong Usage (Du kannst dich doch net selber DDOSSEN!): /ddos <Spieler>");
            }
        } else {
            sender.sendMessage(API.getNoPermString());
        }

        return true;
    }
}
