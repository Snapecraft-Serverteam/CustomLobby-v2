package net.snapecraft.customlobby.essential;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ChangeSlotListener implements Listener {
    @EventHandler
    public void onHeld(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 10.0F, 1.0F);
    }
}
