package fr.Infuseting.entity;

import fr.Infuseting.fight.*;

import java.util.List;

/**
 * extends the Entity class
 * Represents a player with a maximum Mana pool, a current mana level in addition to his Entity stats.
 */
public class Player extends Entity{
    public int currentMana;
    public int maximumMana;
    public List<Spell> spells;

    /**
     * Creates a basic player with static stats.
     */
    public Player(){
        this(100, 100, 10, 20, 30, 30);
    }

    /**
     * Creates a player with specified stats.
     * @param currentHP The base amount of HP the player has (equals the maxium HP when constructed)
     * @param maximumHP The maximum amount of HP the player can have.
     * @param armor The amount of armor the player has.
     * @param attack The amount of attack power the player has.
     * @param currentMana The base amount of mana the player has.
     * @param maxMana The maximum amount of mana the player can have.
     * @param spells The list of spells the player has.
     */
    public Player(   int currentHP, int maximumHP, int armor, int attack, int currentMana,int maxMana,List<Spell> spells){
        this.currentHP = currentHP;
        this.maximumHP = maximumHP;
        this.armor = armor;
        this.attack = attack;
        this.currentMana = currentMana;
        this.maximumMana = maxMana;
        this.spells = spells;
    }

    /**
     * Creates a player with specified stats.
     * @param currentHP The base amount of HP the player has (equals the maxium HP when constructed)
     * @param maximumHP The maximum amount of HP the player can have.
     * @param armor The amount of armor the player has.
     * @param attack The amount of attack power the player has.
     * @param currentMana The base amount of mana the player has.
     * @param maxMana The maximum amount of mana the player can have.
     */
    public Player(int currentHP, int maximumHP, int armor, int attack,int currentMana, int maxMana){
        this(currentHP, maximumHP, armor, attack, currentMana, maxMana, List.of(new Heal(),new Shield(),new Strength(),new Poison(),new ManaGain()));
    }

    /**
     * Makes this player cast a spell to the targetted Entity.
     * @param spell The specified spell that is used by the player.
     * @param target The Entity that is targetted by the spell.
     */
    public void castSpell(Spell spell, Entity target) {
      if(spell.apply(target)){
          spell.specificEffect(target);
          target.addEffects(spell);
      }

    }

}
