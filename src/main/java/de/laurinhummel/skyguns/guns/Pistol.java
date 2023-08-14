package de.laurinhummel.skyguns.guns;

import de.laurinhummel.skyguns.utils.McColors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pistol extends Weapon {
    private final PistolType pistolType;
    public Pistol(PistolType pistolType) {
        this.pistolType = pistolType;
    }

    public enum PistolType {
        DESSERT_EAGLE(30, 25, 0.15f, 5, 2000),
        GLOCK_17(12, 20, 0.2f, 15, 400);

        private final int damage;
        private int range;
        private float deceleration;
        private int magazine;
        private int delay;

        private PistolType(int damage, int range, float deceleration, int magazine, int delay) {
            this.damage = damage;
            this.range = range;
            this.deceleration = deceleration;
            this.magazine = magazine;
            this.delay = delay;
        }

        public int getDamage() { return damage; }
        public int getRange() { return range; }
        public float getDeceleration() { return deceleration; }
        public int getMagSize() { return magazine; }
        private int getDelay() { return delay; }
    }


    @Override
    public int getDamage() { return pistolType.getDamage(); }
    public int getRange() { return pistolType.getRange(); }
    public float getDeceleration() { return pistolType.getDeceleration(); }
    public int getMagSize() { return pistolType.getMagSize(); }
    public int getDelay() { return pistolType.getDelay(); }



    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_HOE);
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
