package net.snapecraft.customlobby.settings;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Settings {
    public static HashMap<Player, Boolean> soundsEnabled = new HashMap<>();

    public static boolean hasSoundsEnabled(Player p) {
        if(Settings.soundsEnabled.keySet().contains(p)) {
            return Settings.soundsEnabled.get(p);
        } else {
            return false;
        }
    }
    public static boolean isInSoundsEnabledList(Player p) {
        return Settings.soundsEnabled.keySet().contains(p);
    }
}
