package fr.Infuseting.entity;

import fr.Infuseting.fight.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player extends Entity{
    public int currentMana;
    public int maximumMana;
    public List<Spell> spells;
    public Player(){
        this(100, 100, 10, 20, 30, 30);
    }
    public Player(   int currentHP, int maximumHP, int armor, int attack, int currentMana,int maxMana,List<Spell> spells){
        this.currentHP = currentHP;
        this.maximumHP = maximumHP;
        this.armor = armor;
        this.attack = attack;
        this.currentMana = currentMana;
        this.maximumMana = maxMana;
        this.spells = spells;
    }

    public Player(int currentHP, int maximumHP, int armor, int attack,int currentMana, int maxMana){
        this(currentHP, maximumHP, armor, attack, currentMana, maxMana, List.of(new Heal(),new Shield(),new Strength(),new Poison(),new ManaGain()));
    }
    public void castSpell(Spell spell, Entity target) {
      if(spell.apply(target)){
          spell.specificEffect(target);
          target.addEffects(spell);
      }

    }

    public List<Spell> getUsableSpells() {
            List<Spell> usableSpells = new ArrayList<>(spells);
            usableSpells.removeIf(sp -> sp.getManaCost() > currentMana);
            return usableSpells;

    }
}
