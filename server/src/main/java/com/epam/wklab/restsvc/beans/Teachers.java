package com.epam.wklab.restsvc.beans;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by sten on 12.04.17.
 */
@XmlRootElement(name="teachers")
public class Teachers {
    private List<Teacher> teachers;

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @XmlElement(name="teacher")
    public List<Teacher> getTeachers() {
        return teachers;
    }

}
