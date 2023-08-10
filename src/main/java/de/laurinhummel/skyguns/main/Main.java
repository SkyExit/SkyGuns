package de.laurinhummel.skyguns.main;

import de.laurinhummel.skyguns.commands.getPistol;
import de.laurinhummel.skyguns.guns.Pistol;
import de.laurinhummel.skyguns.listeners.PreventRenamingWeapons;
import de.laurinhummel.skyguns.listeners.TestPlayerShootParticleListener;
import de.laurinhummel.skyguns.cooldowns.CooldownManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }
    @Override
    public void onEnable() {
        CooldownManager.setupCooldown();
        PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.registerEvents(new TestPlayerShootParticleListener(), this);
            pluginManager.registerEvents(new PreventRenamingWeapons(), this);

            getCommand("pistol").setExecutor(new getPistol());

        ShapedRecipe itemRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "GLOCK17"), new Pistol(Pistol.PistolType.GLOCK_17).getItem());
            itemRecipe.shape(" II", "GNL", " I ");
            itemRecipe.setIngredient('I', Material.IRON_BLOCK);
            itemRecipe.setIngredient('G', Material.GUNPOWDER);
            itemRecipe.setIngredient('N', Material.NETHERITE_INGOT);
            itemRecipe.setIngredient('L', Material.LEVER);
        Bukkit.addRecipe(itemRecipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
}