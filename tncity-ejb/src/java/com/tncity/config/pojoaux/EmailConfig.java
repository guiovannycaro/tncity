/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import com.tncity.config.AbstractConfig;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmailConfig extends AbstractConfig{
       Integer idusuario;
       String smtp;
       String port;
       String usuarioMail;
       String contrasenaMail;
       String correoMail;
       String notificacion;

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsuarioMail() {
        return usuarioMail;
    }

    public void setUsuarioMail(String usuarioMail) {
        this.usuarioMail = usuarioMail;
    }

    public String getContrasenaMail() {
        return contrasenaMail;
    }

    public void setContrasenaMail(String contrasenaMail) {
        this.contrasenaMail = contrasenaMail;
    }

    public String getCorreoMail() {
        return correoMail;
    }

    public void setCorreoMail(String correoMail) {
        this.correoMail = correoMail;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
       
       
       
}
