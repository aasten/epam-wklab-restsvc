package com.epam.wklab.restsvc.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sten on 12.04.17.
 */
@XmlRootElement
public class RESTException {
    private String what;

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }
}
