package de.laurinhummel.skyguns.cooldowns;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CooldownManager {
    public static List<Cooldown> cooldowns;

    public CooldownManager() {
    }

    public static void setupCooldown() {
        cooldowns = new ArrayList<Cooldown>() {
        };
    }

    public static void setCooldown(Player player, int milliseconds) {
        double delay = (double)(System.currentTimeMillis() + (long)(milliseconds));
        cooldowns.add(new Cooldown(player.getUniqueId(), delay));
    }

    public static boolean checkCooldown(Player player) {
        if(cooldowns.isEmpty()) { return false; }
        for (Cooldown cooldown : cooldowns ) {
            if(cooldown.uuid.equals(player.getUniqueId())) {
                return !(cooldown.cooldown <= System.currentTimeMillis());
            }
        }
        return false;
    }
}