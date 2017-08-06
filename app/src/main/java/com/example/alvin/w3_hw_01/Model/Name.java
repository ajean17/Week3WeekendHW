
package com.example.alvin.w3_hw_01.Model;

import java.util.HashMap;
import java.util.Map;

public class Name {

    public static final String SERIALIZED_TITLE = "title";
    public static final String SERIALIZED_FIRST = "first";
    public static final String SERIALIZED_LAST = "last";

    private String title;
    private String first;
    private String last;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getFirst() + " " + this.getLast();
    }
}
