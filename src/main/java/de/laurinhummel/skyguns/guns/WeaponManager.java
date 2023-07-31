package de.laurinhummel.skyguns.guns;

import org.bukkit.inventory.ItemStack;

public class WeaponManager {
    ItemStack weapon;

    public enum WeaponClass {
        PISTOL(false, 1);

        private final boolean auto;
        private final int bulletsPerShot;

        private WeaponClass(boolean auto, int bulletsPerShot) {
            this.auto = auto;
            this.bulletsPerShot = bulletsPerShot;
        }

        public boolean getMagSize() { return auto; }
        public int getDelay() { return bulletsPerShot; }
    }

    public WeaponManager(ItemStack weapon) {
        this.weapon = weapon;
    }

    public boolean isWeapon() {
        ItemStack glock = new Pistol(Pistol.PistolType.GLOCK_17).getItem();
        ItemStack deagle = new Pistol(Pistol.PistolType.DESSERT_EAGLE).getItem();

        return weapon.equals(glock) || weapon.equals(deagle);
    }
}
