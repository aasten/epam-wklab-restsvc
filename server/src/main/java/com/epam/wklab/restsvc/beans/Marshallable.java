package com.epam.wklab.restsvc.beans;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBContext;

public abstract class Marshallable {

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
