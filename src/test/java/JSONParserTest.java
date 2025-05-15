import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.Infuseting.util.*;

public class JSONParserTest {

    @Test
    public void testParseSimpleObject() {
        String json = "{\"key\":\"value\"}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        assertEquals("value", result.getString("key"));
    }

    @Test
    public void testParseNestedObject() {
        String json = "{\"key\":{\"nestedKey\":\"nestedValue\"}}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        JSONObject nestedObject = result.getJSONObject("key");
        assertEquals("nestedValue", nestedObject.getString("nestedKey"));
    }

    @Test
    public void testParseArray() {
        String json = "{\"key\":[1, 2, 3]}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        JSONArray array = result.getJSONArray("key");
        assertEquals(1, array.getNumber(0));
        assertEquals(2, array.getNumber(1));
        assertEquals(3, array.getNumber(2));
    }

    @Test
    public void testParseBoolean() {
        String json = "{\"key\":true}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        assertTrue(result.getBoolean("key"));
    }

    @Test
    public void testParseNull() {
        String json = "{\"key\":null}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        assertNull(result.get("key"));
    }

    @Test
    public void testParseNumber() {
        String json = "{\"key\":123.45}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        assertEquals(123.45, result.getNumber("key"));
    }

    @Test
    public void testParseStringWithSpaces() {
        String json = "{\"key\":\"value with spaces\"}";
        JSONParser parser = new JSONParser(json);
        JSONObject result = parser.parse();

        assertEquals("value with spaces", result.getString("key"));
    }

    @Test
    public void testInvalidJSONThrowsException() {
        String json = "{\"key\":}";
        JSONParser parser = new JSONParser(json);

        assertThrows(IllegalArgumentException.class, parser::parse);
    }
}