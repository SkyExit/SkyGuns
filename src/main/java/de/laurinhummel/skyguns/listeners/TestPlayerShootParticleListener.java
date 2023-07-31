package de.laurinhummel.skyguns.listeners;

import de.laurinhummel.skyguns.guns.WeaponManager;
import de.laurinhummel.skyguns.cooldowns.CooldownManager;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TestPlayerShootParticleListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_AIR)) { return; }
        WeaponManager weaponManager = new WeaponManager(event.getItem());
        if(!weaponManager.isWeapon()) { return; }
        Player player = event.getPlayer();

        if (CooldownManager.checkCooldown(event.getPlayer())) {
            CooldownManager.setCooldown(event.getPlayer(), glock17.getDelay()); //Sets a cooldown
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