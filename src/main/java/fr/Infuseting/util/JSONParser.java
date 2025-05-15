package fr.Infuseting.util;

/**
 * A parser for JSON strings that converts them into {@link JSONObject} or {@link JSONArray}.
 * Supports parsing of strings, numbers, booleans, nulls, objects, and arrays.
 */
public class JSONParser {
    private String toParse;
    private int position;

    /**
     * Constructs a JSONParser with the given JSON string.
     * Removes unnecessary whitespace outside of quoted strings.
     *
     * @param toParse the JSON string to parse
     */
    public JSONParser(String toParse) {
        this.toParse = toParse.replaceAll("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", "");
        this.position = 0;
    }

    /**
     * Parses the JSON string into a {@link JSONObject}.
     *
     * @return the parsed {@link JSONObject}
     */
    public JSONObject parse() {
        return parseObject();
    }

    /**
     * Parses a JSON object from the string.
     *
     * @return the parsed {@link JSONObject}
     * @throws IllegalArgumentException if the JSON object is malformed
     */
    private JSONObject parseObject() {
        JSONObject jsonObject = new JSONObject();
        expect('{');
        while (true) {
            char current = peek();
            if (current == '}') {
                next();
                break;
            }
            String key = parseString();
            expect(':');
            Object value = parseValue();
            jsonObject.put(key, value);
            current = peek();
            if (current == ',') {
                next();
            } else if (current == '}') {
                continue;
            } else {
                throw new IllegalArgumentException("Unexpected character: " + current);
            }
        }
        return jsonObject;
    }

    /**
     * Parses a JSON value, which can be a string, number, boolean, null, object, or array.
     *
     * @return the parsed value as an {@link Object}
     * @throws IllegalArgumentException if the value is malformed
     */
    private Object parseValue() {
        char current = peek();
        if (current == '"') {
            return parseString();
        } else if (current == '{') {
            return parseObject();
        } else if (current == '[') {
            return parseArray();
        } else if (current == 't' || current == 'f') {
            return parseBoolean();
        } else if (current == 'n') {
            return parseNull();
        } else if (Character.isDigit(current) || current == '-') {
            return parseNumber();
        } else {
            throw new IllegalArgumentException("Unexpected character: " + current);
        }
    }

    /**
     * Parses a JSON string value.
     *
     * @return the parsed string
     * @throws IllegalArgumentException if the string is malformed
     */
    private String parseString() {
        expect('"');
        StringBuilder sb = new StringBuilder();
        while (true) {
            char current = next();
            if (current == '"') {
                break;
            }
            sb.append(current);
        }
        return sb.toString();
    }

    /**
     * Parses a JSON boolean value ("true" or "false").
     *
     * @return the parsed boolean
     * @throws IllegalArgumentException if the boolean value is invalid
     */
    private Boolean parseBoolean() {
        if (toParse.startsWith("true", position)) {
            position += 4;
            return true;
        } else if (toParse.startsWith("false", position)) {
            position += 5;
            return false;
        } else {
            throw new IllegalArgumentException("Invalid boolean value");
        }
    }

    /**
     * Parses a JSON null value.
     *
     * @return null
     * @throws IllegalArgumentException if the null value is invalid
     */
    private Object parseNull() {
        if (toParse.startsWith("null", position)) {
            position += 4;
            return null;
        } else {
            throw new IllegalArgumentException("Invalid null value");
        }
    }

    /**
     * Parses a JSON number value.
     *
     * @return the parsed number as a {@link Number}
     * @throws NumberFormatException if the number is invalid
     */
    private Number parseNumber() {
        int start = position;
        while (position < toParse.length() && (Character.isDigit(toParse.charAt(position)) || toParse.charAt(position) == '.' || toParse.charAt(position) == '-')) {
            position++;
        }
        String number = toParse.substring(start, position);
        if (number.contains(".")) {
            return Double.parseDouble(number);
        } else {
            return Integer.parseInt(number);
        }
    }

    /**
     * Parses a JSON array from the string.
     *
     * @return the parsed {@link JSONArray}
     * @throws IllegalArgumentException if the JSON array is malformed
     */
    private JSONArray parseArray() {
        JSONArray jsonArray = new JSONArray();
        expect('[');
        while (true) {
            char current = peek();
            if (current == ']') {
                next();
                break;
            }
            Object value = parseValue();
            jsonArray.add(value);
            current = peek();
            if (current == ',') {
                next();
            } else if (current == ']') {
                continue;
            } else {
                throw new IllegalArgumentException("Unexpected character: " + current);
            }
        }
        return jsonArray;
    }

    /**
     * Peeks at the current character in the JSON string without advancing the position.
     *
     * @return the current character
     */
    private char peek() {
        return toParse.charAt(position);
    }

    /**
     * Retrieves the current character in the JSON string and advances the position.
     *
     * @return the current character
     */
    private char next() {
        return toParse.charAt(position++);
    }

    /**
     * Ensures that the next character in the JSON string matches the expected character.
     * Advances the position if the character matches.
     *
     * @param expected the expected character
     * @throws IllegalArgumentException if the character does not match
     */
    private void expect(char expected) {
        char current = next();
        if (current != expected) {
            throw new IllegalArgumentException("Expected '" + expected + "' but found '" + current + "'");
        }
    }
}