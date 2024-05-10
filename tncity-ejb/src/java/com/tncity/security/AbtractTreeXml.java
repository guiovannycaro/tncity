/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.security;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author inmoticamaster
 */
public abstract class AbtractTreeXml {

    private String type;
    private String name;

    @XmlAttribute
    public abstract Long getId();

    protected abstract List<AbtractTreeXml> getLstElements();

    public abstract void setId(Long id);

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
