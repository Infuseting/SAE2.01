package fr.Infuseting.fight;

import fr.Infuseting.entity.*;
/**
 * Represents a spell that can be cast by the player or the monster.
 * Provides methods to apply the spell to an entity and to get the spell's name, duration, cost and amount.
 * @author Achille
 */
public abstract class Spell {
    protected int duration;
    protected int nbTurnLeft;
    protected int amount;
    protected int cost;
    protected String name;

    /**
     * Creates a new spell with a specified duration, number of turns left, amount and cost.
     * @param duration the specified duration of the spell.
     * @param nbTurnLeft the numbers of turns that the spell will last for.
     * @param amount the amount of potency the spell has.
     * @param cost The mana cost of the spell.
     */
    public Spell(int duration, int nbTurnLeft, int amount, int cost, String name) {
        this.duration = duration;
        this.nbTurnLeft = nbTurnLeft;
        this.amount = amount;
        this.cost = cost;
    };
    public int getManaCost() {
        return cost;
    }
    public int getNbTurnLeft() {
        return nbTurnLeft;
    }

    /**
     * Applies a spell to a specified entity.
     * @param entity an Entity who's the target of the spell.
     * @return true if the spell apply has been successful and false if it hasn't been.
     */
    public boolean apply(Entity entity){
        if(entity.equals(null))return false; // si l'entité est null le sort ne peut être appliqué
        if(nbTurnLeft < 1) return false; // si le nb de tour du sort est à 0 alors il ne peut plus être lancé
        if(entity instanceof Player){ // si le joueur est un player il a du mana
            if(duration == nbTurnLeft) { // si le sort est lancé pour la première fois
                if (((Player) entity).currentMana < cost)
                    return false; // si son mana est inférieur au cout du sort alors il ne peut pas l'appliquer
                else {
                    ((Player) entity).currentMana -= cost; // son mana est mis à jour
                    return true;

                }
            }else{ // si le sort a deja été lancé auparavant alors le joueur ne dépense pas de mana a nouveau
                return true;
            }
        }
        return true;

    }
    public String getName() {
        return name;
    }

    /**
     * Applies the respective effects of the spells that are present in the specified entity's effects List.
     * @param entity the specified entity.
     */
    public  abstract void specificEffect(Entity entity);

    /**
     * Indicates if this Spell is supposed to be cast on the user or not.
     * @return true if the Spell is a SelfSpell, false if it isn't.
     */
    public abstract  boolean isSelfSpell();

    @Override
    public String toString() {
        return String.format("%s (Durée: %s, Coût: %s, Nombre de tours restants: %s, Damage : %s) ", name, duration, cost, nbTurnLeft, amount);
    }
}