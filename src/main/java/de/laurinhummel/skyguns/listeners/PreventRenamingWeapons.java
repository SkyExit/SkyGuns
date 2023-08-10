package de.laurinhummel.skyguns.listeners;

import de.laurinhummel.skyguns.guns.WeaponManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class PreventRenamingWeapons implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(InventoryClickEvent event) {
        if(!(event.getView().getType() == InventoryType.ANVIL)) { return; }
        if(!(event.getRawSlot() == 2)) { return; }
        ItemStack eventItem = event.getView().getItem(0);
        if(!(eventItem.getType() != Material.AIR && event.getView().getItem(2).getType() != Material.AIR)) { return; }
        if(eventItem.getItemMeta().getDisplayName().equals(event.getView().getItem(2).getItemMeta().getDisplayName())) { return; }
        if(new WeaponManager(eventItem).isWeapon()) { event.setCancelled(true); }
    }
}
