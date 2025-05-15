package fr.Infuseting.fight;

import fr.Infuseting.entity.*;
public abstract class Spell {
    protected int duration;
    protected int nbTurnLeft;
    protected int amount;
    protected int cost;
    protected String name;
    public Spell(int duration, int nbTurnLeft, int amount, int cost, String name){
        this.duration = duration;
        this.nbTurnLeft = nbTurnLeft;
        this.amount = amount;
        this.cost = cost;
        this.name = name;
    };
    public int getManaCost() {
        return cost;
    }
    public int getNbTurnLeft() {
        return nbTurnLeft;
    }

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
    public  abstract void specificEffect(Entity entity);

    public abstract  boolean isSelfSpell();

    @Override
    public String toString() {
        return String.format("%s (Durée: %s, Coût: %s, Nombre de tours restants: %s, Damage : %s) ", name, duration, cost, nbTurnLeft, amount);
    }
}