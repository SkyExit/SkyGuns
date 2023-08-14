package de.laurinhummel.skyguns.guns;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageManager {
    Weapon weapon;
    int distance;

    public DamageManager(Weapon weapon, int distance) {
        this.weapon = weapon;
        this.distance = distance;
    }

    public int calculateDamage() {
        int weaponDamage = weapon.getDamage();
        float deceleration = weapon.getDeceleration();

        return (int) Math.round(weaponDamage * Math.pow(1 - deceleration, 0.75 * distance));
    }
}
