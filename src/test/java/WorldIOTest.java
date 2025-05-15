import fr.Infuseting.map.WorldIO;
import fr.Infuseting.map.World;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

public class WorldIOTest {

    @Test
    public void testFilesCreation() {
        WorldIO.saveWorld(new World(null), new File("test.json"));
    }
    @Test
    public void testFilesLoading() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("world/Monde1.json");
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: world/Monde1.json");
        }
        World world = WorldIO.loadWorld(inputStream);
        System.out.println(world.toJson());
    }


}
