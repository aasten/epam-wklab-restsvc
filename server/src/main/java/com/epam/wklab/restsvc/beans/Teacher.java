package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by sten on 12.04.17.
 */
@XmlRootElement
public class Teacher {

    private Integer id;
    private String name;
    private Date birthDay;
    private List<Integer/* lesson id */> lessons;

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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<Integer> getLessons() {
        return lessons;
    }

    public void setLessons(List<Integer> lessons) {
        this.lessons = lessons;
    }
}
