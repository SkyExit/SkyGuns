package de.laurinhummel.skyguns.main;

import de.laurinhummel.skyguns.commands.getPistol;
import de.laurinhummel.skyguns.guns.Pistol;
import de.laurinhummel.skyguns.listeners.TestPlayerShootParticleListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
        PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.registerEvents((Listener) new TestPlayerShootParticleListener(), this);

            getCommand("pistol").setExecutor(new getPistol());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
}