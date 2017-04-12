package com.epam.wklab.restsvc.beans;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by sten on 12.04.17.
 */
@XmlRootElement(name="lessons")
public class Lessons {
    private List<Lesson> lessons;

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @XmlElement(name="lesson")
    public List<Lesson> getLessons() {
        return lessons;
    }

}
