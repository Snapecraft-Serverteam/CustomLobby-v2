package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player p = (Player)sender;
        if (p.hasPermission("CustomLobby.setSpawn"))
        {
            Location currentLoc = p.getLocation();
            CustomLobby.getInstance().reloadConfig();

            CustomLobby.getInstance().getConfig().set("spawn.WORLD", currentLoc.getWorld().getName());
            CustomLobby.getInstance().getConfig().set("spawn.X", currentLoc.getX());
            CustomLobby.getInstance().getConfig().set("spawn.Y", currentLoc.getY());
            CustomLobby.getInstance().getConfig().set("spawn.Z", currentLoc.getZ());
            CustomLobby.getInstance().getConfig().set("spawn.PITCH", currentLoc.getPitch());
            CustomLobby.getInstance().getConfig().set("spawn.YAW", currentLoc.getYaw());
            CustomLobby.getInstance().getConfig().set("spawn.exist", true);
            CustomLobby.getInstance().saveConfig();
            p.sendMessage(CustomLobby.prefix + "§aDer Spawn wurde erfolgreich gesetzt!");
        }
        else
        {
            p.sendMessage(CustomLobby.noPermission);
        }
        return true;
    }
}
