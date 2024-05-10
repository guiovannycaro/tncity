/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
public class ConfigXml {

    Integer id;
    String classPojo;
    List<ConfigXml> lstParams = new ArrayList<>();

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlAttribute
    public String getClassPojo() {
        return classPojo;
    }

    public void setClassPojo(String classPojo) {
        this.classPojo = classPojo;
    }

    @XmlElement(name = "param")
    public List<ConfigXml> getLstParams() {
        return lstParams;
    }

    public void setLstParams(List<ConfigXml> lstParams) {
        this.lstParams = lstParams;
    }

}
