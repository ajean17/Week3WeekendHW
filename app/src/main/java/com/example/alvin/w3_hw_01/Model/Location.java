
package com.example.alvin.w3_hw_01.Model;

import java.util.HashMap;
import java.util.Map;

public class Location {

    public static final String SERIALIZED_STREET = "street";
    public static final String SERIALIZED_CITY = "city";
    public static final String SERIALIZED_STATE = "state";
    public static final String SERIALIZED_POSTCODE = "postcode";

    private String street;
    private String city;
    private String state;
    private String postcode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return this.getStreet() + ", " + this.getCity() + " " + this.getState() + " " + this.getPostcode();
    }
}
