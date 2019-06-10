package net.snapecraft.customlobby;
import net.snapecraft.customlobby.essential.*;
import net.snapecraft.customlobby.gamemode.GamemodeCMD;
import net.snapecraft.customlobby.navigator.NavigatorCommandListener;
import net.snapecraft.customlobby.navigator.SetNavigatorWarpsCMD;
import net.snapecraft.customlobby.nick.Nick;
import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public class CustomLobby extends JavaPlugin
{
    public static String prefix = "PREFIX";
    public static String noPermission = "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
    public static CustomLobby instance;

    public void onEnable()
    {
        instance = this;
        initConfig();
        init();
        System.out.println("\n                _                  _       _     _           \n" +
                "               | |                | |     | |   | |          \n" +
                "  ___ _   _ ___| |_ ___  _ __ ___ | | ___ | |__ | |__  _   _ \n" +
                " / __| | | / __| __/ _ \\| '_ ` _ \\| |/ _ \\| '_ \\| '_ \\| | | |\n" +
                "| (__| |_| \\__ \\ || (_) | | | | | | | (_) | |_) | |_) | |_| |\n" +
                " \\___|\\__,_|___/\\__\\___/|_| |_| |_|_|\\___/|_.__/|_.__/ \\__, |    v." + getDescription().getVersion() + "\n" +
                "                                                        __/ |\n" +
                "                                                       |___/ ");
    }

    private void initConfig()
    {
        saveConfig();
        reloadConfig();
        getConfig().addDefault("player.DEFAULT.exist", false);
        getConfig().addDefault("spawn.exist", false);

        getConfig().addDefault("SQL.host", "");
        getConfig().addDefault("SQL.user", "");
        getConfig().addDefault("SQL.pw", "");
        getConfig().addDefault("SQL.db", "");

        getConfig().addDefault("spawn.WORLD", "world");
        getConfig().addDefault("spawn.x", 0);
        getConfig().addDefault("spawn.y", 0);
        getConfig().addDefault("spawn.z", 0);
        getConfig().addDefault("spawn.YAW", 0.0D);
        getConfig().addDefault("spawn.PITCH", 0.0D);

        getConfig().addDefault("settings.doublejump", 0.5D);
        getConfig().addDefault("settings.prefix", "§7[§3Lobby§7]§5 > §r");
        getConfig().addDefault("settings.jointitle", "Willkommen, %s");
        getConfig().addDefault("settings.joinsubtitle", "auf dem Snapecraft Netzwerk!");

        getConfig().addDefault("warp.default.slot", 1);
        getConfig().addDefault("warp.default.item", Material.GRASS.toString());
        getConfig().addDefault("warp.default.world", "world");
        getConfig().addDefault("warp.default.X", 1);
        getConfig().addDefault("warp.default.Y", 1);
        getConfig().addDefault("warp.default.Z", 1);
        getConfig().addDefault("warp.default.PITCH", 1);
        getConfig().addDefault("warp.default.YAW", 1);

        List<String> headlist = new ArrayList<>();
        headlist.add("Mario");
        headlist.add("Pikachu");
        headlist.add("Tallerik");
        headlist.add("Pepe44");
        headlist.add("MayusYT");
        headlist.add("NoveFerrero");
        headlist.add("MHF_Pumpkin");
        headlist.add("MHF_Chicken");
        getConfig().addDefault("headlist", headlist);

        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        prefix = getConfig().getString("settings.prefix");
        if(!prefix.equals("PREFIX")) {
            noPermission = prefix + "§cDu hast nicht die nötige Berechtigung, um diesen Befehl auszuführen";
        }
    }

    private void init()
    {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChangeSlotListener(), this);

        getCommand("nick").setExecutor(new Nick());
        getCommand("nickplayer").setExecutor(new Nick());
        getCommand("fly").setExecutor(new FlyCMD());
        getCommand("c").setExecutor(new GamemodeCMD());
        getCommand("s").setExecutor(new GamemodeCMD());
        getCommand("sp").setExecutor(new GamemodeCMD());
        getCommand("gui").setExecutor(new NavigatorCommandListener());
        getCommand("setwarp").setExecutor(new SetNavigatorWarpsCMD());
        getCommand("build").setExecutor(new BuildModeCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("setspawn").setExecutor(new SetSpawnCMD());
        getCommand("tpall").setExecutor(new TpAllCMD());
        getCommand("clearall").setExecutor(new KillEntityCMD());
        getCommand("connServer").setExecutor(new ConnectServerCMD());
        getCommand("ddos").setExecutor(new DDOS());
    }

    public void onDisable()
    {
        System.out.println("[Lobby] Disabled!");
    }

    public static CustomLobby getInstance()
    {
        return instance;
    }

    public static String getPrefix()
    {
        return prefix;
    }
}
