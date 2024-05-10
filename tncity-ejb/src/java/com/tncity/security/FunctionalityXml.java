/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.security;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "functionalityTree")
public class FunctionalityXml extends AbtractTreeXml {

    private long idfunc;
    String observations;
    String text;

    public FunctionalityXml() {

    }

    public FunctionalityXml(long id) {
        this.idfunc = id;
    }

    public FunctionalityXml(long id, String name) {
        this.idfunc = id;
        setName(name);
    }

    List<FunctionalityXml> lstFunctionality = new ArrayList<>();

    @Override
    public Long getId() {
        return idfunc;
    }

    @Override
    public void setId(Long id) {
        this.idfunc = id;
    }

    @Override
    protected List<AbtractTreeXml> getLstElements() {
        return (List) this.lstFunctionality;
    }

    @XmlElement
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @XmlElement(name = "item")
    public List<FunctionalityXml> getLstFunctionality() {
        return lstFunctionality;
    }

    public void setLstFunctionality(List<FunctionalityXml> lstFunctionality) {
        this.lstFunctionality = lstFunctionality;
    }

    @Override
    public String getName() {
        return text; //To change body of generated methods, choose Tools | Templates.
    }

    @XmlAttribute
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
