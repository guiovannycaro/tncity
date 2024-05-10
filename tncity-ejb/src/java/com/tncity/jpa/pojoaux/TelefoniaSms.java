/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojoaux;

/**
 *
 * @author guiovanny
 */
public class TelefoniaSms {
    String status;
    String cantidad_mensajes;
    String valor;
    String cantidad_caracteres;
    String mensaje;
    String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCantidad_mensajes() {
        return cantidad_mensajes;
    }

    public void setCantidad_mensajes(String cantidad_mensajes) {
        this.cantidad_mensajes = cantidad_mensajes;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCantidad_caracteres() {
        return cantidad_caracteres;
    }

    public void setCantidad_caracteres(String cantidad_caracteres) {
        this.cantidad_caracteres = cantidad_caracteres;
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
