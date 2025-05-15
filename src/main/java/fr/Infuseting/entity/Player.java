package fr.Infuseting.entity;

import fr.Infuseting.fight.Spell;

import java.util.List;

public class Player extends Entity{
    public int currentMana;
    public int maximumMana;
    public List<Spell> spells;

    public void castSpell(Spell spell, Entity target) {

    }

    @Override
    public void attack(Entity other) {

    }

    @Override
    public void isAlive() {

    }

    @Override
    public void isDead() {

    }
}
