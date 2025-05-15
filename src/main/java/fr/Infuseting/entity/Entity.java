package fr.Infuseting.entity;

import fr.Infuseting.fight.Spell;

import java.util.List;

public abstract class Entity {
    protected int currentHP;
    protected int maximumHP;
    protected int armor;
    protected int attack;
    protected List<Spell> effects;

    public  void attack(Entity other){
        int degatMin = 1;
        if(attack == 0){
            attack = degatMin;
        }

        other.currentHP -= attack;
        other.armor -= attack;
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
