package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DDOS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("CustomLobby.DDOS")) {
            if(args.length == 1) {
                sender.sendMessage(API.getPrefix() + "§aDer Spieler wird jetzt geddost!");
            } else {
                sender.sendMessage("§cWrong Usage (Du kannst dich doch net selber DDOSSEN!): /ddos <Spieler>");
            }
        } else {
            sender.sendMessage(API.getNoPermString());
        }

        return true;
    }
}
