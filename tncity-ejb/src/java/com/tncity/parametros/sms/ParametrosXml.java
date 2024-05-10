/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.parametros.sms;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParametrosXml implements Serializable {

    int id;
    String descripcion;
    String host;
    String puerto;
    String protocolo;
    boolean isActiva = false;

    List<ParametrosVarXml> var = new ArrayList<>();

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsActiva() {
        return isActiva;
    }

    public void setIsActiva(boolean isActiva) {
        this.isActiva = isActiva;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public List<ParametrosVarXml> getVar() {
        return var;
    }

    public void setVar(List<ParametrosVarXml> var) {
        this.var = var;
    }

}
