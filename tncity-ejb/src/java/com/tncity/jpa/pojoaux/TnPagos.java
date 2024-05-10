/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.jpa.pojoaux;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author guiovanny
 */
public class TnPagos implements Serializable {

    String idrecaudo;
    String idbenefactor;
    String idbeneficiario;
    String valor;
    String estado;
    String log;
    String created_at;
    String check_telefonia_at;
    String establecimiento;
    String cod_pago;
    String token_wtnpagos;
    String idpasarela;
    String telefonosms;
    String ciudad;
    String observacion;
    String observacion_adicional;
    String pasareladia;
    String valorcomision;
    String numtransaccion;
    String valorpromocion;
    String promo;

    String idtransaccion;
    String codtransaccion;
    String formapago;
    String franquicia;
    String descripcion;
    String referencia1;
    String fechapago;
    String numerorecibo;
    String codigo;
    String mensajeerror;
    String codrecaudo;

    String td;
    String pin;
    String nombres_apellidos;
    String nombres;
    String apellidos;
    String tipodocumento;
    String numdocumento;

    public TnPagos() {
    }

    public TnPagos(String idrecaudo, String idbenefactor, String idbeneficiario, String valor, String estado, String log, String created_at, String check_telefonia_at, String establecimiento, String cod_pago, String token_wtnpagos, String idpasarela, String telefonosms, String ciudad, String observacion, String observacion_adicional, String pasareladia, String valorcomision, String numtransaccion, String valorpromocion, String promo, String idtransaccion, String codtransaccion, String formapago, String franquicia, String descripcion, String referencia1, String fechapago, String numerorecibo, String codigo, String mensajeerror, String codrecaudo, String td, String pin, String nombres_apellidos, String nombres, String apellidos, String tipodocumento, String numdocumento) {
        this.idrecaudo = idrecaudo;
        this.idbenefactor = idbenefactor;
        this.idbeneficiario = idbeneficiario;
        this.valor = valor;
        this.estado = estado;
        this.log = log;
        this.created_at = created_at;
        this.check_telefonia_at = check_telefonia_at;
        this.establecimiento = establecimiento;
        this.cod_pago = cod_pago;
        this.token_wtnpagos = token_wtnpagos;
        this.idpasarela = idpasarela;
        this.telefonosms = telefonosms;
        this.ciudad = ciudad;
        this.observacion = observacion;
        this.observacion_adicional = observacion_adicional;
        this.pasareladia = pasareladia;
        this.valorcomision = valorcomision;
        this.numtransaccion = numtransaccion;
        this.valorpromocion = valorpromocion;
        this.promo = promo;
        this.idtransaccion = idtransaccion;
        this.codtransaccion = codtransaccion;
        this.formapago = formapago;
        this.franquicia = franquicia;
        this.descripcion = descripcion;
        this.referencia1 = referencia1;
        this.fechapago = fechapago;
        this.numerorecibo = numerorecibo;
        this.codigo = codigo;
        this.mensajeerror = mensajeerror;
        this.codrecaudo = codrecaudo;
        this.td = td;
        this.pin = pin;
        this.nombres_apellidos = nombres_apellidos;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipodocumento = tipodocumento;
        this.numdocumento = numdocumento;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCheck_telefonia_at() {
        return check_telefonia_at;
    }

    public void setCheck_telefonia_at(String check_telefonia_at) {
        this.check_telefonia_at = check_telefonia_at;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getCod_pago() {
        return cod_pago;
    }

    public void setCod_pago(String cod_pago) {
        this.cod_pago = cod_pago;
    }

    public String getToken_wtnpagos() {
        return token_wtnpagos;
    }

    public void setToken_wtnpagos(String token_wtnpagos) {
        this.token_wtnpagos = token_wtnpagos;
    }

    public String getIdpasarela() {
        return idpasarela;
    }

    public void setIdpasarela(String idpasarela) {
        this.idpasarela = idpasarela;
    }

    public String getTelefonosms() {
        return telefonosms;
    }

    public void setTelefonosms(String telefonosms) {
        this.telefonosms = telefonosms;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacion_adicional() {
        return observacion_adicional;
    }

    public void setObservacion_adicional(String observacion_adicional) {
        this.observacion_adicional = observacion_adicional;
    }

    public String getPasareladia() {
        return pasareladia;
    }

    public void setPasareladia(String pasareladia) {
        this.pasareladia = pasareladia;
    }

    public String getValorcomision() {
        return valorcomision;
    }

    public void setValorcomision(String valorcomision) {
        this.valorcomision = valorcomision;
    }

    public String getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(String numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public String getValorpromocion() {
        return valorpromocion;
    }

    public void setValorpromocion(String valorpromocion) {
        this.valorpromocion = valorpromocion;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(String idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public String getCodtransaccion() {
        return codtransaccion;
    }

    public void setCodtransaccion(String codtransaccion) {
        this.codtransaccion = codtransaccion;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia1() {
        return referencia1;
    }

    public void setReferencia1(String referencia1) {
        this.referencia1 = referencia1;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public String getNumerorecibo() {
        return numerorecibo;
    }

    public void setNumerorecibo(String numerorecibo) {
        this.numerorecibo = numerorecibo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensajeerror() {
        return mensajeerror;
    }

    public void setMensajeerror(String mensajeerror) {
        this.mensajeerror = mensajeerror;
    }

    public String getCodrecaudo() {
        return codrecaudo;
    }

    public void setCodrecaudo(String codrecaudo) {
        this.codrecaudo = codrecaudo;
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

    public String getNombres_apellidos() {
        return nombres_apellidos;
    }

    public void setNombres_apellidos(String nombres_apellidos) {
        this.nombres_apellidos = nombres_apellidos;
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

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }
    
    

}
