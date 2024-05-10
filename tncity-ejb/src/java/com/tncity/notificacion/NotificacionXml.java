/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.notificacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificacionXml implements Serializable{

    int id;
    String descripcion;
    boolean isActiva = false;
    boolean sendEmail = true;
    boolean sendSms = false;
    
    String mailAsunto = "Asunto";
    String mailContent = "Contenido";
    String smsContent = "Test SMS !";
    List<NotificacionVarXml> var = new ArrayList<>();

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isIsActiva() {
        return isActiva;
    }

    public void setIsActiva(boolean isActiva) {
        this.isActiva = isActiva;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    

    
    
    
    
    public String getMailAsunto() {
        return mailAsunto;
    }

    public void setMailAsunto(String mailAsunto) {
        this.mailAsunto = mailAsunto;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    @XmlElement(name = "var")
    public List<NotificacionVarXml> getVar() {
        return var;
    }

    public void setVar(List<NotificacionVarXml> var) {
        this.var = var;
    }

   

    
    
}
