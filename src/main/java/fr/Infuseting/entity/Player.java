package fr.Infuseting.entity;

import fr.Infuseting.fight.Heal;
import fr.Infuseting.fight.ManaGain;
import fr.Infuseting.fight.Spell;

import java.util.List;

public class Player extends Entity{
    public int currentMana;
    public int maximumMana;
    public List<Spell> spells = List.of(new Heal(),new ManaGain());

    public void castSpell(Spell spell, Entity target) {
      spell.apply(target);
      spell.specificEffect(target);

    }



}
