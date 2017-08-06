
package com.example.alvin.w3_hw_01.Model;

import java.util.HashMap;
import java.util.Map;

public class Id {

    public static final String SERIALIZED_NAME = "name";
    public static final String SERIALIZED_VALUE = "value";

    private String name;
    private Object value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
