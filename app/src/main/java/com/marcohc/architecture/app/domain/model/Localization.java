
package com.marcohc.architecture.app.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Localization extends BaseModel {

    private String street;
    private String city;
    private String state;
    private String zip;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
