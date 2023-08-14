package de.laurinhummel.skyguns.guns;

public abstract class Weapon {
    public Weapon() {

    }

    public abstract int getDamage();
    public abstract int getRange();
    public abstract float getDeceleration();
    public abstract int getMagSize();
    public abstract int getDelay();
}
