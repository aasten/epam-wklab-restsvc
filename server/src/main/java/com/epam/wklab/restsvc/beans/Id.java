package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by sten on 12.04.17.
 * For transferring scalar id as a response
 */
@XmlRootElement
public class Id {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
