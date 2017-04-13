package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sten on 12.04.17.
 */
@XmlRootElement
public class Lesson extends Marshallable {
    private Integer id;
    private String name;
    private Integer durationMinutes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
