/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TelefoniaConfigSms extends AbstractConfig {

    String host;
    String puerto;
    String protocolo;
    String token_principal;
    String token_sms;
    String estado;
    String sms;
    String flash;
    
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

    public String getToken_principal() {
        return token_principal;
    }

    public void setToken_principal(String token_principal) {
        this.token_principal = token_principal;
    }

    public String getToken_sms() {
        return token_sms;
    }

    public void setToken_sms(String token_sms) {
        this.token_sms = token_sms;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    
    
}
