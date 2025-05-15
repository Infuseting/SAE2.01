package fr.Infuseting.util;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a JSON array that can store various types of objects.
 * Provides methods to add elements and retrieve them with type safety.
 */
public class JSONArray {

    private List<Object> data = new ArrayList<Object>();

    /**
     * Adds an object to the JSON array.
     *
     * @param obj the object to add
     */
    public void add(Object obj) {
        data.add(obj);
    }

    /**
     * Retrieves an object at the specified index.
     *
     * @param index the index of the object to retrieve
     * @return the object at the specified index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public Object get(int index) {
        if (!(data.size() >= index)) {
            throw new IllegalArgumentException("Index not found: " + index);
        }
        return data.get(index);
    }

    /**
     * Retrieves a string at the specified index.
     *
     * @param index the index of the string to retrieve
     * @return the string at the specified index
     * @throws ClassCastException if the value is not a string
     */
    public String getString(int index) {
        Object value = get(index);
        if (!(value instanceof String)) {
            throw new ClassCastException("Value is not a String: " + value);
        }
        return (String) value;
    }

    /**
     * Retrieves a number at the specified index.
     *
     * @param index the index of the number to retrieve
     * @return the number at the specified index
     * @throws ClassCastException if the value is not a number
     */
    public Number getNumber(int index) {
        Object value = get(index);
        if (!(value instanceof Number)) {
            throw new ClassCastException("Value is not a Number: " + value);
        }
        return (Number) value;
    }

    /**
     * Retrieves a boolean at the specified index.
     *
     * @param index the index of the boolean to retrieve
     * @return the boolean at the specified index
     * @throws ClassCastException if the value is not a boolean
     */
    public Boolean getBoolean(int index) {
        Object value = get(index);
        if (!(value instanceof Boolean)) {
            throw new ClassCastException("Value is not a Boolean: " + value);
        }
        return (Boolean) value;
    }

    /**
     * Retrieves a JSON array at the specified index.
     *
     * @param index the index of the JSON array to retrieve
     * @return the JSON array at the specified index
     * @throws ClassCastException if the value is not a JSON array
     */
    public JSONArray getJSONArray(int index) {
        Object value = get(index);
        if (!(value instanceof JSONArray)) {
            throw new ClassCastException("Value is not a JSONArray: " + value);
        }
        return (JSONArray) value;
    }

    /**
     * Retrieves a JSON object at the specified index.
     *
     * @param index the index of the JSON object to retrieve
     * @return the JSON object at the specified index
     * @throws ClassCastException if the value is not a JSON object
     */
    public JSONObject getJSONObject(int index) {
        Object value = get(index);
        if (!(value instanceof JSONObject)) {
            throw new ClassCastException("Value is not a JSONObject: " + value);
        }
        return (JSONObject) value;
    }
}