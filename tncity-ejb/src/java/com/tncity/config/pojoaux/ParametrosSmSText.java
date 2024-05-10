/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

/**
 *
 * @author guiovanny
 */
import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ParametrosSmSText  extends AbstractConfig {
 String from;
String flash;
String token_empresa;
String mensaje;
String id;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public String getToken_empresa() {
        return token_empresa;
    }

    public void setToken_empresa(String token_empresa) {
        this.token_empresa = token_empresa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    
}
