package de.laurinhummel.skyguns.listeners;

import de.laurinhummel.skyguns.guns.DamageManager;
import de.laurinhummel.skyguns.guns.Weapon;
import de.laurinhummel.skyguns.guns.WeaponManager;
import de.laurinhummel.skyguns.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.List;

public class WeaponShootListenerV2 implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_AIR)) { return; }

        WeaponManager weaponManager = new WeaponManager(event.getItem());
        if(!weaponManager.isWeapon()) { return; }
        Weapon weapon = weaponManager.getWeapon();

        Player player = event.getPlayer();

        if (Cooldown.checkCooldown(event.getPlayer())) {
            Cooldown.setCooldown(event.getPlayer(), weapon.getDelay()); //Sets a cooldown
        } else { return; }

        player.setCooldown(Material.GOLDEN_HOE, weapon.getDelay() / 50);  //convert ms into minecraft ticks   (1s = 20ticks   -->   1000ms / 50 = 20ticks)

        Location loc2 = player.getEyeLocation();
        player.playSound(loc2, Sound.UI_BUTTON_CLICK, 1, 1);

        int range = weapon.getRange();
        for(double d = 2; d <= range; d++) {
            loc2.add(loc2.getDirection());
            if(!loc2.getBlock().isPassable()) { continue; }
            player.spawnParticle(Particle.VILLAGER_HAPPY, loc2, 1, 0, 0, 0);
        }

        List<Entity> nearbyE = player.getNearbyEntities(range, range, range);
        ArrayList<LivingEntity> livingE = new ArrayList<>();

        for (Entity e : nearbyE) {
            if (e instanceof LivingEntity && ((LivingEntity) e).getHealth() > 0) {
                livingE.add((LivingEntity) e);
            }
        }

        BlockIterator bItr = new BlockIterator(player, range);
        Block block;
        Location loc;
        int bx, by, bz;
        double ex, ey, ez;
        // loop through player's line of sight

        int distance = 0;

        while (bItr.hasNext()) {
            distance++;
            block = bItr.next();

            if(!block.isPassable()) { return; }

            bx = block.getX();
            by = block.getY();
            bz = block.getZ();

            if(!block.isPassable()) { return; }

            //player.sendMessage(bx + " " + by + " " + bz);
            // check for entities near this block in the line of sight
            for (LivingEntity entity : livingE) {
                loc = entity.getLocation();
                ex = loc.getX();
                ey = loc.getY();
                ez = loc.getZ();
                //if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
                if ((bx - 0.5 <= ex && ex <= bx + 0.5) && (bz - 0.5 <= ez && ez <= bz + 0.5) && (by - 1 <= ey && ey <= by + 1)) {
                    // entity is close enough, set target and stop
                    DamageManager damageManager = new DamageManager(weapon, distance);
                    int dmg = damageManager.calculateDamage();

                    entity.damage(dmg);
                    EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.CUSTOM, dmg);
                    entity.setLastDamageCause(ede);
                    return;
                }
            }
        }
    }
}
