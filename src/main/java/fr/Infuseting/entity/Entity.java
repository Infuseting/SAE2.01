package fr.Infuseting.entity;

import fr.Infuseting.fight.Spell;

import java.util.ArrayList;
import java.util.List;

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

    public void addEffects(Spell spell){
        effects.add(spell);
    }
    public  void attack(Entity other){
        other.currentHP = other.currentHP - (Math.max(1, attack - other.armor));
    };
    public boolean isAlive() {
        if (currentHP < 1) return false;

        return true;
    };
    public boolean isDead(){
        if(currentHP <= 0) return true;
        return false;

    };
}
