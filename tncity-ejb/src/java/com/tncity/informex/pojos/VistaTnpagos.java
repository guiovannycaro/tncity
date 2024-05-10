/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.informex.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author guiovanny
 */
public class VistaTnpagos implements Serializable {

    String idvista;

    String nombres;

    String apellidos;

    String tipo_documento;

    String numero_documento;

    String num_telefono;

    String email;

    String direccion;

    String ciudad_familiar;

    String departamento_familiar;

    String pais_familiar;

    String tipodocumento_ppl;

    String numero_documento_ppl;

    String nombre_ppl;

    String idrecaudo;

    String idbenefactor;

    String valor;

    String estado;

    String log;

    String created_at;

    String check_telefonia_at;

    String establecimiento;

    String cod_pago;

    String telefonosms;

    String observacion;

    String observacionAdicional;

    String ciudad_ppl;
    String pasareladia;

    String nombre_pasarela;

    String codtransaccion;

    String formapago;

    String franquicia;

    String descripcion;

    String referencia1;

    String fechapago;

    String numerorecibo;

    String codigo;

    String mensajeerror;

    public VistaTnpagos() {
    }

    public VistaTnpagos(String idvista, String nombres, String apellidos, String tipo_documento, String numero_documento, String num_telefono, String email, String direccion, String ciudad_familiar, String departamento_familiar, String pais_familiar, String tipodocumento_ppl, String numero_documento_ppl, String nombre_ppl, String idrecaudo, String idbenefactor, String valor, String estado, String log, String created_at, String check_telefonia_at, String establecimiento, String cod_pago, String telefonosms, String observacion, String observacionAdicional, String ciudad_ppl, String pasareladia, String nombre_pasarela, String codtransaccion, String formapago, String franquicia, String descripcion, String referencia1, String fechapago, String numerorecibo, String codigo, String mensajeerror) {
        this.idvista = idvista;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.num_telefono = num_telefono;
        this.email = email;
        this.direccion = direccion;
        this.ciudad_familiar = ciudad_familiar;
        this.departamento_familiar = departamento_familiar;
        this.pais_familiar = pais_familiar;
        this.tipodocumento_ppl = tipodocumento_ppl;
        this.numero_documento_ppl = numero_documento_ppl;
        this.nombre_ppl = nombre_ppl;
        this.idrecaudo = idrecaudo;
        this.idbenefactor = idbenefactor;
        this.valor = valor;
        this.estado = estado;
        this.log = log;
        this.created_at = created_at;
        this.check_telefonia_at = check_telefonia_at;
        this.establecimiento = establecimiento;
        this.cod_pago = cod_pago;
        this.telefonosms = telefonosms;
        this.observacion = observacion;
        this.observacionAdicional = observacionAdicional;
        this.ciudad_ppl = ciudad_ppl;
        this.pasareladia = pasareladia;
        this.nombre_pasarela = nombre_pasarela;
        this.codtransaccion = codtransaccion;
        this.formapago = formapago;
        this.franquicia = franquicia;
        this.descripcion = descripcion;
        this.referencia1 = referencia1;
        this.fechapago = fechapago;
        this.numerorecibo = numerorecibo;
        this.codigo = codigo;
        this.mensajeerror = mensajeerror;
    }

  

    public VistaTnpagos(String thomas_cook, String thomasexamplecom, Date time, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPasareladia() {
        return pasareladia;
    }

    public void setPasareladia(String pasareladia) {
        this.pasareladia = pasareladia;
    }



 

    
    
    public String getIdvista() {
        return idvista;
    }

    public void setIdvista(String idvista) {
        this.idvista = idvista;
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

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad_familiar() {
        return ciudad_familiar;
    }

    public void setCiudad_familiar(String ciudad_familiar) {
        this.ciudad_familiar = ciudad_familiar;
    }

    public String getDepartamento_familiar() {
        return departamento_familiar;
    }

    public void setDepartamento_familiar(String departamento_familiar) {
        this.departamento_familiar = departamento_familiar;
    }

    public String getPais_familiar() {
        return pais_familiar;
    }

    public void setPais_familiar(String pais_familiar) {
        this.pais_familiar = pais_familiar;
    }

    public String getTipodocumento_ppl() {
        return tipodocumento_ppl;
    }

    public void setTipodocumento_ppl(String tipodocumento_ppl) {
        this.tipodocumento_ppl = tipodocumento_ppl;
    }

    public String getNumero_documento_ppl() {
        return numero_documento_ppl;
    }

    public void setNumero_documento_ppl(String numero_documento_ppl) {
        this.numero_documento_ppl = numero_documento_ppl;
    }

    public String getNombre_ppl() {
        return nombre_ppl;
    }

    public void setNombre_ppl(String nombre_ppl) {
        this.nombre_ppl = nombre_ppl;
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

    public String getTelefonosms() {
        return telefonosms;
    }

    public void setTelefonosms(String telefonosms) {
        this.telefonosms = telefonosms;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionAdicional() {
        return observacionAdicional;
    }

    public void setObservacionAdicional(String observacionAdicional) {
        this.observacionAdicional = observacionAdicional;
    }

    public String getCiudad_ppl() {
        return ciudad_ppl;
    }

    public void setCiudad_ppl(String ciudad_ppl) {
        this.ciudad_ppl = ciudad_ppl;
    }

    public String getNombre_pasarela() {
        return nombre_pasarela;
    }

    public void setNombre_pasarela(String nombre_pasarela) {
        this.nombre_pasarela = nombre_pasarela;
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
    
    
}
