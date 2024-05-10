/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojoaux;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PplPojo {
 
    String idBenficiario;
    String estado;
    String tipodocumento;
    String numerodocumento;

    String nombres;
    String apellidos;
    String ciudad;
    String msg;
    boolean successful=false;

    public PplPojo() {
    }

    public PplPojo(String idBenficiario, String estado, String tipodocumento, String numerodocumento, String nombres, String apellidos, String ciudad, String msg) {
        this.idBenficiario = idBenficiario;
        this.estado = estado;
        this.tipodocumento = tipodocumento;
        this.numerodocumento = numerodocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.msg = msg;
    }

    public String getIdBenficiario() {
        return idBenficiario;
    }

    public void setIdBenficiario(String idBenficiario) {
        this.idBenficiario = idBenficiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

   
    
    
}
