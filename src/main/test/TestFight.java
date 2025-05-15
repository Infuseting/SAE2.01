import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;
import fr.Infuseting.fight.Fight;
import fr.Infuseting.fight.Heal;
import fr.Infuseting.fight.Spell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFight {
    @Test
    public void TestFight_constructeur() {
        Player player = new Player();
        Monster monster = new Monster();
        Fight combat = new Fight(player, monster);
        assertEquals(combat.getPlayer(), player);
        assertEquals(combat.getMonster(), monster);
    }
    @Test
    public void TestTurn_sans_spell() {
        Player player = new Player();
        Monster monster = new Monster();
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        combat.turn(spell);
        assertEquals(monster.getCurentHP(), 40);
        assertEquals(player.getCurentHP(), 140);
    }

    @Test
    public void TestTurn_pas_de_mort(){
        Player player = new Player();
        Monster monster = new Monster();
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        Entity res = combat.turn(spell);
        assertEquals(res, null);
    }

    @Test
    public void TestTurn_avec_spell(){
        Player player = new Player();
        Monster monster = new Monster();
        Fight combat = new Fight(player, monster);
        Spell spell = new Heal();
        combat.turn(spell);
        System.out.println(player.getCurentHP());
        assertEquals(player.getCurentHP(), 150);
    }

    @Test
    public void TestTurn_avec_mort(){
        Player player = new Player();
        Monster monster = new Monster();
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        player.attack(monster);
        player.attack(monster);
        player.attack(monster);
        player.attack(monster);
        Entity res = combat.turn(spell);
        assertEquals(res, player);
    }
}
