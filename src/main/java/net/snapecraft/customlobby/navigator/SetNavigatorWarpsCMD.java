package net.snapecraft.customlobby.navigator;

import net.snapecraft.customlobby.CustomLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetNavigatorWarpsCMD implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Player p = (Player)commandSender;
        if ((args.length == 1) && (p.hasPermission("CustomLobby.setNavWarps")))
        {
            if (args[0].equalsIgnoreCase("skyblock"))
            {
                CustomLobby.getInstance().getConfig().set("warps.skyblock.WORLD", p.getLocation().getWorld().getName());
                CustomLobby.getInstance().getConfig().set("warps.skyblock.X", p.getLocation().getBlockX());
                CustomLobby.getInstance().getConfig().set("warps.skyblock.Y", p.getLocation().getBlockY());
                CustomLobby.getInstance().getConfig().set("warps.skyblock.Z",p.getLocation().getBlockZ());
                CustomLobby.getInstance().getConfig().set("warps.skyblock.PITCH", p.getLocation().getYaw());
                CustomLobby.getInstance().getConfig().set("warps.skyblock.YAW", p.getLocation().getPitch());
                p.sendMessage(CustomLobby.prefix + "SkyBlock gesetzt!");
            }
            if (args[0].equalsIgnoreCase("skywars"))
            {
                CustomLobby.getInstance().getConfig().set("warps.skywars.WORLD", p.getLocation().getWorld().getName());
                CustomLobby.getInstance().getConfig().set("warps.skywars.X", p.getLocation().getBlockX());
                CustomLobby.getInstance().getConfig().set("warps.skywars.Y", p.getLocation().getBlockY());
                CustomLobby.getInstance().getConfig().set("warps.skywars.Z", p.getLocation().getBlockZ());
                CustomLobby.getInstance().getConfig().set("warps.skywars.PITCH", p.getLocation().getYaw());
                CustomLobby.getInstance().getConfig().set("warps.skywars.YAW", p.getLocation().getPitch());
                p.sendMessage(CustomLobby.prefix + "skywars gesetzt!");
            }
            if (args[0].equalsIgnoreCase("pvp"))
            {
                CustomLobby.getInstance().getConfig().set("warps.pvp.WORLD", p.getLocation().getWorld().getName());
                CustomLobby.getInstance().getConfig().set("warps.pvp.X", p.getLocation().getBlockX());
                CustomLobby.getInstance().getConfig().set("warps.pvp.Y", p.getLocation().getBlockY());
                CustomLobby.getInstance().getConfig().set("warps.pvp.Z", p.getLocation().getBlockZ());
                CustomLobby.getInstance().getConfig().set("warps.pvp.PITCH", p.getLocation().getYaw());
                CustomLobby.getInstance().getConfig().set("warps.pvp.YAW", p.getLocation().getPitch());
                p.sendMessage(CustomLobby.prefix + "pvp gesetzt!");
            }
        }
        else
        {
            p.sendMessage(CustomLobby.noPermission);
        }
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        return true;
    }
}
