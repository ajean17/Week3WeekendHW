
package com.example.alvin.w3_hw_01.Model;

import java.util.HashMap;
import java.util.Map;

public class Picture {

    public static final String SERIALIZED_LARGE = "large";
    public static final String SERIALIZED_MEDIUM = "medium";
    public static final String SERIALIZED_THUMBNAIL = "thumbnail";

    private String large;
    private String medium;
    private String thumbnail;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
