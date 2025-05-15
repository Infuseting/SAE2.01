package fr.Infuseting.entity;

import fr.Infuseting.fight.*;

import java.util.List;

public class Player extends Entity{
    public int currentMana;
    public int maximumMana;
    public List<Spell> spells = List.of(new Heal(),new Shield(),new Strength(),new Poison(),new ManaGain());

    public void castSpell(Spell spell, Entity target) {
      if(spell.apply(target)){
          spell.specificEffect(target);
      }

    }



}
