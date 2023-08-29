package de.laurinhummel.skyguns.guns;

import de.laurinhummel.skyguns.utils.SkyLogger;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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

        int damage = (int) Math.round(weaponDamage * Math.pow(1 - deceleration, distance));
        SkyLogger.sendConsole(String.valueOf(damage));

        return damage;
    }

    public int calculateDamagePlayer(Player p) {
        double damage = (double) calculateDamage() / 2;
        double armorPoints = p.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        double armorToughness = p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();

        int withArmorReduction = (int) Math.round(damage * (1 - Math.min(20, Math.max(armorPoints / 5, armorPoints - damage / (2 + armorToughness / 4))) / 25));

        SkyLogger.sendConsole(String.valueOf(withArmorReduction));

        return withArmorReduction;
    }
}
