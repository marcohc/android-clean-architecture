
package com.marcohc.architecture.app.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Name extends BaseModel {

    private String title;
    private String first;
    private String last;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first The first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return The last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last The last
     */
    public void setLast(String last) {
        this.last = last;
    }

}
