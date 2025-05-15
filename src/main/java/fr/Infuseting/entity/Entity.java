package fr.Infuseting.entity;

public abstract class Entity {
    protected int currentHP;
    protected int maximumHP;
    protected int armor;
    protected int attack;
    protected List<Spell> effects;

    public abstract void attack(Entity other);
    public abstract void isAlive();
    public abstract void isDead();
}
