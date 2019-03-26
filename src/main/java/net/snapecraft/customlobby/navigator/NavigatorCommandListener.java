package net.snapecraft.customlobby.navigator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigatorCommandListener implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Navigator.createNavigatorGUI((Player)commandSender);

        return true;
    }
}
