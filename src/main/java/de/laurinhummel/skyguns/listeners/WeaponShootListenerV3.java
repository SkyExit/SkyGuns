package de.laurinhummel.skyguns.listeners;

import de.laurinhummel.skyguns.guns.DamageManager;
import de.laurinhummel.skyguns.guns.Weapon;
import de.laurinhummel.skyguns.guns.WeaponManager;
import de.laurinhummel.skyguns.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;


public class WeaponShootListenerV3 implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) { return; }

        WeaponManager weaponManager = new WeaponManager(event.getItem());
        if (!weaponManager.isWeapon()) { return; }
        Weapon weapon = weaponManager.getWeapon();

        Player player = event.getPlayer();

        if (Cooldown.checkCooldown(event.getPlayer())) {
            Cooldown.setCooldown(event.getPlayer(), weapon.getDelay()); //Sets a cooldown
        } else { return; }

        player.setCooldown(Material.GOLDEN_HOE, weapon.getDelay() / 50);  //convert ms into minecraft ticks   (1s = 20ticks   -->   1000ms / 50 = 20ticks)

        Location loc = player.getEyeLocation();
        int range = weapon.getRange();
        player.playSound(loc, Sound.UI_BUTTON_CLICK, 1, 1);

        RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(loc.add(loc.getDirection().normalize()), loc.getDirection(), range);


        if (rayTraceResult != null) {
            LivingEntity target = (LivingEntity) rayTraceResult.getHitEntity();
            target.damage(new DamageManager(weapon, (int) Math.round(loc.distance(target.getLocation()))).calculateDamage());
        }

        int startPoint = 1;
        loc.add(loc.getDirection().multiply(startPoint));
        for (; startPoint <= range; startPoint++) {
            loc.add(loc.getDirection());
            if (!loc.getBlock().isPassable()) {
                continue;
            }
            player.spawnParticle(Particle.VILLAGER_HAPPY, loc, 1, 0, 0, 0);
        }
    }
}
