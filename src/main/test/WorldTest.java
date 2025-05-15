import fr.Infuseting.map.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    @Test
    public void TestException() {
        world world = new world("Monde1");
        Place place1 = new Place(0, "2237", null, "Salle de TP", false, true, false);
        Place place2 = new Place(1, "2236", null, "Salle de TP", false, false, true);
        world.addPlace(place1);
        Path path = new Path(place1, place2, 10);
        assertThrows(UnKnownPlaceException.class, () -> {
            world.addPath(path);
        });
        world.addPlace(place2);
        assertDoesNotThrow(() -> world.addPath(path));
    }

    @Test
    void testThrowsEstPasAdjacent() {
        world world = new world("Test");
        Place a = new Place(1, "A", null, "", false, false, false);
        Place b = new Place(2, "B", null, "", false, false, false);
        Place c = new Place(3, "C", null, "", false, false, false);

        world.addPlace(a);
        world.addPlace(b);
        world.addPlace(c);

        // connecter A et B, mais pas C
        Path path = new Path(a, b, 5);
        assertDoesNotThrow(() -> world.addPath(path));

        // test si C n’est pas adjacent à A
        assertThrows(EstPasAdjacent.class, () -> {
            world.getPlaceIfAdjacent(a, c);
        });
    }


    @Test
    void testCheckPathExists() throws Exception {
        // Préparation du monde et des lieux
        world w = new world("TestWorld");
        Place a = new Place(1, "A", null, "", false, false, false);
        Place b = new Place(2, "B", null, "", false, false, false);
        w.addPlace(a);
        w.addPlace(b);

        // 1) Chemin enregistré → pas d'exception
        Path p = new Path(a, b, 10);
        w.addPath(p);
        assertDoesNotThrow(() -> w.checkPathExists(p),
                "Le chemin p devrait exister et ne pas lever d'exception");

        // 2) Chemin non ajouté → PathNotFoundException
        Path pAbsent = new Path(a, b, 5);
        assertThrows(PathNotFoundException.class,
                () -> w.checkPathExists(pAbsent),
                "Un chemin non ajouté doit lever PathNotFoundException");
    }

}

