/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TelefoniaConfig extends AbstractConfig {

    Integer idusuario;
    String TipoTelefonia;
    String nombreTefefonia;
    String endpoint;

    String host;
    String puerto;
    String protocolo;

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getTipoTelefonia() {
        return TipoTelefonia;
    }

    public void setTipoTelefonia(String TipoTelefonia) {
        this.TipoTelefonia = TipoTelefonia;
    }

    public String getNombreTefefonia() {
        return nombreTefefonia;
    }

    public void setNombreTefefonia(String nombreTefefonia) {
        this.nombreTefefonia = nombreTefefonia;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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

}
