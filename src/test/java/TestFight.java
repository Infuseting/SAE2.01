import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;
import fr.Infuseting.fight.Fight;
import fr.Infuseting.fight.Heal;
import fr.Infuseting.fight.Poison;
import fr.Infuseting.fight.Spell;
import fr.Infuseting.util.JSONParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFight {
    @Test
    public void TestFight_constructeur() {
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\n" +
                "          \"name\": \"Ragondin\",\n" +
                "          \"HP\": 50,\n" +
                "          \"Armor\": 10,\n" +
                "          \"Attack\": 20\n" +
                "        }").parse());
        Fight combat = new Fight(player, monster);
        assertEquals(combat.getPlayer(), player);
        assertEquals(combat.getMonster(), monster);
    }
    @Test
    public void TestTurn_sans_spell() {
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\n" +
                "          \"name\": \"Ragondin\",\n" +
                "          \"HP\": 50,\n" +
                "          \"Armor\": 10,\n" +
                "          \"Attack\": 20\n" +
                "        }").parse());
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        combat.turn(spell);
        assertEquals(monster.getCurrentHP(), 40);
        assertEquals(player.getCurrentHP(), 90);
    }

    @Test
    public void TestTurn_pas_de_mort(){
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\"name\": \"Ragondin\",\"HP\": 50,\"Armor\": 10,\"Attack\": 20}").parse());
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        System.out.println(player.getAttack());
        System.out.println(monster.getCurrentHP());
        Entity res = combat.turn(spell);
        assertNull(res);
    }

    @Test
    public void TestTurn_avec_spell(){
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\"name\": \"Ragondin\",\"HP\": 50,\"Armor\": 10,\"Attack\": 20}").parse());
        Fight combat = new Fight(player, monster);
        Spell spell = new Heal();
        combat.turn(spell);
        assertEquals(player.getCurrentHP(), 90);
    }

    @Test
    public void TestTurn_avec_mort(){
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\"name\": \"Ragondin\",\"HP\": 50,\"Armor\": 10,\"Attack\": 20}").parse());
        Fight combat = new Fight(player, monster);
        Spell spell = null;
        player.attack(monster);
        player.attack(monster);
        player.attack(monster);
        player.attack(monster);
        Entity res = combat.turn(spell);
        assertEquals(res, player);
    }

    @Test
    public void TestNewTurn(){
        Player player = new Player();
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\"name\": \"Ragondin\",\"HP\": 50,\"Armor\": 10,\"Attack\": 20}").parse());
        Fight combat = new Fight(player, monster);
        Spell spell = new Poison();
        combat.turn(spell);
        combat.newTurn();
        assertEquals(monster.getCurrentHP(), 0);
    }

    @Test
    public void TestTurnWithNullMonster() {
        Monster monster = Monster.createMonsterFromJSON(new JSONParser("{\"name\": \"Ragondin\",\"HP\": 50,\"Armor\": 10,\"Attack\": 20}").parse());
        assertThrows(IllegalArgumentException.class, () -> {
            Fight combat = new Fight(null, monster);
        });

    }

}
