package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sten on 12.04.17.
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
