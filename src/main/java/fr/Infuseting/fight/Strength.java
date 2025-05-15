package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Player;

public class Strength extends Spell{

    public Strength(){
        super(5,5,25,10);
    }

    @Override
    public boolean isSelfSpell() {
        return true; // ce sort s'applique à l'utilisateur
    }

    @Override
    public void specificEffect(Entity entity) {
       entity.setAttack(entity.getAttack()+amount); // Le joueur qui utilise ce sort voit son attaque augmentée

    }
}
