package net.snapecraft.customlobby.essential;

import com.connorlinfoot.titleapi.TitleAPI;
import net.snapecraft.customlobby.CustomLobby;
import net.snapecraft.customlobby.gadgets.BootsInventory;
import net.snapecraft.customlobby.gadgets.GadgetsInventory;
import net.snapecraft.customlobby.gadgets.HeadsInventory;
import net.snapecraft.customlobby.hide.Hide;
import net.snapecraft.customlobby.navigator.Navigator;
import net.snapecraft.customlobby.settings.Settings;
import net.snapecraft.customlobby.settings.SettingsMainGUI;
import net.snapecraft.customlobby.utils.API;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MainListener implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!KillEntityCMD.allowKills) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {
        boolean rain = event.toWeatherState();
        if (rain) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {
        boolean storm = event.toThunderState();
        if (storm) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (!e.getPlayer().hasPermission("CustomLobby.pickupItems")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMob(EntitySpawnEvent e) {
        if(!e.getEntityType().equals(EntityType.PLAYER)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("§8[§2+§8] §7" + e.getPlayer().getName());
        if (SpawnCMD.spawnIsDefined()) {
            e.getPlayer().teleport(SpawnCMD.spawnLoc);
        }
        e.getPlayer().getInventory().clear();
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        StartItems.setStarterItems(e.getPlayer());
        String title = CustomLobby.getInstance().getConfig().getString("settings.jointitle");
        String subtitle = CustomLobby.getInstance().getConfig().getString("settings.joinsubtitle");

        title = title.replaceAll("\\{player}", e.getPlayer().getName());
        subtitle = subtitle.replaceAll("\\{player}", e.getPlayer().getName());
        TitleAPI.sendTitle(e.getPlayer(), 10, 20, 5, ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!BuildModeCMD.buildmodeplayers.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        CustomLobby.getInstance().saveConfig();
        CustomLobby.getInstance().reloadConfig();
        Player p = (Player) e.getWhoClicked();
        if(Settings.hasSoundsEnabled(p))
            p.playSound(p.getLocation(), Sound.CLICK, 10.0F, 1.0F);

        // NAVIGATOR
        if ((e.getInventory().getSize() == 36) && e.getInventory().getTitle().equals("§aNavigator")) {
            int slot = e.getSlot();
            e.setCancelled(true);

            ConfigurationSection navSection = CustomLobby.getInstance().getConfig().getConfigurationSection("navigator.row");
            if(slot < 9) {
                ConfigurationSection row = navSection.getConfigurationSection(Integer.toString(0));
                ConfigurationSection obj = row.getConfigurationSection(Integer.toString(slot));
                if(obj.getString("action") != null) {
                    Bukkit.dispatchCommand(e.getWhoClicked(), obj.getString("action"));
                }
            } else if(slot > 8 && slot < 18) {
                ConfigurationSection row = navSection.getConfigurationSection(Integer.toString(1));
                ConfigurationSection obj = row.getConfigurationSection(Integer.toString(slot - 9));
                if(obj.getString("action") != null) {
                    Bukkit.dispatchCommand(e.getWhoClicked(), obj.getString("action"));
                }
            } else if(slot > 17 && slot < 27) {
                ConfigurationSection row = navSection.getConfigurationSection(Integer.toString(2));
                ConfigurationSection obj = row.getConfigurationSection(Integer.toString(slot - 18));
                if(obj.getString("action") != null) {
                    Bukkit.dispatchCommand(e.getWhoClicked(), obj.getString("action"));
                }
            } else if(slot > 26 && slot < 36) {
                ConfigurationSection row = navSection.getConfigurationSection(Integer.toString(3));
                ConfigurationSection obj = row.getConfigurationSection(Integer.toString(slot - 27));
                if(obj.getString("action") != null) {
                    Bukkit.dispatchCommand(e.getWhoClicked(), obj.getString("action"));
                }
            }

            // SETTINGS
        } else if ((e.getInventory().getSize() == 27) && e.getInventory().getTitle().equals("§aDeine Einstellungen")) {
            int slot = e.getSlot();
            e.setCancelled(true);
            // Toggle Sounds
            if (slot == 9) {
                boolean soundsActivated = Settings.hasSoundsEnabled(p);
                if (Settings.isInSoundsEnabledList(p)) {
                    Settings.soundsEnabled.replace(p, API.reverseBoolean(soundsActivated));
                } else {
                    Settings.soundsEnabled.put(p, API.reverseBoolean(soundsActivated));
                }
                if (soundsActivated) {
                    p.sendMessage(API.getPrefix() + " §aDie Sounds wurden erfolgreich deaktiviert!");
                } else {
                    p.sendMessage(API.getPrefix() + " §aDie Sounds wurden erfolgreich aktiviert!");
                }
                p.closeInventory();
            }

            // GADGETS
        } else if((e.getInventory().getSize() == 27) && e.getInventory().getTitle().equals("§aDeine Gadgets")) {
            e.setCancelled(true);
            int slot = e.getSlot();
            // Heads
            if (slot == 9) {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(HeadsInventory.getInventory((Player) e.getWhoClicked()));
            } else if (slot == 10) {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(BootsInventory.getInventory((Player) e.getWhoClicked()));
            }
        } else if((e.getInventory().getSize() == 36) && e.getInventory().getTitle().equals("§aDeine Köpfe")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    e.getWhoClicked().getInventory().setHelmet(null);
                    e.getWhoClicked().sendMessage(API.getPrefix() + " §aDein Kopf wurde entfernt");
                    e.getWhoClicked().closeInventory();
                } else {
                    if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)) {
                        if(e.getSlot() < 36) {
                            if(e.getWhoClicked().hasPermission("CustomLobby.skull." + e.getCurrentItem().getItemMeta().getDisplayName())) {
                                e.getWhoClicked().getInventory().setHelmet(e.getCurrentItem());
                                e.getWhoClicked().sendMessage(API.getPrefix() + " §cDein Kopf wurde gesetzt");
                                e.getWhoClicked().closeInventory();
                            }
                        }
                    }
                }
            }
        } else if((e.getInventory().getSize() == 27) && e.getInventory().getTitle().equals("§aDeine Schuhe")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    removeEffects((Player) e.getWhoClicked(), "boots");
                    e.getWhoClicked().getInventory().setBoots(null);
                    e.getWhoClicked().sendMessage(API.getPrefix() + " §aDeine Schuhe wurde entfernt");
                } else {

                    if(e.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)) {
                        if(e.getWhoClicked().hasPermission("CustomLobby.boot.leather")) {
                            e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                            e.getWhoClicked().sendMessage(API.getPrefix() + " §aDu hast neue Schuhe angezogen");
                            e.getWhoClicked().closeInventory();
                            //Unsichtbar
                            PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, -1, 3, true, false);
                            e.getWhoClicked().addPotionEffect(effect);
                        } else {
                            e.getWhoClicked().sendMessage(API.getNoPermString());
                        }
                    }
                    if(e.getCurrentItem().getType().equals(Material.CHAINMAIL_BOOTS)) {
                        if(e.getWhoClicked().hasPermission("CustomLobby.boot.chainmail")) {
                            e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                            e.getWhoClicked().sendMessage(API.getPrefix() + " §aDu hast neue Schuhe angezogen");
                            e.getWhoClicked().closeInventory();
                        } else {
                            e.getWhoClicked().sendMessage(API.getNoPermString());
                        }
                    }
                    if(e.getCurrentItem().getType().equals(Material.IRON_BOOTS)) {
                        if(e.getWhoClicked().hasPermission("CustomLobby.boot.iron")) {
                            e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                            e.getWhoClicked().sendMessage(API.getPrefix() + " §aDu hast neue Schuhe angezogen");
                            e.getWhoClicked().closeInventory();
                            //Jump
                            PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, -1, 9, true, false);
                            e.getWhoClicked().addPotionEffect(effect);
                        } else {
                            e.getWhoClicked().sendMessage(API.getNoPermString());
                        }
                    }
                    if(e.getCurrentItem().getType().equals(Material.GOLD_BOOTS)) {
                        if(e.getWhoClicked().hasPermission("CustomLobby.boot.iron")) {
                            e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
                            e.getWhoClicked().sendMessage(API.getPrefix() + " §aDu hast neue Schuhe angezogen");
                            e.getWhoClicked().closeInventory();
                            // Speed
                            ((Player) e.getWhoClicked()).setWalkSpeed(1.5f);
                        } else {
                            e.getWhoClicked().sendMessage(API.getNoPermString());
                        }
                    }
                    if(e.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
                        if(e.getWhoClicked().hasPermission("CustomLobby.boot.iron")) {
                            e.getWhoClicked().getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                            e.getWhoClicked().sendMessage(API.getPrefix() + " §aDu hast neue Schuhe angezogen");
                            e.getWhoClicked().closeInventory();
                            // Creative Fly
                            ((Player) e.getWhoClicked()).setAllowFlight(true);
                        } else {
                            e.getWhoClicked().sendMessage(API.getNoPermString());
                        }
                    }

                }
            }
        } else {
            if(!BuildModeCMD.buildmodeplayers.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }

    @Deprecated
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        Material compass = Material.COMPASS;
        Material blazerod = Material.BLAZE_ROD;
        Material stick = Material.STICK;
        Material chest = Material.CHEST;
        Material noteblock = Material.NOTE_BLOCK;
        Material skull = Material.SKULL_ITEM;


        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (BuildModeCMD.buildmodeplayers.contains(p.getName())) {
                e.setCancelled(false);
            }
            if (item == null) {
                item = new ItemStack(Material.AIR);
            }
            if (item.getType() == compass) {
                Navigator.createNavigatorGUI(p);
            }
            if (item.getType() == blazerod || item.getType() == stick) {
                if(Hide.ishidden.keySet().contains(p)) {
                    if (!Hide.ishidden.get(p)) {
                        Hide.hideall(p);
                        StartItems.updateItemsHidden(p);
                    } else {
                        Hide.showall(p);
                        StartItems.updateItems(p);
                    }
                } else {
                    Hide.hideall(p);
                    StartItems.updateItemsHidden(p);
                }

            }

            if (item.getType() == chest) {
                //p.sendMessage(CustomLobby.getPrefix() + "§cGadgets sind zur Zeit noch nicht aktiviert. Bitte habe etwas Geduld!");
                e.getPlayer().openInventory(GadgetsInventory.getInventory(e.getPlayer()));
            }
            if (item.getType() == noteblock) {
                Bukkit.dispatchCommand(p, "music");
            }
            if(item.getType() == skull) {
                p.openInventory(SettingsMainGUI.createMainGui(p));
            }
            e.setCancelled(true);
        }
        if (BuildModeCMD.buildmodeplayers.contains(p.getName())) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        removeEffects(e.getPlayer(), "all");
        e.getPlayer().getInventory().setBoots(null);
        e.getPlayer().getInventory().setHelmet(null);
        if(Hide.ishidden.keySet().contains(e.getPlayer()))
            Hide.ishidden.remove(e.getPlayer());
    }

    public void removeEffects(Player p, String what) {
        if(what.contains("boots") || what.contains("all")) {
            // Effects: Boots
            if(p.getInventory().getBoots() != null) {
                if(p.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
                if(p.getInventory().getBoots().getType().equals(Material.GOLD_BOOTS))
                    p.setWalkSpeed(1f);
                if(p.getInventory().getBoots().getType().equals(Material.LEATHER_BOOTS))
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                if(p.getInventory().getBoots().getType().equals(Material.IRON_BOOTS))
                    p.removePotionEffect(PotionEffectType.JUMP);
            }
        }

    }

}
