package fr.Infuseting.entity;

import fr.Infuseting.fight.Spell;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Entity that has a specified HP, max HP, armor, attack and a list of effects.
 * Provides methods to attack other Entities and other methods that say if an entity has died or is still alive.
 */
public abstract class Entity {
    protected int currentHP;
    protected int maximumHP;
    protected int armor;
    protected int attack;
    protected List<Spell> effects = new ArrayList<>();

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaximumHP() {
        return maximumHP;
    }

    public void setMaximumHP(int maximumHP) {
        this.maximumHP = maximumHP;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public List<Spell> getEffects() {
        return effects;
    }

    public void setEffects(List<Spell> effects) {
        this.effects = effects;
    }

    /**
     * Adds an effect to the effects List.
     * @param spell The specified spell effect that will be added to the effects List.
     */
    public void addEffects(Spell spell){
        effects.add(spell);
    }

    /**
     * Makes this entity attack an other specified one.
     * @param other The target of the attack.
     */
    public  void attack(Entity other){
        other.currentHP = other.currentHP - (Math.max(1, attack - other.armor));
    };

    /**
     * Indicates whether or not this Entity is alive or not
     * @return true if the Entity is alive and false if it isn't.
     */
    public boolean isAlive() {
        if (currentHP < 1) return false;

        return true;
    };

    /**
     * Indicates whether or not this Entity is dead or not
     * @return true if the Entity is dead and false if it isn't.
     */
    public boolean isDead(){
        if(currentHP <= 0) return true;
        return false;

    };
}
