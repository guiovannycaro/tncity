/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.config.pojoaux;

import javax.jws.WebParam;

/**
 *
 * @author guiovanny
 */
public class RecaudoTrans {

    String idrecaudo;
    String idbenefactor;
    String idbeneficiario;
    String td;
    String pin;
    String Telefonosms;
    String created_at;
    String valor;
    String Ciudad;
    String log;
   


    public RecaudoTrans() {
    }

    public RecaudoTrans(String idrecaudo, String idbenefactor, String idbeneficiario, String td, String pin, String Telefonosms, String created_at, String valor, String Ciudad, String log) {
        this.idrecaudo = idrecaudo;
        this.idbenefactor = idbenefactor;
        this.idbeneficiario = idbeneficiario;
        this.td = td;
        this.pin = pin;
        this.Telefonosms = Telefonosms;
        this.created_at = created_at;
        this.valor = valor;
        this.Ciudad = Ciudad;
        this.log = log;
    }

    public String getIdrecaudo() {
        return idrecaudo;
    }

    public void setIdrecaudo(String idrecaudo) {
        this.idrecaudo = idrecaudo;
    }

    public String getIdbenefactor() {
        return idbenefactor;
    }

    public void setIdbenefactor(String idbenefactor) {
        this.idbenefactor = idbenefactor;
    }

    public String getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(String idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTelefonosms() {
        return Telefonosms;
    }

    public void setTelefonosms(String Telefonosms) {
        this.Telefonosms = Telefonosms;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

 
    

}
