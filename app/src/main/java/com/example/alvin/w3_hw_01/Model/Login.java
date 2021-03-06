
package com.example.alvin.w3_hw_01.Model;

import java.util.HashMap;
import java.util.Map;

public class Login {

    public static final String SERIALIZED_USERNAME = "username";
    public static final String SERIALIZED_PASSWORD = "password";
    public static final String SERIALIZED_SALT = "salt";
    public static final String SERIALIZED_MD5 = "md5";
    public static final String SERIALIZED_SHA1 = "sha1";
    public static final String SERIALIZED_SHA256 = "sha256";

    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
