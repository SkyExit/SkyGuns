package de.laurinhummel.skyguns.cooldowns;

import java.util.UUID;

public class Cooldown {
    UUID uuid;
    double cooldown;

    public Cooldown(UUID uuid, double cooldown) {
        this.uuid = uuid;
        this.cooldown = cooldown;
    }

    public UUID getUUID() { return uuid; }
    public double getCooldown() { return cooldown; }
}
