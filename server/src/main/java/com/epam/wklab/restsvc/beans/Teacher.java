package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBContext;

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

    @XmlElement(name="lesson")
    public List<Integer> getLessons() {
        return lessons;
    }

    public void setLessons(List<Integer> lessons) {
        this.lessons = lessons;
    }

public String toXml() {
    try {
        JAXBContext jc = JAXBContext.newInstance(this.getClass());  
        Marshaller marshaller = jc.createMarshaller();  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(this, baos);
        return baos.toString();
    } catch (Exception e) {
        e.printStackTrace();
    }      
    return "";
}
}
