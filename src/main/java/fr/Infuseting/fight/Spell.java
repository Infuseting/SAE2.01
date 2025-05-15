package fr.Infuseting.fight;

import fr.Infuseting.entity.*;
public abstract class Spell {
    protected int duration;
    protected int nbTurnLeft;
    protected int amount;
    protected int cost;

    public boolean apply(Entity entity){
        return false;
    }

    public void specificEffect(Entity entity){
    }

    public boolean isSelfSpell(){
        return false;
    }
}