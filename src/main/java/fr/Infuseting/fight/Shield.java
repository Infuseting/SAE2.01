package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;

public class Shield extends Spell{
    public Shield(){
        super(5,5,25,5);
    }

    @Override
    public boolean isSelfSpell() {
        return true;
    }

    @Override
    public void specificEffect(Entity entity) {
        entity.setArmor(entity.getArmor() + amount);
    }
}
