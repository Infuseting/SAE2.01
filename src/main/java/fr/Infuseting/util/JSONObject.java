package fr.Infuseting.util;

import java.util.HashMap;

/**
 * Represents a JSON object that stores key-value pairs.
 * Provides methods to add, retrieve, and manipulate data with type safety.
 * @author Arthur
 */
public class JSONObject {

    private HashMap<String, Object> data = new HashMap<>();

    /**
     * Adds a key-value pair to the JSON object.
     *
     * @param key   the key as a string
     * @param value the value associated with the key
     */
    public void put(String key, Object value) {
        data.put(key, value);
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key
     * @throws IllegalArgumentException if the key is not found
     */
    public Object get(String key) {
        if (!data.containsKey(key)) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return data.get(key);
    }

    /**
     * Retrieves a string value associated with the specified key.
     *
     * @param key the key to look up
     * @return the string value associated with the key
     * @throws ClassCastException if the value is not a string
     */
    public String getString(String key) {
        Object value = get(key);
        if (!(value instanceof String)) {
            throw new ClassCastException("Value is not a String: " + value);
        }
        return (String) value;
    }

    /**
     * Retrieves a numeric value associated with the specified key.
     *
     * @param key the key to look up
     * @return the numeric value associated with the key
     * @throws ClassCastException if the value is not a number
     */
    public Number getNumber(String key) {
        Object value = get(key);
        if (!(value instanceof Number)) {
            throw new ClassCastException("Value is not a Number: " + value);
        }
        return (Number) value;
    }

    /**
     * Retrieves a boolean value associated with the specified key.
     *
     * @param key the key to look up
     * @return the boolean value associated with the key
     * @throws ClassCastException if the value is not a boolean
     */
    public Boolean getBoolean(String key) {
        Object value = get(key);
        if (!(value instanceof Boolean)) {
            throw new ClassCastException("Value is not a Boolean: " + value);
        }
        return (Boolean) value;
    }

    /**
     * Retrieves a JSON array associated with the specified key.
     *
     * @param key the key to look up
     * @return the JSON array associated with the key
     * @throws ClassCastException if the value is not a JSON array
     */
    public JSONArray getJSONArray(String key) {
        Object value = get(key);
        if (!(value instanceof JSONArray)) {
            throw new ClassCastException("Value is not a JSONArray: " + value);
        }
        return (JSONArray) value;
    }

    /**
     * Retrieves a JSON object associated with the specified key.
     *
     * @param key the key to look up
     * @return the JSON object associated with the key
     * @throws ClassCastException if the value is not a JSON object
     */
    public JSONObject getJSONObject(String key) {
        Object value = get(key);
        if (!(value instanceof JSONObject)) {
            throw new ClassCastException("Value is not a JSONObject: " + value);
        }
        return (JSONObject) value;
    }


    /**
     * Returns a string representation of the JSON object.
     *
     * @return the JSON object as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : data.keySet()) {
            sb.append("\"").append(key).append("\":");
            Object value = data.get(key);
            if (value instanceof String) {
                sb.append("\"").append(value).append("\"");
            } else if (value instanceof JSONObject || value instanceof JSONArray) {
                sb.append(value.toString());
            } else {
                sb.append(value);
            }
            sb.append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1); // Remove the last comma
        }
        sb.append("}");
        return sb.toString();
    }
}