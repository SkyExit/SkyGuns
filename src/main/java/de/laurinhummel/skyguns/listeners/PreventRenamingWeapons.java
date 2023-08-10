package de.laurinhummel.skyguns.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class PreventRenamingWeapons implements Listener {
    @EventHandler// (priority = EventPriority.LOWEST)
    public void onPlayerInteract(InventoryClickEvent event) {
        if(!(event.getView().getType() == InventoryType.ANVIL)) { return; }
        if(!(event.getRawSlot() == 2)) { return; }
        if(!(event.getView().getItem(0).getType() != Material.AIR && event.getView().getItem(2).getType() != Material.AIR)) { return; }
        if(event.getView().getItem(0).getItemMeta().getDisplayName().equals(event.getView().getItem(2).getItemMeta().getDisplayName())) { return; }
        event.setCancelled(true);
    }
}
