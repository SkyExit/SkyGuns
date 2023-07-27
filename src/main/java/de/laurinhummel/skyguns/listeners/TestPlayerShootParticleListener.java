package de.laurinhummel.skyguns.listeners;

import de.laurinhummel.skyguns.guns.Pistol;
import de.laurinhummel.skyguns.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class TestPlayerShootParticleListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Pistol glock17 = new Pistol(Pistol.PistolType.GLOCK_17);
        if(!event.getAction().equals(Action.LEFT_CLICK_AIR)) { return; }
        if(!Objects.equals(event.getItem(), new ItemStack(glock17.getItem()))) { return; }
        Player player = event.getPlayer();

        if (Cooldown.checkCooldown(event.getPlayer())) {
            Cooldown.setCooldown(event.getPlayer(), glock17.getDelay()); //Sets a cooldown
        } else { return; }

        int i = 0;

        Location loc = player.getEyeLocation();
        player.playSound(loc, Sound.UI_BUTTON_CLICK, 1, 1);

        double maxLength = 20;
        for(double d = 2; d <= maxLength; d++) {
            loc.add(loc.getDirection());
            if(!loc.getBlock().isPassable()) { return; }
            player.spawnParticle(Particle.VILLAGER_HAPPY, loc, 1, 0, 0, 0);

            for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0.5, 1, 0.5)) {
                if(entity instanceof Mob) {
                    ((LivingEntity) entity).damage(200);
                    //player.sendMessage("!");
                    i++;
                    if(i == 2) { return; }
                }
            }
        }
    }
}