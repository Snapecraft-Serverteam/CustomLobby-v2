package net.snapecraft.customlobby.essential;

import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.utils.API;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class SetWarpCMD implements CommandExecutor {

    //   /setwarp <name> <slot>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(p.hasPermission("CustomLobby.SetWarps")) {
            if(args.length == 2) {
                if(p.getItemInHand() != null) {
                    if (API.isInt(args[1])) {
                        if(Integer.parseInt(args[1]) >= 0 && Integer.parseInt(args[1]) <= 36) {
                            String name = ChatColor.translateAlternateColorCodes('&', args[0]);
                            ItemStack item = p.getItemInHand();
                            Integer slot = Integer.parseInt(args[1]);
                            Location loc = p.getLocation();

                            CustomLobby.getInstance().getConfig().set("warp." + name + ".slot", slot);
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".item", item.getType().toString());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".world", loc.getWorld());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".X", loc.getBlockX());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".Y", loc.getBlockY());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".Z", loc.getBlockZ());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".PITCH", loc.getPitch());
                            CustomLobby.getInstance().getConfig().set("warp." + name + ".YAW", loc.getYaw());

                            p.sendMessage(CustomLobby.getPrefix() + "§aWarp '" + name + "' mit Item " + item.getType().toString().toLowerCase() + " gesetzt! Slot: " + slot);
                        } else{
                            p.sendMessage(CustomLobby.getPrefix() + "§cBitte gib einen gültigen Slot zwischen 0 und 36 an!");
                        }
                    } else {
                        p.sendMessage(CustomLobby.getPrefix() + "§cBitte gib einen gültigen Slot an!");
                    }
                } else {
                    p.sendMessage(CustomLobby.getPrefix() + "§cBitte halte dein Item in der Hand!");
                }
                return true;
            }
        } else {
            p.sendMessage(CustomLobby.noPermission);
            return true;
        }

        return false;
    }
}
