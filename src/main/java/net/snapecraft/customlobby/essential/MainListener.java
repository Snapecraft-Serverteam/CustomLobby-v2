package net.snapecraft.customlobby.essential;

import com.connorlinfoot.titleapi.TitleAPI;
import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.hide.Hide;
import net.snapecraft.customlobby.navigator.Navigator;
import net.snapecraft.customlobby.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class MainListener implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e)
    {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if (!KillEntityCMD.allowKills) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event)
    {
        boolean rain = event.toWeatherState();
        if (rain) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event)
    {
        boolean storm = event.toThunderState();
        if (storm) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e)
    {
        if (!e.getPlayer().hasPermission("CustomLobby.pickupItems")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.setJoinMessage("");
        if (SpawnCMD.spawnIsDefined()) {
            e.getPlayer().teleport(SpawnCMD.spawnLoc);
        }
        e.getPlayer().getInventory().clear();
        StartItems.setStarterItems(e.getPlayer());
        String title = CustomLobby.getInstance().getConfig().getString("settings.jointitle");
        String subtitle = CustomLobby.getInstance().getConfig().getString("settings.joinsubtitle");

        TitleAPI.sendTitle(e.getPlayer(), 10, 20, 5, title, subtitle);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e)
    {
        if (!BuildModeCMD.buildmodeplayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e)
    {
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        Player p = (Player)e.getWhoClicked();
        p.playSound(p.getLocation(), Sound.CLICK, 10.0F, 1.0F);
        if ((e.getInventory().getSize() == 36) && e.getInventory().getTitle().equals("§aNavigator")) {
            Integer slot = e.getSlot();

            if(!(API.getNameOfCorrespondingNavSlot(slot).equals(""))) {
                ConfigurationSection cs = API.getCorrespondingConfigSection(API.getNameOfCorrespondingNavSlot(slot));
                Location loc = new Location(Bukkit.getWorld(cs.getString("world")),
                        cs.getDouble("X"),
                        cs.getDouble("Y"),
                        cs.getDouble("Z"),
                        Float.parseFloat(cs.getString("YAW")),
                        Float.parseFloat(cs.getString("PITCH")));
                p.teleport(loc);
            }
        }
        if ((!e.getInventory().getName().equalsIgnoreCase("§2Gadget§r - §bShop")) || (

                (!e.getInventory().getName().equalsIgnoreCase("§9Profil, Freunde und Parties")) ||

                        (!BuildModeCMD.buildmodeplayers.contains(p.getName())))) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @Deprecated
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        Material compass = Material.COMPASS;
        Material blazerod = Material.BLAZE_ROD;
        Material chest = Material.CHEST;
        Material skull = Material.SKULL_ITEM;
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (BuildModeCMD.buildmodeplayers.contains(p.getName()))
            {
                e.setCancelled(false);
            }
            if (item == null) {
                item = new ItemStack(Material.AIR);
            }
            if (item.getType() == compass) {
                Navigator.createNavigatorGUI(p);
            }
            if (item.getType() == blazerod) {
                if (!Hide.ishidden) {
                    Hide.hideall(p);
                    StartItems.setStarterItemsHidden(p);
                } else {
                    Hide.showall(p);
                    StartItems.setStarterItems(p);
                }
            }
            if (item.getType() == chest)
            {
                p.sendMessage(CustomLobby.getPrefix() + "§cGadgets sind zur Zeit noch nicht aktiviert. Bitte habe etwas Geduld!");
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onSneakToggle(PlayerToggleSneakEvent e) {
        if(e.isSneaking()) {
            if(e.getPlayer().hasPermission("CustomLobby.BuildModeCMD")) {
                if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
                    StartItems.setBuildmodeItems(e.getPlayer());
                }
            }
        } else {
            if (!Hide.ishidden) {
                StartItems.setStarterItemsHidden(e.getPlayer());
            } else {
                StartItems.setStarterItems(e.getPlayer());
            }
        }
    }
}
