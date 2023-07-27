package de.laurinhummel.skyguns.guns;

import de.laurinhummel.skyguns.utils.McColors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pistol {
    private final PistolType pistolType;
    public Pistol(PistolType pistolType) {
        this.pistolType = pistolType;
    }

    public enum PistolType {
        DESSERT_EAGLE(5, 2000),
        GLOCK_17(15, 400);

        private int magSize;
        private int delay;

        private PistolType(int magSize, int delay) {
            this.magSize = magSize;
            this.delay = delay;
        }

        public int getMagSize() { return magSize; }
        public int getDelay() { return delay; }
    }

    public int getMagSize() { return pistolType.getMagSize(); }
    public int getDelay() { return pistolType.getDelay(); }
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        switch (pistolType) {
            case DESSERT_EAGLE -> {
                itemMeta.setDisplayName(McColors.AQUA + "Sky" + McColors.GOLD + "Guns" + McColors.BLACK + " - " + McColors.GRAY + "DESSERT EAGLE");
            }
            case GLOCK_17 -> {
                itemMeta.setDisplayName(McColors.AQUA + "Sky" + McColors.GOLD + "Guns" + McColors.BLACK + " - " + McColors.GRAY + "GLOCK 17");
            }
        }
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
